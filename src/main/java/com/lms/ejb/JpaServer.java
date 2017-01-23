package com.lms.ejb;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;


/** Wrap EntityManager. */
@Stateless
public class JpaServer {

	public static final int ID_OFFSET = 1000000;
	public static final int USER_ID = 100;

//	private static final Logger log = Logger.getLogger("audit");

	private Map<String, EntityType<?>> entityTypes = new TreeMap<>();

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	private void init() {
		for(EntityType<?> et: em.getMetamodel().getEntities()) {
			entityTypes.put(et.getName(), et);
		}
	}

	public void createDummyData() {

	}

	public Object query(String ql, boolean isNative, boolean isUpdate) {
		ql = ql.replace('+', ' ');
		Query q = isNative ? em.createNativeQuery(ql) : em.createQuery(ql);
		return isUpdate ? q.executeUpdate() : q.getResultList();
	}

	public Object select(String name, Integer id,
			String filter, String group, String sort) {
		if(name == null || name.isEmpty()) return entityTypes.keySet();
		if(id != null) return find(name, id);
		DbQuery q = new DbQuery("select o from " + name + " o");
		q.filter = filter;
		q.group = group;
		q.sort = sort;
		return q.getResults(em, Object.class);
	}

	public Object find(String name, Integer id) {
		return em.find(entityTypes.get(name).getJavaType(), id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object persist(String name, Map map)
			throws ReflectiveOperationException {
		Object obj = entityTypes.get(name).getJavaType().newInstance();
		copyTo(obj, map);
		em.persist(obj);
		return obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object merge(String name, Integer id, Map map)
			throws ReflectiveOperationException {
		Object obj = find(name, id);
		if(obj == null) return obj;
		copyTo(obj, map);
		return em.merge(obj);
	}

	public Object remove(String name, Integer id) {
		Object obj = find(name, id);
		if(obj != null) em.remove(obj);
		return obj;
	}

	private void copyTo(Object to, Map<String, Object> from)
			throws ReflectiveOperationException {
		for(Map.Entry<String, Object> entry: from.entrySet()) {
			String name = entry.getKey();
			Field fld = null;
			for(Class<?> cls = to.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
				try {
					fld = cls.getDeclaredField(name);
					break;
				}
				catch(NoSuchFieldException ex) { }
			}
			if(fld == null) throw new NoSuchFieldException(name);
			if(!fld.isAccessible()) fld.setAccessible(true);
			Object val = entry.getValue();
			String str = val.toString();
			val = null;
			Class<?> cls = fld.getType();
			if(cls == String.class) val = str;
			if(cls == Double.class) val = Double.valueOf(str);
			if(cls == Integer.class) val = Integer.valueOf(str);
			if(cls == Boolean.class) val = Boolean.valueOf(str); 
			if(cls == Timestamp.class) val = Timestamp.valueOf(str);
			if(cls == byte[].class) val = Base64.getDecoder().decode(str); 
			if(val == null) throw new RuntimeException("Unhandled field: " + fld);
			fld.set(to, val);
		}
	}
}


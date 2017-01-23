package com.lms.ejb;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.lms.common.entity.BaseEntity;

/** Internal DB access. */
@Path("jpa")
public class JpaRest {

	@EJB
	private JpaServer server;

	@GET
	@Path("createDummyData")
	public void createDummyData() {
		server.createDummyData();
	}

	@GET
	@Path("jpql")
	@Produces(MediaType.APPLICATION_JSON)
	public Object jpql(
			@QueryParam("isNative") boolean isNative,
			@QueryParam("isUpdate") boolean isUpdate,
			@QueryParam("ql") String ql) {
		return clearRefs(server.query(ql, isNative, isUpdate));
	}

	@GET
	@Path("select")
	@Produces(MediaType.APPLICATION_JSON)
	public Object select(@QueryParam("name") String name,
			@QueryParam("id") String key,
			@QueryParam("filter") String filter,
			@QueryParam("group") String group,
			@QueryParam("sort") String sort) {
		Integer id = (key == null || key.isEmpty()) ? null : new Integer(key);
		return clearRefs(server.select(name, id, filter, group, sort));
	}

	@GET
	@Path("crud")
	@Produces(MediaType.APPLICATION_JSON)
	public Object selectNames() {
		return clearRefs(server.select(null, null, null, null, null));
	}

	@GET
	@Path("crud/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object selectIds(@PathParam("name") String name) {
		return clearRefs(server.query("select o.id from " + name + " o order by o.id", false, false));
	}

	@GET
	@Path("crud/{name}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object select(@PathParam("name") String name, @PathParam("id") Integer id) {
		return clearRefs(server.select(name, id, null, null, null));
	}

	@POST
	@Path("crud/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object persist(@PathParam("name") String name,
			@SuppressWarnings("rawtypes") Map entity)
			throws ReflectiveOperationException {
		return clearRefs(server.persist(name, entity));
	}

	@PUT
	@Path("crud/{name}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object merge(@PathParam("name") String name, @PathParam("id") Integer id,
			@SuppressWarnings("rawtypes") Map entity) throws ReflectiveOperationException {
		return clearRefs(server.merge(name, id, entity));
	}

	@DELETE
	@Path("crud/{name}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object remove(@PathParam("name") String name, @PathParam("id") Integer id) {
		return clearRefs(server.remove(name, id));
	}

	private Object clearRefs(Object obj) {
		return clearRefs2(obj);
	}

	@SuppressWarnings("unused")
	private Object clearRefs1(Object obj) {
		if(obj instanceof Iterable) {
			for(Object o: (Iterable<?>)obj) clearRefs1(o);
		}
		if(obj instanceof BaseEntity) {
			for(Class<?> cls = obj.getClass(); cls != BaseEntity.class; cls = cls.getSuperclass()) {
				for(Field fld: cls.getDeclaredFields()) {
					if(fld.isAnnotationPresent(OneToMany.class)
							|| fld.isAnnotationPresent(ManyToOne.class)) {
						if(!fld.isAccessible()) fld.setAccessible(true);
						try {
							fld.set(obj, null);
						}
						catch(IllegalAccessException e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		}
		return obj;
	}

	private Object clearRefs2(Object obj) {
		if(obj instanceof Iterable) {
			List<Object> list = new ArrayList<>();
			for(Object o: (Iterable<?>)obj) list.add(clearRefs2(o));
			return list;
		}
		if(obj instanceof BaseEntity) {
			Map<String, Object> map = new LinkedHashMap<>();
			for(Class<?> cls = obj.getClass(); cls != BaseEntity.class; cls = cls.getSuperclass()) {
				for(Field fld: cls.getDeclaredFields()) {
					if(fld.isAnnotationPresent(OneToMany.class)
							|| fld.isAnnotationPresent(ManyToOne.class)) continue;
					if(!fld.isAccessible()) fld.setAccessible(true);
					try {
						Object val = fld.get(obj);
						if(val != null) map.put(fld.getName(), val);
					}
					catch(IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
			return map;
		}
		return obj;
	}
}


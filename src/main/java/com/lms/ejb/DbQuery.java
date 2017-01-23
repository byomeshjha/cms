package com.lms.ejb;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DbQuery {

	public String ql;
	public String filter;
	public String group;
	public String sort;
	public int limit;
	public int offset;
	public Map<String, Object> params;

	public DbQuery(String ql) {
		this.ql = ql;
	}

	public String toString() {
		return ql + " " + filter + " " + group + " " + sort + " " + limit + " " + offset + " " + params;
	}

	public void andFilterParam(String filter, String key, Object value) {
		if(value == null
				|| (value instanceof String && ((String)value).isEmpty())
				|| (value instanceof Collection && ((Collection<?>)value).isEmpty()))
			return;
		andFilter(filter + " :" + key);
		addParam(key, value);
	}

	public <T> List<T> getResults(EntityManager em, Class<T> cls) {
		return createQuery(em, cls).getResultList();
	}

	public void andFilter(String filter) {
		this.filter = (this.filter == null || this.filter.isEmpty())
				? filter : this.filter + " and " + filter;
	}

	public void addParam(String key, Object value) {
		if(params == null) params = new HashMap<>();
		params.put(key, value);
	}

	private <T> TypedQuery<T> createQuery(EntityManager em, Class<T> cls) {
		StringBuilder sb = new StringBuilder();
		if(filter != null && !filter.isEmpty()) sb.append(" where ").append(filter);
		if(group != null && !group.isEmpty()) sb.append(" group by " + group);
		if(sort != null && !sort.isEmpty()) sb.append(" order by " + sort);
		String str = ql + sb;
		TypedQuery<T> q = em.createQuery(str, cls);
		if(limit > 0) q.setMaxResults(limit);
		if(offset > 0) q.setFirstResult(offset);
		if(params != null) {
			for(Map.Entry<String, Object> param : params.entrySet()) {
				q.setParameter(param.getKey(), param.getValue());
			}
		}
		return q;
	}
}


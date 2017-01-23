package com.lms.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lms.common.entity.Client;
import com.lms.common.entity.Member;

@Stateless
public class ClientEjb {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Client> getClientList() {
		DbQuery query = new DbQuery("select o from Client o");
		List<Client> list = query.getResults(em, Client.class);
		
		return list;
	}
	
	public Client getClientWithID(Integer clientId) {
		DbQuery query = new DbQuery("select o from Client o join fetch o.addresses where o.clientId = :clientId");
		query.addParam("clientId", clientId);
		List<Client> list = query.getResults(em, Client.class);
		Client c = (list.size() > 0) ? list.get(0) : null;
		
		query = new DbQuery("select o from Member o where o.clientId = :clientId");
		query.addParam("clientId", clientId);
		List<Member> memList = query.getResults(em, Member.class);
		if(memList.size() > 0) c.setMembers(memList);
		
		return c;
	}
	
	public List<Client> getClientList(int firstRow, int maxRow, String sortCol, String sortOrder) {
		if(sortCol == null) sortCol = "Client_ID";
		String queryString = "select * from Client order by "+ sortCol + " " + sortOrder;

		@SuppressWarnings("unchecked")
		List<Client> list = em.createNativeQuery(queryString, 
				Client.class)
				.setMaxResults(maxRow)
				.setFirstResult(firstRow).getResultList();
		
		return list;
	}
	
	public List<Client> getClientList(String searchParameter) {
		String s = "%" + searchParameter.toUpperCase() + "%";
		DbQuery query = new DbQuery("select o from Client o "
				+ "where UPPER(o.firstName) like :fn or UPPER(o.lastName) like :ln or o.phone1 like :ph or o.emailAddress like :em");
		query.addParam("fn", s);
		query.addParam("ln", s);
		query.addParam("ph", s);
		query.addParam("em", s);
		List<Client> list = query.getResults(em, Client.class);
		
		return list;
	}
	
	public int getClientCount() {
		Query query = em.createQuery("select count(*) from Client o");
		Long count = (Long) query.getSingleResult();
		
		return (count != null ? count.intValue() : 0);
	}
	
	public Member getMemberWithID(Integer memberId) {
		DbQuery query = new DbQuery("select o from Member o where o.memberId = :memberId");
		query.addParam("memberId", memberId);
		List<Member> list = query.getResults(em, Member.class);
		Member m = (list.size() > 0) ? list.get(0) : null;

		return m;
	}
	
	public Client saveClient(Client o) {

		if(o.getClientId() == null) {
			em.persist(o);
		} else {
			em.merge(o);
		}
		
		em.flush();

		return o;
	}
	
	public Member saveMember(Member o) {

		if(o.getFirstName() != null && !"".equals(o.getFirstName())) {
			if(o.getClientId() == null) return null;
			if(o.getMemberId() == null) {
				em.persist(o);
			} else {
				em.merge(o);
			}
		}

		
		em.flush();

		return o;
	}
}

package com.lms.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lms.common.entity.CaseDocket;
import com.lms.common.entity.CaseDocument;
import com.lms.common.entity.CaseNote;
import com.lms.common.entity.CaseReminder;
import com.lms.common.entity.FirmUser;

@Stateless
public class CaseEjb {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<CaseDocket> getCaseList() {
		DbQuery query = new DbQuery("select o from CaseDocket o");
		List<CaseDocket> list = query.getResults(em, CaseDocket.class);
		
		return list;
	}
	
	public CaseDocket getCase(Integer ID) {
		DbQuery query = new DbQuery("select o from CaseDocket o "
				+ "JOIN FETCH o.caseNotes a " 
				+ "where o.caseDocketId = :oid and a.caseDocketId = :aid");
		query.addParam("oid", ID);
		query.addParam("aid", ID);
		List<CaseDocket> list = query.getResults(em, CaseDocket.class);
		
		return (list.size() > 0) ? list.get(0) : null;
	}
	
	public List<CaseDocument> getCaseDocuments(Integer ID) {
		DbQuery query = new DbQuery("select o from CaseDocument o "
				+ "where o.caseDocketId = :did");
		query.addParam("did", ID);
		List<CaseDocument> list = query.getResults(em, CaseDocument.class);
		
		return list;
	}
	
	public List<CaseDocket> getCaseList(int firstRow, int maxRow, String sortCol, String sortOrder) {
		if(sortCol == null) sortCol = "Case_Docket_ID";
		String queryString = "select * from CaseDocket order by "+ sortCol + " " + sortOrder;

		@SuppressWarnings("unchecked")
		List<CaseDocket> list = em.createNativeQuery(queryString, 
				CaseDocket.class)
				.setMaxResults(maxRow)
				.setFirstResult(firstRow).getResultList();
		
		return list;
	}
	
	public int getCaseCount() {
		Query query = em.createQuery("select count(*) from CaseDocket o");
		Long count = (Long) query.getSingleResult();
		
		return (count != null ? count.intValue() : 0);
	}
	
	
	public List<CaseReminder> getCaseReminderList(FirmUser user, boolean showForAllUsers) {
		String queryString = "select o from CaseReminder o where o.reminderDate order by o.reminderDate";
		Query query = em.createQuery(queryString);
		
		List<CaseReminder> list = query.getResultList();
		
		return list;
	}
	
	
	public CaseDocket saveCase(CaseDocket o) {
		
		if(o.getCaseDocketId() == null) {
			em.persist(o);
		} else {
			em.merge(o);
		}
		
		if(o.getCaseNotes() != null && o.getCaseNotes().size() > 0) {
			for(CaseNote m : o.getCaseNotes()) {
				m.setCaseDocketId(o.getCaseDocketId());
				if(m.getNoteId() == null) {
					em.persist(m);
				} else {
					em.merge(m);
				}
			}
		}
		
		if(o.getDocuments() != null && o.getDocuments().size() > 0) {
			for(CaseDocument m : o.getDocuments()) {
				m.setCaseDocketId(o.getCaseDocketId());
				if(m.getCaseDocumentId() == null) {
					em.persist(m);
				} else {
					em.merge(m);
				}
			}
		}
		
		em.flush();

		return o;
	}
}

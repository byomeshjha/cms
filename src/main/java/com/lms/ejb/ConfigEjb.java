package com.lms.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.lms.common.entity.AppUser;
import com.lms.common.entity.CaseStatus;
import com.lms.common.entity.CaseType;
import com.lms.common.entity.EmailConfig;
import com.lms.common.entity.Firm;
import com.lms.common.entity.FirmUser;
import com.lms.common.entity.Maintenance;


@Stateless
public class ConfigEjb {
	
	@PersistenceContext
	private EntityManager em;
	
	public Maintenance getMaintenance() {
		DbQuery query = new DbQuery("select o from Maintenance o");
		List<Maintenance> list = query.getResults(em, Maintenance.class);
		Maintenance o = null;
		if(null != list && list.size() > 0)
			o = list.get(0);
		return o;
	}
	
	public EmailConfig getEmailConfig() {
		DbQuery query = new DbQuery("select o from EmailConfig o");
		List<EmailConfig> list = query.getResults(em, EmailConfig.class);
		EmailConfig o = null;
		if(null != list && list.size() > 0)
			o = list.get(0);
		return o;
	}
	
	public List<AppUser> getAppUserList() {
		DbQuery query = new DbQuery("select o from AppUser o");
		List<AppUser> list = query.getResults(em, AppUser.class);
		
		return list;
	}
	
	public List<FirmUser> getFirmUserList(Integer firmId) {
		DbQuery query = new DbQuery("select o from FirmUser o where o.firmId = :firmId");
		query.addParam("firmId", firmId);
		List<FirmUser> list = query.getResults(em, FirmUser.class);
		
		return list;
	}
	
	public List<FirmUser> getFirmUserList(int firstRow, int maxRow, String sortCol, String sortOrder, Integer firmId) {
		if(sortCol == null) sortCol = "User_ID";
		String queryString = "select * from FirmUser " + (firmId == 0 ? "" : " where Firm_ID = :firmId ") + 
				" order by "+ sortCol + " " + sortOrder;
		Query query = em.createNativeQuery(queryString, FirmUser.class);
		if(firmId > 0)
			query.setParameter("firmId", firmId);
		@SuppressWarnings("unchecked")
		List<FirmUser> list = query
				.setMaxResults(maxRow)
				.setFirstResult(firstRow).getResultList();
		
		return list;
	}
	
	public int getFirmUserCount() {
		Query query = em.createQuery("select count(*) from FirmUser o");
		Long count = (Long) query.getSingleResult();
		
		return (count != null ? count.intValue() : 0);
	}
	
	public AppUser getAppUser(final AppUser au) {
		DbQuery query = new DbQuery("select o from AppUser o where o.emailAddress = :email and o.password.password = :password");
		query.addParam("email", au.getEmailAddress());
		query.addParam("password", au.getPassword());
		List<AppUser> list = query.getResults(em, AppUser.class);
		AppUser o = null;
		if(null != list && list.size() > 0)
			o = list.get(0);
		return o;
	}
	
	public FirmUser getFirmUser(final FirmUser firmUser, Firm firm) {
		DbQuery query = new DbQuery("select o from FirmUser o where o.emailAddress = :email "
				+ "and o.password.password = :password and o.firmId = :firmId");
		query.addParam("email", firmUser.getEmailAddress());
		query.addParam("password", firmUser.getPassword());
		query.addParam("firmId", firm.getFirmId());
		List<FirmUser> list = query.getResults(em, FirmUser.class);
		FirmUser o = null;
		if(null != list && list.size() > 0)
			o = list.get(0);
		return o;
	}
	
	public Firm getFirmByDomain(final String domain) {
		DbQuery query = new DbQuery("select o from Firm o where o.firmDomain = :domain ");
		query.addParam("domain", domain);
		List<Firm> list = query.getResults(em, Firm.class);
		Firm o = null;
		if(null != list && list.size() > 0)
			o = list.get(0);
		return o;
	}

	
	public AppUser getAppUserByID(Integer id) {
		DbQuery query = new DbQuery("select o from AppUser o where o.id = :id ");
		query.addParam("id", id);
		List<AppUser> list = query.getResults(em, AppUser.class);
		AppUser o = null;
		if(null != list && list.size() > 0)
			o = list.get(0);
		return o;
	}
	
	public FirmUser getFirmUserByID(Integer id) {
		DbQuery query = new DbQuery("select o from FirmUser o where o.id = :id ");
		query.addParam("id", id);
		List<FirmUser> list = query.getResults(em, FirmUser.class);
		FirmUser o = null;
		if(null != list && list.size() > 0)
			o = list.get(0);
		return o;
	}
	
	public List<CaseStatus> getCaseStatusList(Firm firm) {
		DbQuery query = new DbQuery("select o from CaseStatus o where o.firmId = :firmId ");
		query.addParam("firmId", firm.getFirmId());
		List<CaseStatus> list = query.getResults(em, CaseStatus.class);

		return list;
	}
	
	public List<CaseType> getCaseTypeList(Firm firm) {
		DbQuery query = new DbQuery("select o from CaseType o where o.firmId = :firmId ");
		query.addParam("firmId", firm.getFirmId());
		List<CaseType> list = query.getResults(em, CaseType.class);

		return list;
	}
	
	
	public Maintenance saveMaintenance(Maintenance o) {
		if(o.getId() == null)
			em.persist(o);
		else
			em.merge(o);
		
		em.flush();

		return o;
	}
	
	public EmailConfig saveEmailConfig(EmailConfig o) {
		if(o.getId() == null)
			em.persist(o);
		else
			em.merge(o);
		
		em.flush();

		return o;
	}
	
	public AppUser saveAppUser(AppUser o) {
		if(o.getId() == null)
			em.persist(o);
		else
			em.merge(o);
		
		em.flush();

		return o;
	}
	
	public FirmUser saveFirmUser(FirmUser o) {
		if(o.getId() == null)
			em.persist(o);
		else
			em.merge(o);
		
		em.flush();

		return o;
	}
	
	public AppUser deleteAppUser(AppUser o) {
		em.remove(o);
		em.flush();

		return o;
	}
	
	public FirmUser deleteFirmUser(FirmUser o) {
		em.remove(o);
		em.flush();

		return o;
	}
}

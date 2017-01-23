package com.lms.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@MappedSuperclass
public abstract class BaseEntity {
	
	//@Inject @Named
	//SessionUserInfo sessionUser;
	
	@Column(name = "Created_By")
	private Integer CreatedByUserID;
	
	@Column(name="Date_Time_Created")
	private Timestamp dateTimeCreated;
	
	@Column(name = "Last_Modified_By")
	private Integer LastModifiedByUserID;
	
	@Column(name="Date_Time_Modified")
	private Timestamp dateTimeModified;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Created_By", insertable = false, updatable = false)
	private FirmUser CreatedByUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Last_Modified_By", insertable = false, updatable = false)
	private FirmUser LastModifiedByUser;
	
	protected int get_int_id() { throw new UnsupportedOperationException(); }
	protected Object get_obj_id() { return getId(); }
	
	public Object getId() { throw new UnsupportedOperationException(); }
	
	@Override
	public boolean equals(Object obj) { return objEquals(obj); }

	@SuppressWarnings("unused")
	private boolean intEquals(Object obj) {
		if(obj == null || obj.getClass() != getClass()) return false;
		int id1 = this.get_int_id();
		int id2 = ((BaseEntity)obj).get_int_id();
		return (id1 > 0) ? id1 == id2 : (id2 > 0) ? false : super.equals(obj);
	}

	private boolean objEquals(Object obj) {
		if(obj == null || obj.getClass() != getClass()) return false;
		Object id1 = this.get_obj_id();
		Object id2 = ((BaseEntity)obj).get_obj_id();
		return (id1 != null) ? id1.equals(id2) : (id2 != null) ? false : super.equals(obj);
	}

	@Override
	public int hashCode() { return objHash(); }

	@SuppressWarnings("unused")
	private int intHash() {
		int id = get_int_id();
		return (id > 0) ? id : super.hashCode();
	}

	private int objHash() {
		Object id = get_obj_id();
		return (id != null) ? id.hashCode() : super.hashCode();
	}
	
	@PrePersist
	void prePersist() {
		preUpdate();
		dateTimeCreated = dateTimeModified;
		CreatedByUserID = LastModifiedByUserID;
	}

	@PreUpdate
	void preUpdate() {
		dateTimeModified = new Timestamp(System.currentTimeMillis());
	}
	public Integer getCreatedByUserID() {
		return CreatedByUserID;
	}
	public void setCreatedByUserID(Integer createdByUserID) {
		CreatedByUserID = createdByUserID;
	}
	public Timestamp getDateTimeCreated() {
		return dateTimeCreated;
	}
	public void setDateTimeCreated(Timestamp dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}
	public Integer getLastModifiedByUserID() {
		return LastModifiedByUserID;
	}
	public void setLastModifiedByUserID(Integer lastModifiedByUserID) {
		LastModifiedByUserID = lastModifiedByUserID;
	}
	public Timestamp getDateTimeModified() {
		return dateTimeModified;
	}
	public void setDateTimeModified(Timestamp dateTimeModified) {
		this.dateTimeModified = dateTimeModified;
	}
	public FirmUser getCreatedByUser() {
		return CreatedByUser;
	}
	public void setCreatedByUser(FirmUser createdByUser) {
		CreatedByUser = createdByUser;
	}
	public FirmUser getLastModifiedByUser() {
		return LastModifiedByUser;
	}
	public void setLastModifiedByUser(FirmUser lastModifiedByUser) {
		LastModifiedByUser = lastModifiedByUser;
	}

}

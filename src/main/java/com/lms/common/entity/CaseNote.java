package com.lms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class CaseNote extends BaseEntity {
	
	@Column(name = "Note_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NoteSequence")
	@SequenceGenerator(name="NoteSequence", sequenceName="note_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Note_ID")
	private Integer noteId;
	
	@Column(name = "Note", length = 255)
	private String note;
	
	@Column(name = "Case_Docet_ID")
	private Integer caseDocketId;

	@ManyToOne
	@JoinColumn(name = "Case_Docet_ID", insertable = false, updatable = false)
	private CaseDocket caseDocket;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getCaseDocketId() {
		return caseDocketId;
	}

	public void setCaseDocketId(Integer caseDocketId) {
		this.caseDocketId = caseDocketId;
	}

	public CaseDocket getCaseDocket() {
		return caseDocket;
	}

	public void setCaseDocket(CaseDocket caseDocket) {
		this.caseDocket = caseDocket;
	}
	
}

package com.gotracrat.managelocation.dto;

import java.util.Date;

/**
 * @author manikanta
 */
public class ChangeLogDTO {

	private Integer journalid;
	private Integer entityid;
	private String entry;
	private String enteredby;
	private Date enteredon;

	public ChangeLogDTO(Integer journalid, Integer entityid, String entry, String enteredby, Date enteredon) {
		super();
		this.journalid = journalid;
		this.entityid = entityid;
		this.entry = entry;
		this.enteredby = enteredby;
		this.enteredon = enteredon;
	}

	public Integer getJournalid() {
		return journalid;
	}

	public void setJournalid(Integer journalid) {
		this.journalid = journalid;
	}

	public Integer getEntityid() {
		return entityid;
	}

	public void setEntityid(Integer entityid) {
		this.entityid = entityid;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getEnteredby() {
		return enteredby;
	}

	public void setEnteredby(String enteredby) {
		this.enteredby = enteredby;
	}

	public Date getEnteredon() {
		return enteredon;
	}

	public void setEnteredon(Date enteredon) {
		this.enteredon = enteredon;
	}

	

}

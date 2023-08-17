package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ItemService", schema = "dbo")
public class ItemService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ServiceId",nullable = false)
	private Integer serviceId;
	@Column(name = "ItemID",nullable = false)
	private Integer itemId;
	@Column(name = "ServiceDate",nullable =false)
	private Date serviceDate;
	@Column(name = "ServiceCause")
	private String serviceCause;
	@Column(name = "Complete")
	private Boolean complete;
	@Column(name = "ActualCompletion")
	private Date actualCompletion;
	@Column(name = "CompletedBy")
	private String completedBy;
	@Column(name = "CreatedDate")
	private Date createdDate;
	@Column(name = "CreatedBy")
	private String createdBy;
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	@Column(name = "updatedBy")
	private String updatedBy;
}

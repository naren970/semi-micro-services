package com.gotracrat.managelocation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Persistent class for Failuretype entity stored in table FailureType. This
 * class is a "record entity" without JPA links.
 * 
 * @author Prabhakar
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FailureType", schema = "dbo")
public class FailureType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FailureTypeId", nullable = false)
	private Integer failuretypeid;
	@Column(name = "ItemTypeId", nullable = false)
	private Integer itemtypeid;
	@Column(name = "Description", nullable = false, length = 50)
	private String description;
	@Column(name = "Causes", nullable = false)
	private String causes;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(failuretypeid);
		sb.append(itemtypeid);
		sb.append("|");
		sb.append(description);
		sb.append("|");
		sb.append(causes);
		return sb.toString();
	}
}
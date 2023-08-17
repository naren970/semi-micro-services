package com.gotracrat.managecompany.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/* 
 * Persistent class for Manual entity stored in table Status.
 * This class is a "record entity" without JPA links.
 * @author shirisha
 * since 2020-03-10
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "Manual", schema = "dbo")
public class Manual implements Serializable {
	private static final long serialVersionUID = 1L;
  	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)
  	@Column(name = "ManualID", nullable = false)
  	private Integer manualid;  
    @Column(name = "FileName", nullable = true)
    private String filename;
    @Column(name = "ContentType", nullable = true)
    private String contenttype;
	@Column(name = "Description", nullable = true)
    private String description;
    @Column(name = "Manual", nullable = true)
    private byte[] manual;
   
    public Manual() {
         super();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
           stringBuilder.append(manualid);
           stringBuilder.append("|");
           stringBuilder.append(filename);
           stringBuilder.append("|");
           stringBuilder.append(contenttype);
           stringBuilder.append("|");
           stringBuilder.append(description);
           stringBuilder.append("|");
           stringBuilder.append(manual);
           return stringBuilder.toString();
    }
}
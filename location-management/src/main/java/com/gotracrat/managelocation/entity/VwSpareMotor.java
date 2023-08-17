package com.gotracrat.managelocation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vw_Spare_Motors", schema = "dbo")
public class VwSpareMotor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ITEMID", nullable = false)
	private Integer itemId;
	@Column(name = "Tag")
	private String tag;
	@Column(name = "TYPENAME")
	private String typeName;
	@Column(name = "[HP/KW/KVA]")
	private String hp;
	@Column(name = "RPM")
	private String rpm;
	@Column(name = "[AC / (ARM) VOLTS]")
	private String voltage;
	@Column(name = "FRAME")
	private String frame;
	@Column(name = "ENCLOSURE")
	private String enclosure;
	@Column(name = "[Phase/Hertz]")
	private String phase;
}

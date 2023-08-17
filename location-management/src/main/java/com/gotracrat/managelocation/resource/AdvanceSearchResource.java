package com.gotracrat.managelocation.resource;

import com.gotracrat.managelocation.dto.AdvanceSearchNotesDTO;
import com.gotracrat.managelocation.dto.AdvanceSearchRepairDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvanceSearchResource implements Serializable {


	private static final long serialVersionUID = 1L;
	private int companyID;
	private String extraTag;
	private String RFQ;
	private String PO;
	private String job;
	private boolean noteFlag;
	private boolean repairFlag;
	private boolean rfqFlag;
	private String isOwnerAdmin;
	private String userId;
	private List<AdvanceSearchNotesDTO> itemNotes;
	private List<AdvanceSearchRepairDTO> repairlogList;
	private List<RFQResource> RFQsList;
}

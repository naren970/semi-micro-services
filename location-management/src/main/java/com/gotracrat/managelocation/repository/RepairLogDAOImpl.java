package com.gotracrat.managelocation.repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gotracrat.managelocation.dto.RepairLogAttachmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.resource.RepairLogResource;

@Repository
public class RepairLogDAOImpl implements RepairLogDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RepairLogAttachmentDTO> getAllAttachments(Integer itemId) {

		String sql = "select AttachmentID,EntityID,FileName,isNew from attachment where entityID in (Select RepairLogID from repairlog  where itemId ='"
				+ itemId + "') and entityTypeId=12";

		return jdbcTemplate.query(sql, new RowMapper<RepairLogAttachmentDTO>() {

			@Override
			public RepairLogAttachmentDTO mapRow(java.sql.ResultSet rs, int row) throws SQLException {
				RepairLogAttachmentDTO attachment = new RepairLogAttachmentDTO();
				attachment.setAttachmentId(rs.getInt("AttachmentID"));
				attachment.setFileName(rs.getString("FileName"));
				attachment.setNew(rs.getBoolean("isNew"));
				attachment.setEntityId(rs.getInt("EntityID"));
				jdbcTemplate.getDataSource().getConnection().close();
				return attachment;
			}
		});
	}

	@Override
	public RepairLogResource getRepairLog(Integer repairlogid) {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("spRepairLogGetByRepairLogId");
		SqlParameterSource in = new MapSqlParameterSource().addValue("RepairLogID", repairlogid);
		RepairLogResource repairlogResource = new RepairLogResource();
		try {
			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List repairLogList = (List) out.get("#result-set-1");
			Iterator i = repairLogList.iterator();

			while (i.hasNext()) {
				Map repairLogMap = (Map) i.next();
				repairlogResource.setRepairlogid((Integer) repairLogMap.get("RepairLogId"));
				repairlogResource.setItemid((Integer) repairLogMap.get("ItemId"));
				repairlogResource.setItemtype((String) repairLogMap.get("ItemType"));
				repairlogResource.setRfqnumber((String) repairLogMap.get("RFQNumber"));
				repairlogResource.setPonumber((String) repairLogMap.get("PONumber"));
				repairlogResource.setRepairvendornumber((String) repairLogMap.get("RepairVendorNumber"));
				repairlogResource.setJobnumber((String) repairLogMap.get("JobNumber"));
				repairlogResource.setDateinitiated((Date) repairLogMap.get("DateInitiated"));
				repairlogResource.setDateacknowledged((Date) repairLogMap.get("DateAcknowledged"));
				repairlogResource.setEstimatedcompletion((Date) repairLogMap.get("EstimatedCompletion"));
				repairlogResource.setActualcompletion((Date) repairLogMap.get("ActualCompletion"));
				repairlogResource.setRepaircost((BigDecimal) repairLogMap.get("RepairCost"));
				repairlogResource.setIswarranty((Boolean) repairLogMap.get("IsWarranty"));
				repairlogResource.setWarrantytype((String) repairLogMap.get("WarrantyType"));
				repairlogResource.setFailuretype((String) repairLogMap.get("FailureType"));
				repairlogResource.setFailurecause((String) repairLogMap.get("FailureCause"));
				repairlogResource.setFailuredate((Date) repairLogMap.get("FailureDate"));
				repairlogResource.setRepairlocationid((Integer) repairLogMap.get("RepairLocationID"));
				repairlogResource.setRepaircompanyid((Integer) repairLogMap.get("RepairCompanyID"));
				repairlogResource.setTransferlogid((Integer) repairLogMap.get("TransferLogID"));
				repairlogResource.setRepairnotes((String) repairLogMap.get("RepairNotes"));
				repairlogResource.setTag((String) repairLogMap.get("Tag"));
				repairlogResource.setRepairjobstatus((String) repairLogMap.get("RepairJobStatus"));
				repairlogResource.setTitle((String) repairLogMap.get("Title"));
				repairlogResource.setComplete((Boolean) repairLogMap.get("Complete"));
				repairlogResource.setCompletedby((String) repairLogMap.get("CompletedBy"));
				repairlogResource.setRepairVendorName((String) repairLogMap.get("RepairVendorName"));
				repairlogResource.setRepairLocationName((String) repairLogMap.get("LocationPath"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return repairlogResource;
	}
}
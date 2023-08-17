package com.gotracrat.attributesandtypes.repository;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.controller.resource.AttachmentDTO;
import com.gotracrat.attributesandtypes.utils.GotracratUtility;

@Repository
public class AttachmentDAOImpl implements AttachmentDAO {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AttachmentDTO> getAllAttachments(int entityid) {
		String sql = "select EntityID,AttachmentID,FileName,isNew from Attachment where EntityID in (select JournalID from journal where EntityID= '"+entityid+"' and EntityTypeID=2 and JournalTypeID=3) and EntityTypeID=10";
		return jdbcTemplate.query(sql, new RowMapper<AttachmentDTO>() {

			@Override
			public AttachmentDTO mapRow(java.sql.ResultSet rs, int row) throws SQLException {
				AttachmentDTO l = new AttachmentDTO();
				l.setEntityId(rs.getInt("EntityID"));
				l.setAttachmentID(rs.getInt("AttachmentID"));
				l.setFileName(rs.getString("FileName"));
				l.setNew(rs.getBoolean("isNew"));
			    jdbcTemplate.getDataSource().getConnection().close();
			    return l;
			}
			
			
		});
		


	}

	@Override
	public List<AttachmentDTO> getAllAttachmentsForNotes(int entityid, int companyID, int journalTypeId,int entitytypeid) {

		String sql = "select EntityID,AttachmentID,FileName,isNew from Attachment where EntityID in (select JournalID from journal where EntityID= '"+entityid+"'  and CompanyID='"+companyID+"' and JournalTypeID='"+journalTypeId+"'and EntityTypeID='"+entitytypeid+"')";
		return jdbcTemplate.query(sql, new RowMapper<AttachmentDTO>() {

			@Override
			public AttachmentDTO mapRow(java.sql.ResultSet rs, int row) throws SQLException {
				AttachmentDTO l = new AttachmentDTO();
				l.setEntityId(rs.getInt("EntityID"));
				l.setAttachmentID(rs.getInt("AttachmentID"));
				l.setFileName(rs.getString("FileName"));
				l.setNew(rs.getBoolean("isNew"));
			    jdbcTemplate.getDataSource().getConnection().close();
			    return l;
			}
			
			
		});
	}

	
	
	
}

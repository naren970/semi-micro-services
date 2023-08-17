package com.gotracrat.attributesandtypes.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.Status;
@Repository
public class StatusDAOImpl implements StatusDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	
	public List<Status> getAllStatus(int companyId, int entityTypeId) {
		String sql = "select Status,StatusID from Status where CompanyID='" + companyId + "' and EntityTypeID='"+entityTypeId+"'";

		return jdbcTemplate.query(sql, new RowMapper<Status>() {

			@Override
			public Status mapRow(java.sql.ResultSet rs, int row) throws SQLException {
				Status s  = new Status();
				s.setStatus(rs.getString("Status"));
s.setStatusid(rs.getInt("StatusID"));	
jdbcTemplate.getDataSource().getConnection().close();
			    return s;
			}

		});

	}

}

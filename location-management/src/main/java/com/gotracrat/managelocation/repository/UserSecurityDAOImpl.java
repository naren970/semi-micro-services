package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.FindReplacementDTO;
import com.gotracrat.managelocation.dto.LocationNamesDTO;
import com.gotracrat.managelocation.dto.UserLevelsDTO;
import com.gotracrat.managelocation.dto.UserRolesDTO;
import com.gotracrat.managelocation.entity.AspnetUsers;
import com.gotracrat.managelocation.resource.ProfileResource;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserSecurityDAOImpl implements UserSecurityDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	public EntityManager em;

	@SuppressWarnings({"unchecked", "deprecation"})
	@Override
	public List<UserLevelsDTO> getLevels(String userName, Integer companyId) {
		return em.createNativeQuery(
				"EXEC spRoleGetLevelsByUserName  @UserName =:userName,@CompanyId =:companyId")
				.setParameter("userName", userName)
				.setParameter("companyId", companyId)
				.unwrap(org.hibernate.query.NativeQuery.class)
				.setResultTransformer(Transformers.aliasToBean(UserLevelsDTO.class)).getResultList();
	}

	@Override
	public List<LocationNamesDTO> getLocationNames(String userid, Integer companyid) {
		String sql = "select name,l.LocationID LocationID,l.ParentID ParentID from Location l inner join UserSecurity us on  l.LocationID=us.LocationID where us.UserID='"
				+ userid + "'and us.CompanyId='" + companyid + "'";

		return jdbcTemplate.query(sql, (rs, row) -> {
			LocationNamesDTO locationName = new LocationNamesDTO();
			locationName.setLocationid(rs.getInt("LocationID"));
			locationName.setParentid(rs.getInt("ParentID"));
			locationName.setLocationname(rs.getString("name"));
			jdbcTemplate.getDataSource().getConnection().close();
			return locationName;
		});
	}

	@Override
	public AspnetUsers getUserIDByUserName(String userName) {

		String sql = "SELECT * FROM aspnet_Users WHERE UserName = ?";
		AspnetUsers user = null;
		try {
			user = (AspnetUsers) jdbcTemplate.queryForObject(sql, new Object[] { userName }, new UserRowMapper());
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<ProfileResource> findAllBycompanyid(Integer companyid) throws SQLException {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("spProfileGetByCompanyId");
		SqlParameterSource in = new MapSqlParameterSource().addValue("CompanyID", companyid);
		List<ProfileResource> profilesList = new ArrayList<>();

			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List profiles = (List) out.get("#result-set-1");

			Stream<Map> profilesStream = profiles.stream().map(Map.class::cast);
			return profilesStream.map(rolesMap ->
				 ProfileResource.builder().userid((String) rolesMap.get("UserId"))
						 .profileid((Integer) rolesMap.get("ProfileId"))
						 .username((String) rolesMap.get("UserName"))
						 .email((String) rolesMap.get("Email"))
						 .firstname((String) rolesMap.get("FirstName"))
						 .lastname((String) rolesMap.get("LastName"))
						 .jobtitle((String) rolesMap.get("JobTitle"))
						 .department((String) rolesMap.get("Department"))
						 .phone((String) rolesMap.get("Phone"))
						 .mobilephone((String) rolesMap.get("MobilePhone"))
						 .fax((String) rolesMap.get("Fax"))
						 .isowneradmin((Boolean) rolesMap.get("IsOwnerAdmin")).build()).collect(Collectors.toList());
	}

	@Override
	public Integer getCountOfLocationIDByUserId(int companyid, String userid) {
		Integer locationCount = 0;
		String sql = "select COUNT(LOCATIONID) as locationcount from usersecurity where UserID = ? AND CompanyID=? AND LocationID=0";

		try {
			locationCount = jdbcTemplate.queryForObject(sql, new Object[] { userid, companyid }, Integer.class);
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locationCount;
	}

	@Override
	public List<ProfileResource> getUserProfilesAsAdmin(Integer companyid) throws SQLException {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("spProfileGetByCompanyIdAsOwnerAdmin");
		SqlParameterSource in = new MapSqlParameterSource().addValue("CompanyID", companyid);
		List<ProfileResource> profilesList = new ArrayList<>();

			Map<String, Object> out = jdbcCall.execute(in);
			jdbcTemplate.getDataSource().getConnection().close();
			List profiles = (List) out.get("#result-set-1");

			Stream<Map> profilesStream = profiles.stream().map(Map.class::cast);
			return profilesStream.map(rolesMap ->
					ProfileResource.builder().userid((String) rolesMap.get("UserId"))
							.profileid((Integer) rolesMap.get("ProfileId"))
							.username((String) rolesMap.get("UserName"))
							.email((String) rolesMap.get("Email"))
							.firstname((String) rolesMap.get("FirstName"))
							.lastname((String) rolesMap.get("LastName"))
							.jobtitle((String) rolesMap.get("JobTitle"))
							.department((String) rolesMap.get("Department"))
							.phone((String) rolesMap.get("Phone"))
							.mobilephone((String) rolesMap.get("MobilePhone"))
							.fax((String) rolesMap.get("Fax")).build()).collect(Collectors.toList());
		}

	@Override
	public int getRankForUserInAllLocations(Integer companyId, String userId) {

		Integer Rank = null;
		String sql = "SELECT RANK FROM  SECURITYROLERANK WHERE ROLEID=(SELECT ROLEID  FROM USERSECURITY WHERE USERID = ? AND CompanyID=? AND LocationID=0)";

		try {
			Rank = jdbcTemplate.queryForObject(sql, new Object[] { userId, companyId }, Integer.class);
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Rank;
	}

	public Integer getCountOfOtherLocationsByUserId(Integer companyId, String userId) {

		Integer locationCount = 0;
		String sql = "SELECT COUNT(*) FROM USERSECURITY WHERE   COMPANYID=? AND USERID=? AND  LOCATIONID!=0";

		try {
			locationCount = jdbcTemplate.queryForObject(sql, new Object[] { companyId, userId }, Integer.class);
			jdbcTemplate.getDataSource().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locationCount;
	}
}
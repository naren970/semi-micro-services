package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.ServicesDTO;
import com.gotracrat.managelocation.resource.InServiceVsSpareRequest;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReportsDAOImpl implements ReportsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	public EntityManager entityManager;

	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ServicesDTO> getServiceReports(Integer companyId, String startDate, String endDate) {
		return entityManager
				.createNativeQuery(
						"EXEC spServiceReport @CompanyID=:companyId,@StartDate =:startdate," + "@EndDate=:endDate")
				.setParameter("companyId", companyId).setParameter("startdate", startDate)
				.setParameter("endDate", endDate).unwrap(org.hibernate.query.NativeQuery.class)
				.setResultTransformer(Transformers.aliasToBean(ServicesDTO.class)).getResultList();
	}

	public Map<String, Object> getInServiceVsSpareReports(InServiceVsSpareRequest inServiceVsSpareRequest) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.INTEGER));
		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlParameter(Types.VARCHAR));
		return jdbcTemplate.call(connection -> {
			CallableStatement callableStatement = connection
					.prepareCall("{call Spinservicevssparereport(?,?,?,?)}");
			callableStatement.setInt(1, inServiceVsSpareRequest.getCompanyId());
			callableStatement.setString(2, inServiceVsSpareRequest.getHp());
			callableStatement.setString(3, inServiceVsSpareRequest.getRpm());
			callableStatement.setString(4, inServiceVsSpareRequest.getFrame());
			return callableStatement;
		}, paramList);

	}
}

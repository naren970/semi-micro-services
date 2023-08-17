package com.gotracrat.managecompany.repository;

import com.gotracrat.managecompany.entity.CompaniesView;
import com.gotracrat.managecompany.resource.CompanyResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveEntityTypeType(CompanyResource companyResource){

        List<SqlParameter> paramList = new ArrayList<SqlParameter>();
        paramList.add(new SqlParameter(Types.INTEGER));
        paramList.add(new SqlParameter(Types.INTEGER));
        paramList.add(new SqlParameter(Types.INTEGER));

        jdbcTemplate.call(new CallableStatementCreator() {

            @Override
            public CallableStatement createCallableStatement(Connection connection) throws SQLException {
                CallableStatement callableStatement = connection.prepareCall("{call spEntityTypeTypeSave(?, ?, ?)}");
                callableStatement.setInt(1, companyResource.getCompanyid());
                callableStatement.setInt(2, companyResource.getEntityTypeId());
                callableStatement.setInt(3, companyResource.getTypeId());
                return callableStatement;
            }
        }, paramList);

    }


    @Override
    public void deleteEntityTypeType(Integer companyid) {
        Integer count = 0;
        String sql = "SELECT COUNT(*) FROM  [ENTITYTYPE-TYPE]  WHERE  ENTITYTYPEID=1 AND ENTITYID= ?";
        try {
            count = jdbcTemplate.queryForObject(sql, new Object[]{companyid}, Integer.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (count > 0) {
            String sqlForDelete = "DELETE FROM  [ENTITYTYPE-TYPE]  WHERE  ENTITYTYPEID=1 AND ENTITYID=" + companyid
                    + "";

            int result = jdbcTemplate.update(sqlForDelete);
            if (result > 0) {
                this.deleteAttributeValues(companyid);
            }

        }

    }

    private void deleteAttributeValues(Integer companyid) {
        Integer count = 0;
        String sql = "SELECT COUNT(*) FROM  ATTRIBUTEVALUE  WHERE  ENTITYTYPEID=1 AND ENTITYID= ?";

        try {
            count = jdbcTemplate.queryForObject(sql, new Object[]{companyid}, Integer.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (count > 0) {
            String sqlForDelete = "DELETE FROM  ATTRIBUTEVALUE  WHERE  ENTITYTYPEID=1 AND ENTITYID=" + companyid + "";
            jdbcTemplate.update(sqlForDelete);
        }

    }


    @Override
	public int getType(Integer companyid) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("spTypeGetByCompanyID");
		SqlParameterSource in = new MapSqlParameterSource().addValue("CompanyID", companyid);
		int typeId = 0;

			Map<String, Object> out = jdbcCall.execute(in);
			List typeList = (List) out.get("#result-set-1");
			Iterator i = typeList.iterator();
			while (i.hasNext()) {
                Map typeMap = (Map) i.next();
                typeId = (Integer) typeMap.get("TypeID");
            }
		return typeId;
	}
    
    @Override
	public List<CompaniesView> getCompaniesByUser(String userid) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource())
				.withProcedureName("spCompanyGetByUserId");
		SqlParameterSource in = new MapSqlParameterSource().addValue("UserId", userid);
		List<CompaniesView> companiesList = new ArrayList<>();

		Map<String, Object> out = jdbcCall.execute(in);
		List companies = (List) out.get("#result-set-1");
		Iterator i = companies.iterator();
		
		while (i.hasNext()) {
			Map companiesMap = (Map) i.next();
			CompaniesView company = new CompaniesView();
			company.setCompanyid((Integer) companiesMap.get("CompanyID"));
			company.setName((String) companiesMap.get("Name"));
			company.setFilePath((String) companiesMap.get("filePath"));
			company.setCity((String) companiesMap.get("City"));
			company.setPhone((String) companiesMap.get("Phone"));
			companiesList.add(company);

		}
		return companiesList;
	}
}

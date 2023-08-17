package com.gotracrat.managelocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.UserLog;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, String>, JpaSpecificationExecutor<UserLog>  {
	
	public List<UserLog> findByuserNameAndCompanyIDOrderByLogDateDesc(String username,Integer companyId);

}

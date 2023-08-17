package com.gotracrat.attributesandtypes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.UserLog;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, String>, JpaSpecificationExecutor<UserLog>  {

}

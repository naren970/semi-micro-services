package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.UserRolesDTO;
import com.gotracrat.managelocation.entity.UsersecurityKey;
import com.gotracrat.managelocation.entity.VwUserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VwUserSecurityRepository extends JpaRepository<VwUserSecurity, UsersecurityKey>{

  List<VwUserSecurity> findByCompositePrimaryKeyUseridOrderByCompanyNameAscLocationNameAsc(String userid);

  @Query(value="Select new com.gotracrat.managelocation.dto.UserRolesDTO(u.roleName,u.rank) from VwUserSecurity u where u.userName = ?1 and u.compositePrimaryKey.companyid = ?2 order by u.roleName")
  List<UserRolesDTO> getRolesForALoggedInUser(String userName, Integer companyId);
}

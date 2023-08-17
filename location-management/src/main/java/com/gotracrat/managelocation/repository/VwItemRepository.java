package com.gotracrat.managelocation.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.Vwitem;

@Repository
public interface VwItemRepository extends JpaRepository<Vwitem, Integer>, JpaSpecificationExecutor<Vwitem> {

	@Query(value = "Select v.tag from Vwitem v where v.tag like %:tagName% and v.companyId = :companyId")
	 List<String> findSuggestions(@Param("tagName") String tagName, @Param("companyId") Integer companyId,
			Pageable pageable);
	 
}

package com.gotracrat.managelocation.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gotracrat.managelocation.entity.ItemService;

@Repository
public interface ItemServiceRepository extends JpaRepository<ItemService, Integer> {

	List<ItemService> findByitemId(Integer itemId);

	@Query(value="SELECT top 1 s.serviceDate FROM ItemService s where s.itemId=:itemId and s.serviceDate>getDate() and s.complete=0 order by s.serviceDate",nativeQuery = true)
	Date findServiceDateByItemId(@Param("itemId") Integer itemId);
}

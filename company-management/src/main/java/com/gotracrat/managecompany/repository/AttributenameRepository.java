package com.gotracrat.managecompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.managecompany.entity.Attributename;

/**
 * Jpa repository for Attributename.
 * @author prabhakar 
 * since 2018-06-18
 */
@Repository
public interface AttributenameRepository
		extends JpaRepository<Attributename, Integer>, JpaSpecificationExecutor<Attributename> {
	
	public List<Attributename> findByTypeTypeidOrderByDisplayorder(int typeId);
}
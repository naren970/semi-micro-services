/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:08 )
 *
*/
package com.gotracrat.attributesandtypes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.gotracrat.attributesandtypes.entity.Attributename;

/**
 * Jpa repository for Attributename.
 * @author prabhakar
 */
@Repository
public interface AttributenameRepository  extends JpaRepository<Attributename, Integer>, JpaSpecificationExecutor<Attributename> {
	
	public List<Attributename> findByTypeTypeidOrderByDisplayorder(int typeId);
}
/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:54 )
 * 
*/
package com.gotracrat.managecompany.repository;

import com.gotracrat.managecompany.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository for Announcement.
 * @author prabhakar
 */
@Repository
public interface AnnouncementRepository  extends JpaRepository<Announcement, Integer>, JpaSpecificationExecutor<Announcement> {}
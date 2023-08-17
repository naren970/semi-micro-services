package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Announcement Repository.
 *
 * @author anudeep
 * @since 22-12-2021
 */
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer>, JpaSpecificationExecutor<Announcement> {

    @Query(value="Select a.announcementtext from Announcement a where a.company.companyid in (-1, :companyId)")
    List<String> findByAnnouncementText(@Param("companyId")Integer companyId);
}

package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.dto.ChangeLogDTO;
import com.gotracrat.managelocation.entity.Journal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Integer>, JpaSpecificationExecutor<Journal> {


    @Query(value = "Select new com.gotracrat.managelocation.dto.ChangeLogDTO(j.journalid,j.entityid,j.entry,j.enteredby,j.enteredon) from Journal j where j.entityid = ?1 and j.entitytypeid= ?2 and j.journaltypeid = ?3 order by j.enteredon desc")
    List<ChangeLogDTO> getChangeLogData(Integer entityId, Integer entityTypeId, Integer journalTypeId, Pageable pageable);

}

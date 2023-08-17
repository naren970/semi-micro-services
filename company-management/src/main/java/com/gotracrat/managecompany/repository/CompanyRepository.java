package com.gotracrat.managecompany.repository;

import com.gotracrat.managecompany.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Jpa repository for Company.
 *
 * @author prabhakar
 * since 2018-05-25
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {

    public List<Company> findAllByVendor(boolean vendorFlag);

    public List<Company> findAllByVendorAndParentcompanyCompanyid(boolean vendorFlag, Integer companyid);

    @Query("Select c.filePath from Company c where c.companyid = ?1")
    public String findFilePathByCompanyId(Integer companyId);

    @Transactional
    @Procedure(procedureName = "spCompanyDelete")
    void deleteCompany(@Param("CompanyID") Integer companyId);

    @Query(value = "SELECT c.companyimageInBytes FROM Company c WHERE c.companyid = ?1")
    byte[] getLogo(Integer companyId);
    
    @Query(value ="select e.typeId from [EntityType-Type] e where e.EntityID=?1 and e.EntityTypeID=1", nativeQuery = true)
    public Integer getTypeId(Integer companyId);
    
    
    @Transactional
    @Procedure(procedureName = "spEntityTypeTypeSave")
    void saveEntityTypeType(@Param("EntityID") Integer companyId,@Param("EntityTypeID") Integer entityTypeID,@Param("TypeID") Integer typeID);
}

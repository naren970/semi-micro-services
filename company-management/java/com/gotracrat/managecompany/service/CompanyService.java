package com.gotracrat.managecompany.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.gotracrat.managecompany.resource.resource.CompanyDTO;
import com.gotracrat.managecompany.resource.resource.CompanyResource;
import com.gotracrat.managecompany.entity.CompaniesView;
import com.gotracrat.managecompany.entity.Company;

/* 
 * Service interface for Company.
 * @author prabhakar
 * since 2018-05-25
 */
public interface CompanyService {
    /**
     * Recover all companies.
     *
     * @return list of company
     */
    public List<CompaniesView> getAllCompanies();

    /**
     * Recover an company following an id.
     *
     * @param id the given id
     * @return the company
     */
    public CompanyResource get(Integer companyid) throws IOException;

    /**
     * Perform an company deletion.
     *
     * @param id the given id
     * @return state of deletion (true if ok otherwise false)
     */

    public  String delete(Integer companyid);
    /**
     * Perform an company creation.
     *
     * @param company to create
     * @return created company
     */
    public CompanyResource create(Optional<CompanyResource> companyResource) throws IOException;

    /**
     * Perform an company update.
     *
     * @param company to update
     * @return state of update (true if ok otherwise false)
     */
    public Boolean update(Optional<CompanyResource> companyResource) throws IOException,SQLException;

    /**
     * Test company existence.
     *
     * @param company to check
     * @return true if author exist otherwise false
     */
    public Boolean exist(Company company);
    
    public List<CompanyResource> getAllVendors(boolean vendorFlag, Integer companyid) throws IOException;
    
    public Resource loadFileAsResource(int companyID);

	public CompanyResource saveLogo(MultipartFile file, Integer companyId) throws IOException;

	List<CompanyDTO> getCompanies();
}

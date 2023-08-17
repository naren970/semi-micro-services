package com.gotracrat.managecompany.service;

import com.gotracrat.managecompany.entity.CompaniesView;
import com.gotracrat.managecompany.resource.CompanyDTO;
import com.gotracrat.managecompany.resource.CompanyLogoDTO;
import com.gotracrat.managecompany.resource.CompanyResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public CompanyResource get(Integer companyid) ;

    /**
     * Perform an company deletion.
     *
     * @param id the given id
     * @return state of deletion (true if ok otherwise false)
     */

    public  String  delete(Integer companyId);
    /**
     * Perform an company creation.
     *
     * @param company to create
     * @return created company
     */
    public CompanyResource create(CompanyResource companyResource);

    /**
     * Perform an company update.
     *
     * @param company to update
     * @return state of update (true if ok otherwise false)
     */
    public boolean update(CompanyResource companyResource);
    
    public List<CompanyResource> getAllVendors(boolean vendorFlag, Integer companyid) throws IOException;
    
    public Resource loadFileAsResource(int companyID);

	public void saveLogo(MultipartFile file, Integer companyId);

	List<CompanyDTO> getCompanies();

	public List<CompaniesView> getCompaniesByUser(String userid);

    CompanyLogoDTO getLogo(Integer companyId);
}

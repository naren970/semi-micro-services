package com.gotracrat.managecompany.service;

import com.gotracrat.managecompany.assemblers.CompanyToCompanyResourceAssembler;
import com.gotracrat.managecompany.entity.*;
import com.gotracrat.managecompany.exception.ResourceNotFoundException;
import com.gotracrat.managecompany.repository.*;
import com.gotracrat.managecompany.resource.*;
import com.gotracrat.managecompany.util.EntityTypeIDEnum;
import com.gotracrat.managecompany.util.FileUtils;
import com.gotracrat.managecompany.util.GoTracratContants;
import com.gotracrat.managecompany.util.GotracratUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

/*
 * Service implementation for Company
 * @author prabhakar
 * Since 2018-05-25
 */
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AttributevalueRepository attributeValueRepository;

    @Autowired
    private UsersecurityRepository usersecurityRepository;

    @Autowired
    private CompaniesViewRepository companiesViewRepository;

    private Path fileStorageLocation;

    @Override
    public List<CompaniesView> getAllCompanies() {
        return companiesViewRepository.findByOrderByName();
    }

    @Override
    public List<CompaniesView> getCompaniesByUser(String userid) {
        return companiesViewRepository.getCompaniesByUser(userid);

    }

    @Override
    public CompanyLogoDTO getLogo(Integer companyId) {
        return new CompanyLogoDTO(GotracratUtility.decompress(companyRepository.getLogo(companyId))) ;
    }

    @Override
    public List<CompanyDTO> getCompanies() {
        return companiesViewRepository.getAllCompanies();
    }

    @Override
    public CompanyResource get(Integer companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setState(company.getState().trim());
            CompanyResource companyResource = CompanyToCompanyResourceAssembler.companyResourceFromCompany(company);
            companyResource.setTypeId(companyRepository.getTypeId(company.getCompanyid()));
            List<Attributevalue> attributeValues = attributeValueRepository
                    .findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(company.getCompanyid(),
                            EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
            if (attributeValues != null && !attributeValues.isEmpty()) {
                List<AttributevalueResource> attributevalueResourceList = buildAttributevalueResources(attributeValues);
                companyResource.setAttributevalues(attributevalueResourceList);
            }
            return companyResource;
        }
        throw new ResourceNotFoundException(GoTracratContants.INVALID_COMPANY_ID + companyId);
    }


    @Override
    public CompanyResource create(CompanyResource companyResource) {
        Company actualCompany = getActualCompany(companyResource);
        Company company = companyRepository.save(actualCompany);
        if (company.getCompanyid() != null && company.getCompanyid() != 0) {
            saveUserSecurity(company.getCompanyid(), companyResource.getUserid());
            companyResource.setCompanyid(company.getCompanyid());
            companyResource.getAnnouncement().setAnnouncementdate(announcementRepository.findbycompanycompanyid(company.getCompanyid()));
        }
        return companyResource;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean update(CompanyResource companyResource) {
        if (isCompanyExists(companyResource.getCompanyid())) {
            Company company = getActualCompany(companyResource);
            companyRepository.save(company);
            if (companyResource.getTypeId() != null && companyResource.getTypeId() != 0) {
                companyRepository.saveEntityTypeType(companyResource.getCompanyid(),EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID(),companyResource.getTypeId());
            } else {
                companyDAO.deleteEntityTypeType(companyResource.getCompanyid());
            }
            Optional<List<AttributevalueResource>> attributeValueResources = Optional
                    .ofNullable(companyResource.getAttributevalues());
            if (attributeValueResources.isPresent()) {
                List<Attributevalue> attributeValues = buildAttributeValues(attributeValueResources.get(), company);
                if (!attributeValues.isEmpty()) {
                    attributeValueRepository.saveAll(attributeValues);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String delete(Integer companyId) {
        Optional<Company> company =companyRepository.findById(companyId);
        if (company.isPresent()) {
            companyRepository.deleteCompany(companyId);
            return GoTracratContants.COMPANY_DELETED;
        }
        throw new ResourceNotFoundException(GoTracratContants.INVALID_COMPANY_ID + companyId);
    }

    @Override
    public List<CompanyResource> getAllVendors(boolean vendorFlag, Integer companyId) throws IOException {
        List<Company> allByVendor = companyRepository.findAllByVendorAndParentcompanyCompanyid(vendorFlag, companyId);
        if (!CollectionUtils.isEmpty(allByVendor)) {
            List<CompanyResource> companyResources = new ArrayList<>(allByVendor.size());
            allByVendor.forEach(company -> {
                CompanyResource companyResource = new CompanyResource();
                BeanUtils.copyProperties(company, companyResource);
                if (companyResource.getParentcompanyResourceList() != null
                        && !companyResource.getParentcompanyResourceList().isEmpty()) {
                    companyResource.getParentcompanyResourceList().clear();
                }
                companyResources.add(companyResource);
            });
            return companyResources;
        }
        return Collections.emptyList();
    }

    private List<Attributevalue> buildAttributeValues(List<AttributevalueResource> attributeValueResources,
                                                      Company company) {
        List<Attributevalue> attributeValues = new ArrayList<>();
        attributeValueResources.forEach(attributeValueResource -> {
            Attributevalue attributeValue = new Attributevalue();
            BeanUtils.copyProperties(attributeValueResource, attributeValue);
            attributeValue.setEntityid(company.getCompanyid());
            attributeValue.setEntitytypeid(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
            AttributevalueKey compositePrimaryKey = new AttributevalueKey();
            Attributename attributeName = new Attributename();
            attributeName.setAttributenameid(attributeValueResource.getAttributename().getAttributenameid());
            compositePrimaryKey.setAttributeName(attributeName);
            compositePrimaryKey.setEntityid(company.getCompanyid());
            compositePrimaryKey.setEntitytypeid(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
            attributeValue.setCompositePrimaryKey(compositePrimaryKey);
            attributeValues.add(attributeValue);
        });
        return attributeValues;
    }

    @Override
    public void saveLogo(MultipartFile file, Integer companyId) {
            Company company = companyRepository.findById(companyId).orElseThrow(() ->
                    new ResourceNotFoundException(GoTracratContants.INVALID_COMPANY_ID));
            company.setCompanycontenttype(file.getContentType());
            company.setCompanyfilename(file.getOriginalFilename());
            this.fileStorageLocation = Paths.get(FileUtils.generateXlsFileDirectory()).toAbsolutePath().normalize();
            Path targetLocation = this.fileStorageLocation.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            try {
                Files.createDirectories(this.fileStorageLocation);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception exception) {
                log.info("error while saving logo", exception);
            }
            company.setFilePath(targetLocation.toAbsolutePath().toString());
            companyRepository.save(company);
        }


    @Override
    public Resource loadFileAsResource(int companyID) {
        String companyFilePath = companyRepository.findFilePathByCompanyId(companyID);
        if (null != companyFilePath) {
            try {
                this.fileStorageLocation = Paths.get(FileUtils.generateXlsFileDirectory()).toAbsolutePath().normalize();
                Path filePath = this.fileStorageLocation.resolve(companyFilePath).normalize();
                Resource resource = new UrlResource(filePath.toUri());
                if (resource.exists()) {
                    return resource;
                }
            } catch (Exception exception) {
                log.error("Error while loading files as resource", exception);
            }
        }
        return null;
    }


    private boolean isCompanyExists(Integer companyId) {
        return companyRepository.existsById(companyId);
    }


    /*
     * gives super admin role to the  user who created the company
     */
    private void saveUserSecurity(Integer companyId, String userId) {
        Usersecurity usersecurity = new Usersecurity();
        usersecurity.setRoleid(GoTracratContants.SUPER_ADMIN_ROLE_ID);
        usersecurity.setCompanyid(companyId);
        usersecurity.setLocationid(0);
        usersecurity.setUserid(userId);
        usersecurityRepository.save(usersecurity);
    }

    private Company getActualCompany(CompanyResource companyResource) {
        Company company = new Company();
        if (companyResource.getCompanyid() != null && companyResource.getCompanyid() != 0) {
            Optional<Company> companyOptional = companyRepository.findById(companyResource.getCompanyid());
            if (companyOptional.isPresent()) {
                company = companyOptional.get();
            }
        }
        BeanUtils.copyProperties(companyResource, company);
        AnnouncementResource announcementResource = companyResource.getAnnouncement();
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(announcementResource, announcement);
        companyResource.setEntityTypeId(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
        if (companyResource.getCompanyid() == null || companyResource.getCompanyid() == 0) {
            TypeResource typeResource = companyResource.getType();
            Type type = new Type();
            BeanUtils.copyProperties(typeResource, type);
            type.setEntitytypeid(EntityTypeIDEnum.USER_TYPE_ENTITYTYPE_ID.entityTypeID());
            type.setName(GoTracratContants.TYPE_NAME);
            type.setLastmodifiedby(GoTracratContants.TYPE_NAME);
            type.setDescription(GoTracratContants.TYPE_DECS);
        }
        announcement.setCompany(company);
        List<Announcement> announcements = new ArrayList<>(1);
        announcements.add(announcement);
        company.setAnnouncements(announcements);
        if (companyResource.getParentcompany() != null) {
            Company parentCompany = new Company();
            BeanUtils.copyProperties(companyResource.getParentcompany(), parentCompany);
            company.setParentcompany(parentCompany);
        }
        if (companyResource.getLogo() != null && !companyResource.getLogo().isEmpty()) {
            company.setCompanyimageInBytes(GotracratUtility.compress(companyResource.getLogo()));
        }
        return company;
    }

    private List<AttributevalueResource> buildAttributevalueResources(List<Attributevalue> attributevalues) {
        List<AttributevalueResource> attributevalueResourceList = new ArrayList<>();
        attributevalues.forEach(attributevalue -> {
            AttributevalueResource attributevalueResource = new AttributevalueResource();
            BeanUtils.copyProperties(attributevalue, attributevalueResource);
            attributevalueResource.setEntityid(attributevalue.getEntityid());
            attributevalueResource.setEntitytypeid(attributevalue.getEntitytypeid());
            AttributenameResource attributenameResource = new AttributenameResource();
            BeanUtils.copyProperties(attributevalue.getCompositePrimaryKey().getAttributeName(), attributenameResource);
            attributenameResource.setAttributenameid(
                    attributevalue.getCompositePrimaryKey().getAttributeName().getAttributenameid());
            Attributetype attributetype = attributevalue.getCompositePrimaryKey().getAttributeName().getAttributetype();
            AttributetypeResource attributetypeResource = new AttributetypeResource();
            BeanUtils.copyProperties(attributetype, attributetypeResource);
            attributenameResource.setAttributetype(attributetypeResource);
            Type type = attributevalue.getCompositePrimaryKey().getAttributeName().getType();
            TypeResource typeResource = new TypeResource();
            BeanUtils.copyProperties(type, typeResource);
            attributenameResource.setType(typeResource);
            attributevalueResource.setAttributename(attributenameResource);
            attributevalueResourceList.add(attributevalueResource);
        });
        return attributevalueResourceList;
    }

}

package com.gotracrat.managecompany.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gotracrat.managecompany.assemblers.CompanyToCompanyResourceAssembler;
import com.gotracrat.managecompany.resource.resource.AnnouncementResource;
import com.gotracrat.managecompany.resource.resource.AttributenameResource;
import com.gotracrat.managecompany.resource.resource.AttributetypeResource;
import com.gotracrat.managecompany.resource.resource.AttributevalueResource;
import com.gotracrat.managecompany.resource.resource.CompanyDTO;
import com.gotracrat.managecompany.resource.resource.CompanyResource;
import com.gotracrat.managecompany.resource.resource.TypeResource;
import com.gotracrat.managecompany.entity.Announcement;
import com.gotracrat.managecompany.entity.Attributename;
import com.gotracrat.managecompany.entity.Attributetype;
import com.gotracrat.managecompany.entity.Attributevalue;
import com.gotracrat.managecompany.entity.AttributevalueKey;
import com.gotracrat.managecompany.entity.CompaniesView;
import com.gotracrat.managecompany.entity.Company;
import com.gotracrat.managecompany.entity.Type;
import com.gotracrat.managecompany.entity.Usersecurity;
import com.gotracrat.managecompany.exception.ResourceNotFoundException;
import com.gotracrat.managecompany.repository.AttributevalueRepository;
import com.gotracrat.managecompany.repository.CompaniesViewRepository;
import com.gotracrat.managecompany.repository.CompanyDAO;
import com.gotracrat.managecompany.repository.CompanyRepository;
import com.gotracrat.managecompany.repository.UsersecurityRepository;
import com.gotracrat.managecompany.util.EntityTypeIDEnum;
import com.gotracrat.managecompany.util.FileUtils;
import com.gotracrat.managecompany.util.GoTracratContants;
import com.gotracrat.managecompany.util.GotracratUtility;

/*
 * Service implementation for Company
 * @author prabhakar
 * Since 2018-05-25
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	private CompanyDAO companyDAOImpl;

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
	public List<CompanyDTO> getCompanies() {
		return companiesViewRepository.getAllCompanies();
	}

	@Override
	public CompanyResource get(Integer companyId) throws IOException {
		Optional<Company> companyData = companyRepository.findById(companyId);
		if(!companyData.isPresent()) {
			throw new ResourceNotFoundException(GoTracratContants.INVALID_COMPANY_ID +companyId);
		}
		Company company =companyData.get();
		CompanyResource companyResource = CompanyToCompanyResourceAssembler.companyResourceFromCompany(company);
		int typeId = companyDAOImpl.getType(company.getCompanyid());
		companyResource.setTypeId(typeId);
		List<Attributevalue> attributevalues = attributeValueRepository
				.findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(company.getCompanyid(), EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
		if (attributevalues != null && !attributevalues.isEmpty()) {
			List<AttributevalueResource> attributevalueResourceList = buildAttributevalueResources(attributevalues);
			companyResource.setAttributevalues(attributevalueResourceList);
		}
		return companyResource;
	}

	@Override
	public String delete(Integer companyId) {
		if (companyRepository.findById(companyId) != null) {
			companyRepository.deleteCompany(companyId);
			return GoTracartContants.COMPANY_DELETED;
		}
		throw new ResourceNotFoundException(GoTracartContants.INVALID_COMPANY_ID + companyId);
	}

	@Override
	public CompanyResource create(Optional<CompanyResource> resource) throws IOException {
		CompanyResource companyResource=null;
		if(resource.isPresent()) {
			companyResource=resource.get();
		}
		Company actualCompany = getActualCompany(companyResource);
		Company company = companyRepository.save(actualCompany);
		Optional<List<AttributevalueResource>> attributeValueResources = Optional.ofNullable(companyResource.getAttributevalues());
		if (company.getCompanyid() != null && company.getCompanyid() != 0 && attributeValueResources != null
				&& attributeValueResources.isPresent()) {
			List<Attributevalue> attributeValues = buildAttributeValues(attributeValueResources, company);
			if (!attributeValues.isEmpty()) {
				attributeValueRepository.saveAll(attributeValues);
			}
		}
		saveUserSecurity(company.getCompanyid(), companyResource.getUserid());
		return buildCompanyResource(company, new CompanyResource());
	}

	private void saveUserSecurity(Integer companyId, String userId) {
		Usersecurity usersecurity = new Usersecurity();
		usersecurity.setRoleid(GoTracratContants.READ_ONLY);
		usersecurity.setCompanyid(companyId);
		usersecurity.setLocationid(0);
		usersecurity.setUserid(userId);
		usersecurityRepository.save(usersecurity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Boolean update(Optional<CompanyResource> resource) throws IOException, SQLException {
		CompanyResource companyResource = null;
		if(resource.isPresent()) {
		companyResource=resource.get();
		}
		if (companyRepository.findById(companyResource.getCompanyid()) != null) {
			Company company = getActualCompany(companyResource);
			companyRepository.save(company);
			if (companyResource.getTypeId() != null && companyResource.getTypeId() != 0) {
				companyDAOImpl.saveEntityTypeType(companyResource);
			} else {
				companyDAOImpl.deleteEntityTypeType(companyResource.getCompanyid());
			}
			Optional<List<AttributevalueResource>> attributeValueResources = Optional.ofNullable(companyResource.getAttributevalues());
			if (attributeValueResources != null && attributeValueResources.isPresent()) {
				List<Attributevalue> attributeValues = buildAttributeValues(attributeValueResources, company);
				if (!attributeValues.isEmpty()) {
					attributeValueRepository.saveAll(attributeValues);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public List<CompanyResource> getAllVendors(boolean vendorFlag, Integer companyId) throws IOException {
		List<Company> allByVendor = companyRepository.findAllByVendorAndParentcompanyCompanyid(vendorFlag, companyId);
		if (!CollectionUtils.isEmpty(allByVendor)) {
			List<CompanyResource> companyResources = new ArrayList<>(allByVendor.size());
			allByVendor.forEach(company -> {
				CompanyResource companyResource = new CompanyResource();
				BeanUtils.copyProperties(company, companyResource);
				byte[] companyImage = company.getCompanyimageInBytes();
				String decompress;
				try {
					decompress = GotracratUtility.decompress(companyImage);
					companyResource.setCompanyimage(decompress);
				} catch (IOException e) {
					e.printStackTrace();
				}
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

	private Company getActualCompany(CompanyResource companyResource) {
		Company company = companyResource.getCompanyid() != null && companyResource.getCompanyid() != 0
				? companyRepository.findById(companyResource.getCompanyid())
				: new Company();
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
		return company;
	}

	@Override
	public Boolean exist(Company company) {
		return false;
	}

	private List<Attributevalue> buildAttributeValues(Optional<List<AttributevalueResource>> attributes,
													  Company companyTemp) {
		List<AttributevalueResource> attributeValueResources = attributes.get();
		List<Attributevalue> attributeValues = new ArrayList<>();
		attributeValueResources.forEach(attributeValueResource -> {
			Attributevalue attributeValue = new Attributevalue();
			BeanUtils.copyProperties(attributeValueResource, attributeValue);
			attributeValue.setEntityid(companyTemp.getCompanyid());
			attributeValue.setEntitytypeid(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
			AttributevalueKey compositePrimaryKey = new AttributevalueKey();
			Attributename attributeName = new Attributename();
			attributeName.setAttributenameid(attributeValueResource.getAttributename().getAttributenameid());
			compositePrimaryKey.setAttributeName(attributeName);
			compositePrimaryKey.setEntityid(companyTemp.getCompanyid());
			compositePrimaryKey.setEntitytypeid(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
			attributeValue.setCompositePrimaryKey(compositePrimaryKey);
			attributeValues.add(attributeValue);
		});
		return attributeValues;
	}

	private CompanyResource buildCompanyResource(Company company, CompanyResource companyResource) {
		BeanUtils.copyProperties(company, companyResource);
		if (company.getParentcompany() != null) {
			CompanyResource parentCompany = new CompanyResource();
			BeanUtils.copyProperties(company.getParentcompany(), parentCompany);
			companyResource.setParentcompany(parentCompany);
		}
		return companyResource;
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

	@Override
	public CompanyResource saveLogo(MultipartFile file, Integer companyId) throws IOException {
		CompanyResource companyResource = new CompanyResource();
		Optional<Company> companyData = companyRepository.findById(companyId);
		if (companyData.isPresent()) {
			Company company =companyData.get();
			company.setCompanycontenttype(file.getContentType());
			company.setCompanyfilename(file.getOriginalFilename());
			company.setCompanyimageInBytes(file.getBytes());
			company.setCompanyimage(file.getBytes().toString());
			this.fileStorageLocation = Paths.get(FileUtils.generateXlsFileDirectory())
					.toAbsolutePath().normalize();
			Path targetLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());
			try {
				Files.createDirectories(this.fileStorageLocation);
			} catch (Exception exception) {
				logger.info("error while saving logo",exception);
			} 
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			company.setFilePath(targetLocation.toAbsolutePath().toString());
			Company created = companyRepository.save(company);
			BeanUtils.copyProperties(created, companyResource);
		} else {
			companyResource = null;
		}
		return companyResource;
	}

	@Override
	public Resource loadFileAsResource(int companyID) {
		this.fileStorageLocation = Paths.get(FileUtils.generateXlsFileDirectory()).toAbsolutePath().normalize();
		CompanyResource companyResource = null;
		try {
			//get only file path column instead of get company
			companyResource = get(companyID);
		} catch (IOException exception) {
			logger.error("Error while getting a resource",exception);
		}
		try {
			Files.createDirectories(this.fileStorageLocation);
			Path filePath = this.fileStorageLocation.resolve(companyResource.getFilePath()).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			}
		} catch (Exception exception) {
			logger.error("Error while loading files as resource",exception);
		}
		return null;
	}
}

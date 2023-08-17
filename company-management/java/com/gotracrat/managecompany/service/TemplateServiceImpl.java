package com.gotracrat.managecompany.service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.managecompany.resource.resource.AttributeXMl;
import com.gotracrat.managecompany.resource.resource.CompanyExport;
import com.gotracrat.managecompany.resource.resource.CompanyXml;
import com.gotracrat.managecompany.resource.resource.StatusXml;
import com.gotracrat.managecompany.resource.resource.TemplateDto;
import com.gotracrat.managecompany.resource.resource.TemplateResource;
import com.gotracrat.managecompany.resource.resource.TemplateWithOutXml;
import com.gotracrat.managecompany.resource.resource.TypeXml;
import com.gotracrat.managecompany.entity.Announcement;
import com.gotracrat.managecompany.entity.Attributelistitem;
import com.gotracrat.managecompany.entity.Attributename;
import com.gotracrat.managecompany.entity.Attributesearchtype;
import com.gotracrat.managecompany.entity.Attributetype;
import com.gotracrat.managecompany.entity.Company;
import com.gotracrat.managecompany.entity.Status;
import com.gotracrat.managecompany.entity.Template;
import com.gotracrat.managecompany.entity.Type;
import com.gotracrat.managecompany.entity.UserLog;
import com.gotracrat.managecompany.repository.AttributenameRepository;
import com.gotracrat.managecompany.repository.CompanyRepository;
import com.gotracrat.managecompany.repository.StatusRepository;
import com.gotracrat.managecompany.repository.TemplateDAO;
import com.gotracrat.managecompany.repository.TemplateRepository;
import com.gotracrat.managecompany.repository.TypeRepository;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private AttributenameRepository attributenameRepository;

	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private TemplateDAO templateDAO;

	@Autowired
	private TemplateRepository templateRepository;

	@Override
	public List<TemplateResource> getAllTemplateByCompanyId(Integer companyid) {
		List<Template> templateList = templateRepository.findBycompanyID(companyid);
		List<TemplateResource> templateResourceList = new ArrayList<>();
		if (templateList.size() > 0) {
			templateList.parallelStream().forEach(template -> {
				TemplateResource templateResource = new TemplateResource();
				BeanUtils.copyProperties(template, templateResource);
				templateResourceList.add(templateResource);
			});
		}
		return templateResourceList;
	}

	@Override
	public TemplateResource createTemplate(TemplateDto templateDto, UserLog userLog) throws SQLException {
		Template template = new Template();
		template.setName(templateDto.getTemplateName());
		template.setCompanyID(templateDto.getCompanyId());
		String xml = templateDAO.getXml(templateDto);
		template.setXml(xml);
		Template createdTemplate = templateRepository.save(template);
		TemplateResource templateResource = new TemplateResource();
		if (createdTemplate != null)
			BeanUtils.copyProperties(createdTemplate, templateResource);

		return templateResource;
	}

	@Override
	public String delete(Integer templateId, UserLog userLog) {
		Template template = new Template();
		if (templateId != null) {
			template = templateRepository.findOne(templateId);
			if (template != null) {
				templateRepository.delete(templateId);
				return GoTracratContants.TEMPLATE_DELETED;
			}
		}
		throw new ResourceNotFoundException(GoTracratContants.INVALID_TEMPLATE_ID + templateId)
	}

	@Override
	public Company createCompanyFromTemplate(TemplateDto templateDto) {
		Template template = templateRepository.findOne(templateDto.getTemplateId());
		String xmlString = template.getXml();
		Company createdCompany = new Company();
		if (xmlString != null) {
			try {

				JAXBContext jaxbContext = JAXBContext.newInstance(CompanyExport.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				CompanyExport companyExport = (CompanyExport) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
				if (companyExport != null) {
					CompanyXml company = companyExport.getCompanyXml();

					if (company != null) {
						createdCompany = createCompany(company, templateDto);
					}

					List<StatusXml> statusList = companyExport.getStatusList();
					if (createdCompany != null && statusList.size() > 0) {
						createStatus(statusList, createdCompany.getCompanyid());
					}
					List<TypeXml> typeList = companyExport.getTypeList();
					if (createdCompany != null && typeList.size() > 0) {
						createType(typeList, createdCompany.getCompanyid(), templateDto.getUserName(), 0);
					}
				}
				return createdCompany;
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return createdCompany;
	}

	private void createType(List<TypeXml> typeList, Integer companyid, String userName, Integer parentTypeId) {
		typeList.forEach(type -> {
			Type toBeSavedtype = new Type();
			// toBeSavedtype.setTypeid(0);
			toBeSavedtype.setName(type.getName());
			toBeSavedtype.setEntitytypeid(type.getEntityTypeId());
			Company company = new Company();
			company.setCompanyid(companyid);
			if (parentTypeId != 0) {
				Type parentType = new Type();
				/*
				 * if (typeResource.getParentid().getTypeid() == null) {
				 * parentType.getType().setTypeid(0); }
				 */

				parentType.setTypeid(parentTypeId);
				toBeSavedtype.setType(parentType);
			}
			toBeSavedtype.setCompany(company);
			toBeSavedtype.setLastmodifiedby(userName);
			toBeSavedtype.setIshidden(false);
			toBeSavedtype.setHostingfee(new BigDecimal(0));
			Type createdType = typeRepository.save(toBeSavedtype);
			if (createdType != null && type.getAttributeList() != null) {
				List<AttributeXMl> attributeList = type.getAttributeList();
				saveAttributes(attributeList, createdType.getTypeid());
			}

			if (createdType != null && type.getChildTypeList() != null) {
				List<TypeXml> childTypeList = type.getChildTypeList();
				createType(childTypeList, companyid, userName, createdType.getTypeid());
			}

		});

	}

	private void saveAttributes(List<AttributeXMl> attributeList, Integer typeID) {
		List<Attributename> tobeSavedAttributeNameList = new ArrayList<>();
		attributeList.forEach(attribute -> {

			Attributename attributename = new Attributename();
			attributename.setAttributenameid(0);
			attributename.setName(attribute.getName());
			attributename.setIsrequired(attribute.getIsRequired());
			attributename.setTooltip(attribute.getTooltip());
			attributename.setSearchmodifier(attribute.getSearchModifier());
			attributename.setDisplayorder(attribute.getDisplayOrder());
			attributename.setIsrequiredformatch(attribute.getIsRequiredForMatch());
			attributename.setIsmanufacturer(attribute.getIsManufacturer());

			Type type = new Type();
			type.setTypeid(typeID);
			attributename.setType(type);

			Attributetype attributetype = new Attributetype();
			attributetype.setAttributetypeid(attribute.getAttributeTypeId());
			attributename.setAttributetype(attributetype);

			Attributesearchtype attributesearchtype = new Attributesearchtype();
			attributesearchtype.setAttributesearchtypeid(attribute.getAttributeSearchTypeId());
			attributename.setSearchtype(attributesearchtype);

			if (attribute.getAttributeTypeId().equals(4)) {
				List<Attributelistitem> attributelistitemList = new ArrayList<>();
				Optional<Attributename> oldAttribute = attributenameRepository
						.findById(attribute.getAttributeNameId());
				if (oldAttribute.isPresent()) {
					Attributename oldAttributeName =oldAttribute.get();
					List<Attributelistitem> attributeNameForList = oldAttributeName.getAttributelistitem();
					for (Attributelistitem attributelistitem : attributeNameForList) {
						Attributelistitem attributelistitemNew = new Attributelistitem();
						// attributelistitemNew.setAttributelistvaluesid(null);
						attributelistitemNew.setListitem(attributelistitem.getListitem());
						attributelistitemNew.setListitembk(attributelistitem.getListitembk());
						attributelistitemNew.setAttributename(attributename);
						attributelistitemList.add(attributelistitemNew);
					}
				}
				attributename.setAttributelistitem(attributelistitemList);
			}

			tobeSavedAttributeNameList.add(attributename);
			/*
			 * if (attributenameResource.getAttributelistitemResource() != null) {
			 * List<Attributelistitem> attributelistitemList = new ArrayList<>();
			 * 
			 * for (AttributelistitemResource attributelistitemResource :
			 * attributenameResource .getAttributelistitemResource()) { Attributelistitem
			 * attributelistitem = new Attributelistitem();
			 * BeanUtils.copyProperties(attributelistitemResource, attributelistitem);
			 * attributelistitem.setAttributename(attributename);
			 * attributelistitemList.add(attributelistitem); }
			 * attributename.setAttributelistitem(attributelistitemList); }
			 */

		});

		if (tobeSavedAttributeNameList != null && tobeSavedAttributeNameList.size() > 0) {
			attributenameRepository.saveAll(tobeSavedAttributeNameList);
		}

	}

	private void createStatus(List<StatusXml> statusList, Integer companyid) {
		List<Status> toBeSavedStatusList = new ArrayList<>();
		statusList.forEach(status -> {
			Status toBeSavedStatus = new Status();
			// toBeSavedStatus.setStatusid(0);
			toBeSavedStatus.setEntitytypeid(status.getEntityTypeId());
			toBeSavedStatus.setStatus(status.getStatus());
			toBeSavedStatus.setInservice(status.getInService());
			toBeSavedStatus.setUnderrepair(status.getUnderRepair());
			toBeSavedStatus.setSpare(status.getSpare());
			toBeSavedStatus.setDestroyed(status.getDestroyed());

			Company company = new Company();
			company.setCompanyid(companyid);
			toBeSavedStatus.setCompany(company);

			toBeSavedStatusList.add(toBeSavedStatus);
		});
		if (toBeSavedStatusList.size() > 0) {
			statusRepository.saveAll(toBeSavedStatusList);
		}
	}

	private Company createCompany(CompanyXml companyXml, TemplateDto templateDto) {
		Company company = new Company();
		company.setName(templateDto.getCompanyName());
		company.setAddress1(companyXml.getAddress1());
		company.setAddress2(companyXml.getAddress2());
		company.setIssandbox(false);
		company.setSupplylevelwarning(false);
		company.setLastmodifiedby(templateDto.getUserName());
		company.setVendor(false);
		Announcement announcement = new Announcement();
		announcement.setAnnouncementtext("");
		announcement.setAnnouncementdate(new Date());
		announcement.setCompany(company);
		List<Announcement> announcements = new ArrayList<>(1);
		announcements.add(announcement);
		company.setAnnouncements(announcements);

		Company createdCompany = companyRepository.save(company);

		return createdCompany;

	}

	@Override
	public List<TemplateWithOutXml> getAllTemplateWithOutXml(Integer companyId) {
		return templateRepository.getAllTemplatesWithOutXMl(companyId);
	}
}

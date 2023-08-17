/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:09 )
 *
 */
package com.gotracrat.attributesandtypes.service;

import java.util.*;

import com.gotracrat.attributesandtypes.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gotracrat.attributesandtypes.controller.resource.CompanyResource;
import com.gotracrat.attributesandtypes.controller.resource.TypeDTO;
import com.gotracrat.attributesandtypes.controller.resource.TypeResource;
import com.gotracrat.attributesandtypes.entity.Company;
import com.gotracrat.attributesandtypes.entity.Type;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.TypeDAO;
import com.gotracrat.attributesandtypes.repository.TypeRepository;
import com.gotracrat.attributesandtypes.utils.EntityTypeIDEnum;
import com.gotracrat.attributesandtypes.utils.GoTracratConstants;

/**
 * Service implementation for Type.
 *
 * @author prabhakar
 */
@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private TypeDAO typeDAO;



	@Override
	public TypeResource get(Integer typeId) {
		
		Optional<Type> optionalType= typeRepository.findById(typeId);
		if(optionalType.isPresent()) {
			Type type=optionalType.get();
			TypeResource typeResource = new TypeResource();
			BeanUtils.copyProperties(type, typeResource);
			
			CompanyResource companyResource = new CompanyResource();
			BeanUtils.copyProperties(type.getCompany(), companyResource);
			if(type.getType() != null ) {
				TypeResource parenttypeResource = new TypeResource();
				BeanUtils.copyProperties(type.getType(), parenttypeResource);
				if(parenttypeResource.getTypeList() != null && !parenttypeResource.getTypeList().isEmpty()) {
					parenttypeResource.getTypeList().clear();
				}
				typeResource.setParentid(parenttypeResource);
			}
			typeResource.setCompany(companyResource);
			return typeResource;
		}
		
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean delete(Type type,UserLog userLog) {
		if (type != null) {
			return typeDAO.delete(type.getTypeid());
		}
		userLog.setCanNotInsert(true);
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Type create(TypeResource typeResource,UserLog userLog) {
		Type type = buildTypeForCreate(typeResource);
		if (type != null) {
			return typeRepository.save(type);
		}
		userLog.setCanNotInsert(true);
		return null;
	}

	@Override
	@Transactional//(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean save(TypeResource typeResource,UserLog userLog) {
		final Integer pk = typeResource.getTypeid();
		
		 Type type = typeRepository.findById(pk).orElseThrow(() ->
				 new ResourceNotFoundException(GoTracratConstants.USER_NOT_FOUND));
		 if(null != type.getTypeid()) {
		 Type type1 =updateType(type,typeResource);
			 typeRepository.save(type1);
			 return true;
		 }
		 userLog.setCanNotInsert(true);
		return false;
	}

	private Type updateType(Type type, TypeResource typeResource) {
			 if(null!=typeResource.getAttributesearchdisplay()) {
				 type.setAttributesearchdisplay(typeResource.getAttributesearchdisplay()); 
			 }
			 if(null!=typeResource.getDescription()) {
				 type.setDescription(typeResource.getDescription()); 
			 }
			 if(null!=typeResource.getName()) {
				 type.setName(typeResource.getName()); 
			 }
			 if(null!=typeResource.getHostingfee()) {
				 type.setHostingfee(typeResource.getHostingfee()); 
			 }
			if (!typeResource.getModuleType().isEmpty()) {
				if (typeResource.getModuleType().equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
					type.setEntitytypeid(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
				} else if (typeResource.getModuleType().equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
					type.setEntitytypeid(EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID());
				} else if (typeResource.getModuleType().equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
					type.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());
				} else if (typeResource.getModuleType().equalsIgnoreCase(GoTracratConstants.USER_MODULE_TYPE)) {
					type.setEntitytypeid(EntityTypeIDEnum.USER_TYPE_ENTITYTYPE_ID.entityTypeID());
				}
			}
			 if(null!=typeResource.getParentid() && null!= typeResource.getParentid().getTypeid() &&
					 typeResource.getParentid().getTypeid()!=0) {
				 Type parentType = new Type();
					parentType.setTypeid(typeResource.getParentid().getTypeid());
					type.setType(parentType);
			 }
			 if(null!=typeResource.getTypespareratio()) {
				 type.setTypespareratio(typeResource.getTypespareratio()); 
			 }
			 if(null!=typeResource.getTypemtbs()) {
				 type.setTypemtbs(typeResource.getTypemtbs()); 
			 }
			 type.setLastmodifiedby(typeResource.getLastmodifiedby());
			 return type;
	}

	@Override
	public boolean exist(Type type) {
		return typeRepository.existsById(type.getTypeid());
	}

	@Override
	public List<TypeDTO> getAllTypes(Integer companyId, String moduleType) {
		if (companyId != null && companyId != 0 && !moduleType.isEmpty()) {
			Integer entityTypeId = getEntityTypeId(moduleType);
			return typeRepository.getTypesByCompanyidAndEntitytypeid(companyId, entityTypeId);
		}
		return Collections.emptyList();
	}

	@Override
	public List<TypeResource> getAllTypeWithHierarchy(Integer companyId, String moduleType) {
		if (companyId != null && companyId != 0 && !moduleType.isEmpty()) {
			Integer entityTypeId = getEntityTypeId(moduleType);
			List<Type> types = typeRepository.findByCompanyCompanyidAndEntitytypeid(companyId, entityTypeId);
			List<TypeResource> typeResources = new ArrayList<>();
			types.forEach(type -> {
				if (type.getType() == null) {
					TypeResource typeResource = new TypeResource();
					BeanUtils.copyProperties(type, typeResource);
					CompanyResource companyResource = new CompanyResource();
					BeanUtils.copyProperties(type.getCompany(), companyResource);
					typeResource.setCompany(companyResource);
					typeResources.add(typeResource);
				}
			});
			return typeResources;
		}
		return Collections.emptyList();
	}

	private Type buildTypeForCreate(TypeResource typeResource) {
		if (typeResource.getCompany().getCompanyid() != null && typeResource.getCompany().getCompanyid() != 0 ) {
			Type type = new Type();
			BeanUtils.copyProperties(typeResource, type);
			Company company = new Company();
			company.setCompanyid(typeResource.getCompany().getCompanyid());
			type.setCompany(company);
			Type parentType = new Type();
			if (typeResource.getParentid().getTypeid() == null) {
				parentType.getType().setTypeid(0);
			}

			parentType.setTypeid(typeResource.getParentid().getTypeid());
			type.setType(parentType);

			if (!typeResource.getModuleType().isEmpty()) {
				if (typeResource.getModuleType().equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
					type.setEntitytypeid(EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID());
				} else if (typeResource.getModuleType().equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
					type.setEntitytypeid(EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID());
				} else if (typeResource.getModuleType().equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
					type.setEntitytypeid(EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());
				}else if (typeResource.getModuleType().equalsIgnoreCase(GoTracratConstants.USER_MODULE_TYPE)) {
					type.setEntitytypeid(EntityTypeIDEnum.USER_TYPE_ENTITYTYPE_ID.entityTypeID());
				}
				else {
					return null;
				}
			} else {
				return null;
			}
			return type;
		} else {
			return null;
		}
	}

	@Override
	public Map<String, Integer> getAttributesForSearchDisplay(Integer companyId) {
		 return  typeDAO.getAttributesForSearchDisplay(companyId);
	}


	private Integer getEntityTypeId(String moduleType) {
		Integer entityTypeId = null;
		if (moduleType.equalsIgnoreCase(GoTracratConstants.COMPANY_MODULE_TYPE)) {
			entityTypeId = EntityTypeIDEnum.COMPANY_TYPE_ENTITYTYPE_ID.entityTypeID();
		} else if (moduleType.equalsIgnoreCase(GoTracratConstants.LOCATION_MODULE_TYPE)) {
			entityTypeId = EntityTypeIDEnum.LOCATION_TYPE_ENTITYTYPE_ID.entityTypeID();
		}else if (moduleType.equalsIgnoreCase(GoTracratConstants.ITEM_MODULE_TYPE)) {
			entityTypeId = EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID();
		} else if (moduleType.equalsIgnoreCase(GoTracratConstants.USER_MODULE_TYPE)) {
			entityTypeId = EntityTypeIDEnum.USER_TYPE_ENTITYTYPE_ID.entityTypeID();
		}
		return entityTypeId;
	}

	
	
	
}

/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:08 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gotracrat.attributesandtypes.exception.ResourceNotFoundException;
import com.gotracrat.attributesandtypes.utils.GoTracratConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gotracrat.attributesandtypes.controller.resource.AttributeValueResource;
import com.gotracrat.attributesandtypes.controller.resource.AttributelistitemResource;
import com.gotracrat.attributesandtypes.controller.resource.AttributenameResource;
import com.gotracrat.attributesandtypes.controller.resource.AttributesearchtypeResource;
import com.gotracrat.attributesandtypes.controller.resource.AttributetypeResource;
import com.gotracrat.attributesandtypes.controller.resource.TypeResource;
import com.gotracrat.attributesandtypes.entity.Attributelistitem;
import com.gotracrat.attributesandtypes.entity.Attributename;
import com.gotracrat.attributesandtypes.entity.Attributesearchtype;
import com.gotracrat.attributesandtypes.entity.Attributetype;
import com.gotracrat.attributesandtypes.entity.Attributevalue;
import com.gotracrat.attributesandtypes.entity.Type;
import com.gotracrat.attributesandtypes.entity.UserLog;
import com.gotracrat.attributesandtypes.repository.AttributeDAO;
import com.gotracrat.attributesandtypes.repository.AttributeValueRepository;
import com.gotracrat.attributesandtypes.repository.AttributelistitemRepository;
import com.gotracrat.attributesandtypes.repository.AttributenameRepository;
import com.gotracrat.attributesandtypes.utils.EntityTypeIDEnum;

/**
 * Service implementation for Attributename.
 * 
 * @author prabhakar
 */
@Service
public class AttributenameServiceImpl implements AttributenameService {

	@Autowired
	private AttributenameRepository attributenameRepository;

	@Autowired
	private AttributelistitemRepository attributelistitemRepository;

	@Autowired
	private AttributeDAO attributeDAOImpl;
	
	@Autowired
	private AttributeValueRepository attributeValueRepository;

	@Override
	public AttributenameResource getAttribute(Integer attributenameid) {
		Optional<Attributename> attributeNameOptional = attributenameRepository.findById(attributenameid);
		Attributename attributename = null;
		if (attributeNameOptional.isPresent()) {
			attributename = attributeNameOptional.get();
		}
		return buildAttributeNameResource(attributename);
	}
    
	@Override
	public Map<Integer, List<AttributenameResource>> getItemTypeAndAttributes(Integer companyId) {
		Map<Integer, List<AttributenameResource>> typeAttributeMap = new HashMap<>();
		List<Integer> typeIds = attributeDAOImpl.getTypeIdsByCompanyId(companyId);
		typeIds.stream().forEach(typeId -> {
			List<AttributenameResource> attributeNameResourceList = getAllAttributesByTypeId(typeId);
			typeAttributeMap.put(typeId, attributeNameResourceList);
		});
		return typeAttributeMap;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String delete(Integer attributenameid, UserLog userLog) {
		if (attributenameRepository.findById(attributenameid).isPresent()) {
			attributeDAOImpl.delete(attributenameid);
			return GoTracratConstants.ATTRIBUTE_DELETED;
		}
		throw new ResourceNotFoundException(GoTracratConstants.INVALID_ATTRIBUTE_NAME_ID + attributenameid);
	}
	@Override
	// @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public AttributenameResource createAttribute(AttributenameResource attributenameResource, UserLog userLog) {

		Attributename attribute = attributenameRepository.save(this.buildAttributeName(attributenameResource));
		return this.buildAttributeNameResource(attribute);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Boolean saveAttribute(AttributenameResource attributenameResource, UserLog userLog) {
		final Integer pk = attributenameResource.getAttributenameid();
		if (attributenameRepository.findById(pk).isPresent()) {
			Attributename attributename = this.buildAttributeName(attributenameResource);
			attributelistitemRepository.deleteAllByAttributenameAttributenameid(attributename.getAttributenameid());
			attributenameRepository.save(attributename);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(AttributenameResource attributenameResource) {
		return attributenameRepository.existsById(attributenameResource.getAttributenameid());
	}
    
	@Override
	public List<AttributenameResource> getAllAttributesByTypeId(Integer typeId) {
		List<Attributename> attributenameList = attributenameRepository.findByTypeTypeidOrderByDisplayorder(typeId);
		List<AttributenameResource> attributenameResourcelist = new ArrayList<>();
		attributenameList.forEach(attributename -> {
			AttributenameResource attributenameResource = buildAttributeNameResource(attributename);
			attributenameResourcelist.add(attributenameResource);
		});
		return attributenameResourcelist;
	}

	@Override
	public List<AttributenameResource> updateAttributeOrder(List<AttributenameResource> attributenamelist) {
		return attributeDAOImpl.updateAttributeOrder(attributenamelist);
	}

	private Attributename buildAttributeName(AttributenameResource attributenameResource) {
		Attributename attributename = new Attributename();
		BeanUtils.copyProperties(attributenameResource, attributename);

		Type type = new Type();
		BeanUtils.copyProperties(attributenameResource.getType(), type);
		attributename.setType(type);

		Attributetype attributetype = new Attributetype();
		BeanUtils.copyProperties(attributenameResource.getAttributetype(), attributetype);
		attributename.setAttributetype(attributetype);

		if (attributenameResource.getSearchtype().getAttributesearchtypeid() != null) {
			Attributesearchtype attributesearchtype = new Attributesearchtype();
			BeanUtils.copyProperties(attributenameResource.getSearchtype(), attributesearchtype);
			attributename.setSearchtype(attributesearchtype);
		}

		if (attributenameResource.getAttributelistitemResource() != null) {
			List<Attributelistitem> attributelistitemList = new ArrayList<>();

			attributenameResource.getAttributelistitemResource().forEach(attributelistitemResource -> {
				Attributelistitem attributelistitem = new Attributelistitem();
				BeanUtils.copyProperties(attributelistitemResource, attributelistitem);
				attributelistitem.setAttributename(attributename);
				attributelistitemList.add(attributelistitem);
			});
			attributename.setAttributelistitem(attributelistitemList);
			}
			return attributename;
		}

	private AttributenameResource buildAttributeNameResource(Attributename attribute) {

		AttributenameResource attributenameResource = null;
		if (attribute == null) {
			return attributenameResource;
		}
		attributenameResource = new AttributenameResource();
		BeanUtils.copyProperties(attribute, attributenameResource);

		TypeResource typeResource = new TypeResource();
		BeanUtils.copyProperties(attribute.getType(), typeResource);
		attributenameResource.setType(typeResource);

		AttributetypeResource attributetypeResource = new AttributetypeResource();
		BeanUtils.copyProperties(attribute.getAttributetype(), attributetypeResource);
		attributenameResource.setAttributetype(attributetypeResource);

		AttributesearchtypeResource attributesearchtypeResource = new AttributesearchtypeResource();
		BeanUtils.copyProperties(attribute.getSearchtype(), attributesearchtypeResource);
		attributenameResource.setSearchtype(attributesearchtypeResource);

		if (attribute.getAttributelistitem() != null) {
			List<AttributelistitemResource> attributelistitemList = new ArrayList<>();
			attribute.getAttributelistitem().forEach(attributelistitem -> {
				AttributelistitemResource attributelistitemResource = new AttributelistitemResource();
				BeanUtils.copyProperties(attributelistitem, attributelistitemResource);
				attributelistitemList.add(attributelistitemResource);
			});
			attributenameResource.setAttributelistitemResource(attributelistitemList.stream()
			        .sorted(Comparator.comparing(AttributelistitemResource::getListitem))
			        .collect(Collectors.toList()));
		}

		return attributenameResource;
	}

	@Override
	public List<AttributeValueResource> getAllAttributesByEntityId(Integer entityId, Integer entityTypeId) {
		List<Attributevalue> attributevalueList = attributeValueRepository
				.findByCompositePrimaryKeyEntityidAndCompositePrimaryKeyEntitytypeid(entityId,
						EntityTypeIDEnum.ITEM_TYPE_ENTITYTYPE_ID.entityTypeID());
		if (attributevalueList != null && !attributevalueList.isEmpty()) {
			return buildAttributevalueResources(attributevalueList);
		}
		return Collections.emptyList();
	}

	private List<AttributeValueResource> buildAttributevalueResources(List<Attributevalue> attributeValues) {
		List<AttributeValueResource> attributevalueResourceList = new ArrayList<>();
		attributeValues.parallelStream().forEach(attributevalue -> {
			AttributeValueResource attributevalueResource = new AttributeValueResource();
			BeanUtils.copyProperties(attributevalue, attributevalueResource);
			attributevalueResource.setEntityid(attributevalue.getEntityid());
			attributevalueResource.setEntitytypeid(attributevalue.getEntitytypeid());
			AttributenameResource attributenameResource = new AttributenameResource();
			Attributename attributeName = attributevalue.getCompositePrimaryKey().getAttributename();
			BeanUtils.copyProperties(attributeName, attributenameResource);
			attributenameResource.setAttributenameid(attributeName.getAttributenameid());
			if (attributeName.getAttributelistitem() != null) {
				List<AttributelistitemResource> attributelistitemList = new ArrayList<>();
				attributeName.getAttributelistitem().forEach(attributelistitem -> {
					AttributelistitemResource attributelistitemResource = new AttributelistitemResource();
					BeanUtils.copyProperties(attributelistitem, attributelistitemResource);
					attributelistitemList.add(attributelistitemResource);
				});
				attributenameResource.setAttributelistitemResource(attributelistitemList);
			}
			Attributetype attributetype = attributevalue.getCompositePrimaryKey().getAttributename().getAttributetype();
			AttributetypeResource attributetypeResource = new AttributetypeResource();
			BeanUtils.copyProperties(attributetype, attributetypeResource);
			attributenameResource.setAttributetype(attributetypeResource);
			Type type = attributevalue.getCompositePrimaryKey().getAttributename().getType();
			TypeResource typeResource = new TypeResource();
			BeanUtils.copyProperties(type, typeResource);
			attributenameResource.setType(typeResource);
			attributevalueResource.setAttributename(attributenameResource);
			attributevalueResourceList.add(attributevalueResource);
		});
		return attributevalueResourceList;		
	}

}

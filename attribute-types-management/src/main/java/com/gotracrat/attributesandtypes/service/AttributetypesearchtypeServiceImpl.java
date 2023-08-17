/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:09 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import com.gotracrat.attributesandtypes.controller.resource.AttributesearchtypeResource;
import com.gotracrat.attributesandtypes.entity.Attributesearchtype;
import com.gotracrat.attributesandtypes.entity.Attributetypesearchtype;
import com.gotracrat.attributesandtypes.entity.AttributetypesearchtypeKey;
import com.gotracrat.attributesandtypes.repository.AttributetypesearchtypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for Attributetypesearchtype.
 * 
 * @author prabhakar
 */
@Service
public class AttributetypesearchtypeServiceImpl implements AttributetypesearchtypeService {

	@Autowired
	private AttributetypesearchtypeRepository attributetypesearchtypeRepository;

	@Override
	public Page<Attributetypesearchtype> search(Pageable pageable) {
		return attributetypesearchtypeRepository.findAll(pageable);
	}

	@Override
	public List<AttributesearchtypeResource> getAllAttributeSearchType(Integer attributetypeid) {
		final List<Attributetypesearchtype> attributetypeResourceList = attributetypesearchtypeRepository
				.findByCompositePrimaryKeyAttributetypeidAttributetypeid(attributetypeid);
		final List<AttributesearchtypeResource> attributesearchtypeList = new ArrayList<>();
		attributetypeResourceList.forEach(attributetypesearchtype -> {
			AttributesearchtypeResource attributesearchtypeResource = new AttributesearchtypeResource();
			BeanUtils.copyProperties(attributetypesearchtype.getAttributesearchtypeid(), attributesearchtypeResource);
			attributesearchtypeList.add(attributesearchtypeResource);
		});
		return attributesearchtypeList;
	}

	@Override
	public Optional<Attributetypesearchtype> get(Integer attributetypeid, Integer attributesearchtypeid) {
		AttributetypesearchtypeKey key = new AttributetypesearchtypeKey(attributetypeid, attributesearchtypeid);
		return attributetypesearchtypeRepository.findById(key);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Boolean delete(Integer attributetypeid, Integer attributesearchtypeid) {
		// Build the composite key
		AttributetypesearchtypeKey key = new AttributetypesearchtypeKey(attributetypeid, attributesearchtypeid);
		if (attributetypesearchtypeRepository.findById(key).isPresent()) {
			attributetypesearchtypeRepository.deleteById(key);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Attributetypesearchtype create(Attributetypesearchtype attributetypesearchtype) {
		return attributetypesearchtypeRepository.save(attributetypesearchtype);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Boolean save(Attributetypesearchtype attributetypesearchtype) {
		return false;
	}

	@Override
	public Boolean exist(Attributetypesearchtype attributetypesearchtype) {
		return false;
	}

}

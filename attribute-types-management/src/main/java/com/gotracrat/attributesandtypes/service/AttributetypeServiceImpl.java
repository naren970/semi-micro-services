/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:09 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gotracrat.attributesandtypes.controller.resource.AttributetypeResource;
import com.gotracrat.attributesandtypes.entity.Attributetype;
import com.gotracrat.attributesandtypes.repository.AttributetypeRepository;

/**
 * Service implementation for Attributetype.
 * @author prabhakar
 */
@Service
public class AttributetypeServiceImpl implements AttributetypeService {

	@Autowired
	private AttributetypeRepository attributetypeRepository;

	@Override
	public List<AttributetypeResource> getAllAttributetypes() {
		List<Attributetype> attributetypeList= attributetypeRepository.findAll();
		List<AttributetypeResource> attributetypeResourcesList = new ArrayList<>();
			attributetypeList.forEach(attributetype -> {
			AttributetypeResource attributetypeResource = new AttributetypeResource();
			BeanUtils.copyProperties(attributetype, attributetypeResource);
			attributetypeResourcesList.add(attributetypeResource);
		});
		return attributetypeResourcesList;
	}
	@Override
	public Optional<Attributetype> get(Integer attributetypeid) {
		return attributetypeRepository.findById(attributetypeid);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Boolean delete(Integer attributetypeid) {
		if (attributetypeRepository.findById(attributetypeid).isPresent()) {
			attributetypeRepository.deleteById(attributetypeid);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Attributetype create(Attributetype attributetype) {
		return attributetypeRepository.save(attributetype);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Boolean save(Attributetype attributetype) {
		final Integer pk = attributetype.getAttributetypeid();
		if (attributetypeRepository.findById(pk).isPresent()) {
			attributetypeRepository.save(attributetype);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(Attributetype attributetype) {
		return attributetypeRepository.existsById(attributetype.getAttributetypeid());
	}


}

/*
 * Created on 2018-06-18 ( Date ISO 2018-06-18 - Time 12:46:07 )
 *
*/
package com.gotracrat.attributesandtypes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gotracrat.attributesandtypes.entity.Attributelistitem;
import com.gotracrat.attributesandtypes.repository.AttributelistitemRepository;

/**
 * Service implementation for Attributelistitem.
 * @author prabhakar
 */
@Service
public class AttributelistitemServiceImpl implements AttributelistitemService {

	@Autowired
	private AttributelistitemRepository attributelistitemRepository;

	@Override
	public Optional<Attributelistitem> get(Integer attributelistvaluesid) {
		return attributelistitemRepository.findById(attributelistvaluesid);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Boolean delete(Integer attributelistvaluesid) {
		if (attributelistitemRepository.findById(attributelistvaluesid).isPresent()) {
			attributelistitemRepository.deleteById(attributelistvaluesid);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Attributelistitem create(Attributelistitem attributelistitem) {
		return attributelistitemRepository.save(attributelistitem);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Boolean save(Attributelistitem attributelistitem) {
		final Integer pk = attributelistitem.getAttributelistvaluesid();
		if (attributelistitemRepository.findById(pk).isPresent()) {
			attributelistitemRepository.save(attributelistitem);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(Attributelistitem attributelistitem) {
		return attributelistitemRepository.existsById(attributelistitem.getAttributelistvaluesid());
	}

	
}

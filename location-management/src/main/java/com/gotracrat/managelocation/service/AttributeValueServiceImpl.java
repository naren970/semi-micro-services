/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:29 )
 * prabhakar
*/
package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.managelocation.entity.Attributevalue;
import com.gotracrat.managelocation.entity.AttributevalueKey;
import com.gotracrat.managelocation.repository.AttributevalueRepository;

/**
 * Service implementation for Attributevalue.
 * @author prabhakar
 */
@Service
public class AttributeValueServiceImpl implements AttributeValueService {

	@Autowired
	private AttributevalueRepository attributevalueRepository;

	@Override
	public Attributevalue get(Integer attributenameid, Integer entityid, Integer entitytypeid) {
		// Build the composite key
		AttributevalueKey key = new AttributevalueKey(attributenameid, entityid, entitytypeid);
		return attributevalueRepository.findById(key).orElseThrow(() ->
				new ResourceNotFoundException(GoTracratConstants.ATTRIBUTE_VALUE_NOT_FOUND));
	}

	@Override
	public Boolean delete(Integer attributenameid, Integer entityid, Integer entitytypeid) {
		// Build the composite key
		AttributevalueKey key = new AttributevalueKey(attributenameid, entityid, entitytypeid);
		if (attributevalueRepository.findById(key).isPresent()) {
			attributevalueRepository.deleteById(key);
			return true;
		}
		return false;
	}

	@Override
	public Attributevalue create(Attributevalue attributevalue) {
		return attributevalueRepository.save(attributevalue);
	}

	@Override
	public Boolean save(Attributevalue attributevalue) {
		return false;
	}

	@Override
	public Boolean exist(Attributevalue attributevalue) {
		// Build the composite key
		AttributevalueKey pk = new AttributevalueKey(1, attributevalue.getEntityid(), attributevalue.getEntitytypeid());
		return attributevalueRepository.existsById(pk);
	}

}

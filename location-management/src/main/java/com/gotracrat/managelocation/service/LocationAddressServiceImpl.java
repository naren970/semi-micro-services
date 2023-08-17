/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:30 )
 * prabhakar
 */
package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.entity.Locationaddress;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.LocationaddressRepository;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Locationaddress.
 *
 * @author prabhakar
 */
@Service
public class LocationAddressServiceImpl implements LocationAddressService {

    @Autowired
    private LocationaddressRepository locationaddressRepository;

    @Override
    public Locationaddress get(Integer locationaddressid) {
        return locationaddressRepository.findById(locationaddressid).orElseThrow(() ->
                new ResourceNotFoundException(GoTracratConstants.LOCATION_ADDRESS_NOT_FOUND));
    }

    @Override
    public Boolean delete(Integer locationaddressid) {
        if (locationaddressRepository.findById(locationaddressid).isPresent()) {
            locationaddressRepository.deleteById(locationaddressid);
            return true;
        }
        return false;
    }

    @Override
    public Locationaddress create(Locationaddress locationaddress) {
        return locationaddressRepository.save(locationaddress);
    }

    @Override
    public Boolean save(Locationaddress locationaddress) {
        final Integer pk = locationaddress.getLocationaddressid();
        if (locationaddressRepository.findById(pk).isPresent()) {
            locationaddressRepository.save(locationaddress);
            return true;
        }
        return false;
    }

    @Override
    public Boolean exist(Locationaddress locationaddress) {
        return locationaddressRepository.existsById(locationaddress.getLocationaddressid());
    }


}

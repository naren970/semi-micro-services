package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.entity.Company;
import com.gotracrat.managelocation.entity.UserLog;
import com.gotracrat.managelocation.entity.WarrantyType;
import com.gotracrat.managelocation.exception.ResourceNotFoundException;
import com.gotracrat.managelocation.repository.WarrantyTypeRepository;
import com.gotracrat.managelocation.resource.WarrantyTypeResource;
import com.gotracrat.managelocation.utils.GoTracratConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for WarrantyType.
 *
 * @author prabhakar
 * @since 13-08-2018
 */
@Service
public class WarrantyTypeServiceImpl implements WarrantyTypeService {

    @Autowired
    private WarrantyTypeRepository warrantyTypeRepository;

    @Override
    public List<WarrantyTypeResource> getAllWarrantyTypeByCompanyId(Integer companyId) {
        return buildWarrantyTypeResources(warrantyTypeRepository.findByCompanyCompanyid(companyId));
    }

    @Override
    public WarrantyTypeResource getWarrantyType(Integer warrantyTypeId) {
        Optional<WarrantyType> warrantyType = warrantyTypeRepository.findById(warrantyTypeId);
        if (warrantyType.isPresent()) {
            return buildWarrantyTypeResource(warrantyType.get());
        }
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_WARRANTY_TYPE_ID + warrantyTypeId);
    }

    private WarrantyTypeResource buildWarrantyTypeResource(WarrantyType warrantyType) {
        WarrantyTypeResource warrantytypeResource = new WarrantyTypeResource();
        BeanUtils.copyProperties(warrantyType, warrantytypeResource);
        warrantytypeResource.setCompanyid(warrantyType.getCompany().getCompanyid());
        return warrantytypeResource;
    }

    @Override
    public WarrantyTypeResource createWarrantyType(WarrantyTypeResource warrantyTypeResource, UserLog userLog) {
        return buildWarrantyTypeResource(warrantyTypeRepository.save(buildWarrantyType(warrantyTypeResource)));
    }


    @Override
    public void updateWarrantyType(WarrantyTypeResource warrantyTypeResource, UserLog userLog) {
        if (warrantyTypeRepository.existsById(warrantyTypeResource.getWarrantytypeid())) {
            warrantyTypeRepository.save(buildWarrantyType(warrantyTypeResource));
        } else {
            throw new ResourceNotFoundException(GoTracratConstants.INVALID_WARRANTY_TYPE_ID + warrantyTypeResource.getWarrantytypeid());
        }
    }

    @Override
    public String delete(Integer warrantyTypeId, UserLog userLog) {
        if (warrantyTypeRepository.existsById(warrantyTypeId)) {
            warrantyTypeRepository.deleteById(warrantyTypeId);
            return GoTracratConstants.WARRANTY_TYPE_DELETED;
        }
        throw new ResourceNotFoundException(GoTracratConstants.INVALID_WARRANTY_TYPE_ID + warrantyTypeId);
    }


    private WarrantyType buildWarrantyType(WarrantyTypeResource warrantytypeResource) {
        WarrantyType warrantytype = new WarrantyType();
        BeanUtils.copyProperties(warrantytypeResource, warrantytype);
        Company company = new Company();
        company.setCompanyid(warrantytypeResource.getCompanyid());
        warrantytype.setCompany(company);
        return warrantytype;
    }

    private List<WarrantyTypeResource> buildWarrantyTypeResources(List<WarrantyType> warrantyTypes) {
        if (warrantyTypes != null && !warrantyTypes.isEmpty()) {
            return warrantyTypes.stream().map(warrantyType -> {
                WarrantyTypeResource warrantytypeResource = new WarrantyTypeResource();
                BeanUtils.copyProperties(warrantyType, warrantytypeResource);
                warrantytypeResource.setCompanyid(warrantyType.getCompany().getCompanyid());
                return warrantytypeResource;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}

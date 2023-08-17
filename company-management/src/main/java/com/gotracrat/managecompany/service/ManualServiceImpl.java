package com.gotracrat.managecompany.service;

import com.gotracrat.managecompany.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.managecompany.resource.ManualResource;
import com.gotracrat.managecompany.entity.Manual;
import com.gotracrat.managecompany.repository.ManualRepository;
import com.gotracrat.managecompany.util.GoTracratContants;
import com.gotracrat.managecompany.util.GotracratUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ManualServiceImpl implements ManualService {

    @Autowired
    private ManualRepository manualRepository;

    @Override
    public ManualResource getManual() {
        Manual manual = manualRepository.findById(GoTracratContants.MANUAL_ID).orElseThrow(() ->
                new ResourceNotFoundException(GoTracratContants.INVALID_COMPANY_ID));
        ManualResource manualResource = new ManualResource();
        BeanUtils.copyProperties(manual, manualResource);
        manualResource.setManualFile(GotracratUtility.decompress(manual.getManual()));
        return manualResource;
    }

    @Override
    public void save(ManualResource manualResource) {
        Manual manual = new Manual();
        BeanUtils.copyProperties(manualResource, manual);
        byte[] compressed = GotracratUtility.compress(manualResource.getManualFile());
        manual.setManual(compressed);
        manualRepository.save(manual);
    }
}

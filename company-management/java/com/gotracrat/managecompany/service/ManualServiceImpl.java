package com.gotracrat.managecompany.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.managecompany.resource.resource.ManualResource;
import com.gotracrat.managecompany.entity.Manual;
import com.gotracrat.managecompany.repository.ManualRepository;
import com.gotracrat.managecompany.util.GoTracratContants;
import com.gotracrat.managecompany.util.GotracratUtility;


@Service
public class ManualServiceImpl implements ManualService {

	@Autowired
	private ManualRepository manualRepository;
	
	@Override
	public ManualResource getManual()  {
		Optional<Manual> manualData = manualRepository.findById(GoTracratContants.MANUAL_ID);
		Manual manual =manualData.get();
		ManualResource  manualResource = new ManualResource();
		BeanUtils.copyProperties(manual, manualResource);
		try {
			manualResource.setManualFile(GotracratUtility.decompress(manual.getManual()));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return manualResource;
	}

	@Override
	public void save(ManualResource manualResource) throws IOException {
		Manual manual=new Manual();
		BeanUtils.copyProperties(manualResource, manual);
		byte[] compressed = GotracratUtility.compress(manualResource.getManualFile());
		manual.setManual(compressed);
	    manualRepository.save(manual);
	}

}

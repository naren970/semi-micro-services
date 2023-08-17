package com.gotracrat.managecompany.service;


import java.io.IOException;

import com.gotracrat.managecompany.resource.resource.ManualResource;

public interface ManualService {

	public ManualResource getManual();

	public void save(ManualResource manualResource) throws IOException;

}

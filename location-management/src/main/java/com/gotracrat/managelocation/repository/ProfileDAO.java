package com.gotracrat.managelocation.repository;

import com.gotracrat.managelocation.resource.ProfileResource;

public interface ProfileDAO {
	public ProfileResource getProfileWithUserId(String userid);
}

package com.gotracrat.managecompany.assemblers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.gotracrat.managecompany.resource.resource.AnnouncementResource;
import com.gotracrat.managecompany.resource.resource.CompanyResource;
import com.gotracrat.managecompany.entity.Announcement;
import com.gotracrat.managecompany.entity.Company;

public class CompanyToCompanyResourceAssembler {
	private CompanyToCompanyResourceAssembler() {
	}

	public static CompanyResource companyResourceFromCompany(Company company)  {
		CompanyResource companyResource = new CompanyResource();
		BeanUtils.copyProperties(company, companyResource);
		List<Announcement> announcements = company.getAnnouncements();
		if (!CollectionUtils.isEmpty(announcements)) {
			Announcement announcement = announcements.get(0);
			AnnouncementResource announcementResource = new AnnouncementResource();
			BeanUtils.copyProperties(announcement, announcementResource);
			companyResource.setAnnouncement(announcementResource);
		}
		if (company.getParentcompany() != null) {
			CompanyResource parentCompany = new CompanyResource();
			BeanUtils.copyProperties(company.getParentcompany(), parentCompany);
			if (parentCompany.getParentcompanyResourceList() != null && 
					!parentCompany.getParentcompanyResourceList().isEmpty()) {
				parentCompany.getParentcompanyResourceList().clear();
			}
			companyResource.setParentcompany(parentCompany);
		}
		return companyResource;

	}
}

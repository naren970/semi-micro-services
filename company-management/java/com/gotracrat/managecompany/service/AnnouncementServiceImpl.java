package com.gotracrat.managecompany.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotracrat.managecompany.resource.resource.AnnouncementResource;
import com.gotracrat.managecompany.entity.Announcement;
import com.gotracrat.managecompany.entity.Company;
import com.gotracrat.managecompany.repository.AnnouncementRepository;

/*
 * Service implementation for Announcement.
 * @author prabhakar
 * since 2018-05-25
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementRepository announcementRepository;

	@Override
	public Optional<Announcement> get(Integer announcementid) {
		return announcementRepository.findById(announcementid);
	}

	@Override
	public Boolean delete(Integer announcementid) {
		if (announcementRepository.findById(announcementid) != null) {
			announcementRepository.deleteById(announcementid);
			return true;
		}
		return false;
	}

	@Override
	public Announcement create(Announcement announcement) {
		return announcementRepository.save(announcement);
	}

	@Override
	public boolean update(AnnouncementResource announcementResource) {
		Announcement announcement = new Announcement();
		BeanUtils.copyProperties(announcementResource, announcement);
		Company company = new Company();
		company.setCompanyid(announcementResource.getCompanyid());
		announcement.setCompany(company);
		if (announcementRepository.findById(announcement.getAnnouncementid()) != null) {
			announcementRepository.save(announcement);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(Announcement announcement) {
		return announcementRepository.existsById(announcement.getAnnouncementid());
	}

}

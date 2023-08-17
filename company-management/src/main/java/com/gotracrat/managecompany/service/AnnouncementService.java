package com.gotracrat.managecompany.service;

import com.gotracrat.managecompany.entity.Announcement;
import com.gotracrat.managecompany.resource.AnnouncementResource;

import java.util.Optional;

/**
 * Service interface for Announcement.
 * @author prabhakar
 * since 2018-05-25
 */
public interface AnnouncementService {
	/**
	 * Recover an announcement following an id.
	 * @param id the given id
	 * @return the announcement
	 */
	public Optional<Announcement> get(Integer announcementid);

	/**
	 * Perform an announcement deletion.
	 * @param id the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public Boolean delete(Integer announcementid);

	/**
	 * Perform an announcement creation.
	 * @param announcement to create
	 * @return created announcement
	 */
	public Announcement create(Announcement announcement);

	/**
	 * Perform an announcement update.
     * @param announcement to update
	 */
	public void update(AnnouncementResource announcementResource);

	/**
	 * Test announcement existence.
	 * @param announcement to check
	 * @return true if author exist otherwise false
	 */
	public Boolean exist(Announcement announcement);
}

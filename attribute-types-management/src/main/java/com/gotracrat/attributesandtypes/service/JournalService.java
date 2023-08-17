/*
 * Created on 2018-05-25 ( Date ISO 2018-05-25 - Time 20:24:58 )
 * 
*/
package com.gotracrat.attributesandtypes.service;

import java.util.List;

import com.gotracrat.attributesandtypes.entity.Journal;
import com.gotracrat.attributesandtypes.entity.UserLog;

/**
 * Service interface for Journal.
 * 
 * @author prabhakar
 */
public interface JournalService {

	/**
	 * Perform a pageable and filtered search.
	 * 
	 * @param pageable
	 *            pagination configuration
	 * @param criteria
	 *            filters
	 * @return a page of journal
	 */
	// public Page<Journal> search(Pageable pageable, JournalCriteria criteria);
	/**
	 * Recover list of journal.
	 * 
	 * @return the All journal
	 */
	public List<Journal> getAllNotes(int journalTypeId, int companyID, int entitytypeid,  int entityid);

	/**
	 * Recover an journal following an id.
	 * 
	 * @param id
	 *            the given id
	 * @return the journal
	 */
	public Journal get(Integer journalid);

	

	/**
	 * Perform an journal creation.
	 * 
	 * @param journal
	 *            to create
	 * @return created journal
	 */
	public Journal create(Journal journal,UserLog userLog);

	/**
	 * Perform an journal update.
	 * 
	 * @param journal
	 *            to update
	 * @return state of update (true if ok otherwise false)
	 */
	public Boolean save(Journal journal,UserLog userLog);

	/**
	 * Test journal existence.
	 * 
	 * @param journal
	 *            to check
	 * @return true if author exist otherwise false
	 */
	public Boolean exist(Journal journal);

	public List<Journal> getAllNotes(int companyID, int entitytypeid, int entityid);

	/**
	 * Perform an journal deletion.
	 * 
	 * @param id
	 *            the given id
	 * @return state of deletion (true if ok otherwise false)
	 */
	public String delete(Integer journalId, UserLog userLog, Journal journalTemp);

	String getActionComment(Journal journal, String updateNoteType);
}

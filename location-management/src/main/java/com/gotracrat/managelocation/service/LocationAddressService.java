/*
 * Created on 2018-07-15 ( Date ISO 2018-07-15 - Time 13:09:30 )
 * prabhakar
 */
package com.gotracrat.managelocation.service;

import com.gotracrat.managelocation.entity.Locationaddress;

/**
 * Service interface for Locationaddress.
 *
 * @author prabhakar
 */
public interface LocationAddressService {

    /**
     * Perform a pageable and filtered search.
     * @param pageable pagination configuration
     * @param criteria filters
     * @return a page of locationaddress
     */
    //public Page<Locationaddress> search(Pageable pageable, LocationaddressCriteria criteria);

    /**
     * Recover an locationaddress following an id.
     *
     * @param locationaddressid the given id
     * @return the locationaddress
     */
    public Locationaddress get(Integer locationaddressid);

    /**
     * Perform an locationaddress deletion.
     *
     * @param locationaddressid the given id
     * @return state of deletion (true if ok otherwise false)
     */
    public Boolean delete(Integer locationaddressid);

    /**
     * Perform an locationaddress creation.
     *
     * @param locationaddress to create
     * @return created locationaddress
     */
    public Locationaddress create(Locationaddress locationaddress);

    /**
     * Perform an locationaddress update.
     *
     * @param locationaddress to update
     * @return state of update (true if ok otherwise false)
     */
    public Boolean save(Locationaddress locationaddress);

    /**
     * Test locationaddress existence.
     *
     * @param locationaddress to check
     * @return true if author exist otherwise false
     */
    public Boolean exist(Locationaddress locationaddress);
}

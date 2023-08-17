package com.gotracrat.managelocation.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gotracrat.managelocation.repository.ItemDAO;

@Component
public class LocationCacheService {
	
	@Autowired
	private ItemDAO ItemDAOImpl;
	
	private Map<Integer, Map<String, Object>> locationsCacheByCompany = new HashMap<>();
	
	public Map<String,Object> getAllLocationsByHeirarchy( Integer companyId) throws SQLException{
		loadLocationsToCache(companyId);
		return locationsCacheByCompany.get(companyId);
	}
	
	private synchronized void loadLocationsToCache(Integer companyId) throws SQLException {
		if ( locationsCacheByCompany.get(companyId)
				== null) {
			Map<String, Object> resultMap = loadLocationsFromDb(companyId);
			locationsCacheByCompany.put(companyId, resultMap);
		}
	}
	
	private Map<String, Object> loadLocationsFromDb(Integer companyId) throws SQLException {
		return ItemDAOImpl.getAllLocationByHierarchy(companyId);
	}

	public void updateAllLocationsByHeirarchy(Integer companyID) {
		
		 
		 if (locationsCacheByCompany.containsKey(companyID)) {
			 locationsCacheByCompany.remove(companyID);	
			
			}
	}
	public void deleteAllLocationsByHeirarchy(Integer companyID) {
		if (locationsCacheByCompany.containsKey(companyID)) {
			 locationsCacheByCompany.remove(companyID);
		}
	}

}

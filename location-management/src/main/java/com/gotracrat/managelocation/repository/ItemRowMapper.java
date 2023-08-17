package com.gotracrat.managelocation.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gotracrat.managelocation.resource.ItemResource;

public class ItemRowMapper implements RowMapper<Object> { 

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		ItemResource item = new ItemResource();
		item.setItemid(rs.getInt("ItemID"));
		item.setLocationid(rs.getInt("LocationID"));
		item.setTag(rs.getString("Tag"));
		item.setName(rs.getString("Name"));
		item.setDescription(rs.getString("Description"));
		item.setStatusid(rs.getInt("StatusID"));
		item.setWarrantytypeid(rs.getInt("WarrantyTypeID"));  
        item.setWarrantyexpiration(rs.getDate("WarrantyExpiration"));
        item.setSerialnumber(rs.getString("SerialNumber"));
        item.setModelnumber(rs.getString("ModelNumber"));
        item.setMeantimebetweenservice(rs.getInt("MeanTimeBetweenService"));
        item.setInserviceon(rs.getDate("InServiceOn"));
        item.setLastmodifiedby(rs.getString("LastModifiedBy"));
        item.setIsinrepair(rs.getBoolean("LastModifiedBy"));
        item.setDesiredspareratio(rs.getBigDecimal("DesiredSpareRatio"));
        item.setManufacturerid(rs.getInt("ManufacturerID"));
        item.setRepairqual(rs.getInt("RepairQual"));
        item.setPurchaseprice(rs.getBigDecimal("PurchasePrice"));
        item.setPurchasedate(rs.getDate("PurchaseDate"));
        item.setDefaultimageattachmentid(rs.getInt("DefaultImageAttachmentID"));
        item.setIsstale(rs.getBoolean("IsStale"));
        item.setTypeId(rs.getInt("TypeID"));   
        item.setStatusname(rs.getString("status"));
        item.setLocationName(rs.getString("LocationPath"));
        item.setTypeName(rs.getString("TypeName"));
		return item;
	}

}

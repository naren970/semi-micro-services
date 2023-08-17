package com.gotracrat.managelocation.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the TransferLog database table.
 * 
 */
@Entity
public class TransferLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransferLogID")
	private Integer transferLogID;

	@Column(name = "DaysinOldStatus")
	private Integer daysinOldStatus;

	@Column(name = "Details")
	private String details;

	@Column(name = "FromLocation")
	private String fromLocation;

	@Column(name = "FromLocationID")
	private Integer fromLocationID;

	@Column(name = "ItemID")
	private Integer itemID;

	@Column(name = "JobNumber")
	private String jobNumber;

	@Column(name = "NewStatus")
	private String newStatus;

	@Column(name = "OldStatus")
	private String oldStatus;

	@Column(name = "PONumber")
	private String PONumber;

	@Column(name = "ShippingNumber")
	private String shippingNumber;

	@Column(name = "ToLocation")
	private String toLocation;

	@Column(name = "ToLocationID")
	private Integer toLocationID;

	@Column(name = "TrackingNumber")
	private String trackingNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TransferDate")
	private Date transferDate;

	@Column(name = "TransferredBy")
	private String transferredBy;

	public TransferLog() {
	}

	public TransferLog(Integer transferLogID, Integer daysinOldStatus, String details, String fromLocation,
			Integer fromLocationID, Integer itemID, String jobNumber, String newStatus, String oldStatus,
			String pONumber, String shippingNumber, String toLocation, Integer toLocationID, String trackingNumber,
			Date transferDate, String transferredBy) {
		super();
		this.transferLogID = transferLogID;
		this.daysinOldStatus = daysinOldStatus;
		this.details = details;
		this.fromLocation = fromLocation;
		this.fromLocationID = fromLocationID;
		this.itemID = itemID;
		this.jobNumber = jobNumber;
		this.newStatus = newStatus;
		this.oldStatus = oldStatus;
		PONumber = pONumber;
		this.shippingNumber = shippingNumber;
		this.toLocation = toLocation;
		this.toLocationID = toLocationID;
		this.trackingNumber = trackingNumber;
		this.transferDate = transferDate;
		this.transferredBy = transferredBy;
	}

	public Integer getTransferLogID() {
		return transferLogID;
	}

	public void setTransferLogID(Integer transferLogID) {
		this.transferLogID = transferLogID;
	}

	public Integer getDaysinOldStatus() {
		return daysinOldStatus;
	}

	public void setDaysinOldStatus(Integer daysinOldStatus) {
		this.daysinOldStatus = daysinOldStatus;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public Integer getFromLocationID() {
		return fromLocationID;
	}

	public void setFromLocationID(Integer fromLocationID) {
		this.fromLocationID = fromLocationID;
	}

	public Integer getItemID() {
		return itemID;
	}

	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getPONumber() {
		return PONumber;
	}

	public void setPONumber(String pONumber) {
		PONumber = pONumber;
	}

	public String getShippingNumber() {
		return shippingNumber;
	}

	public void setShippingNumber(String shippingNumber) {
		this.shippingNumber = shippingNumber;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public Integer getToLocationID() {
		return toLocationID;
	}

	public void setToLocationID(Integer toLocationID) {
		this.toLocationID = toLocationID;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getTransferredBy() {
		return transferredBy;
	}

	public void setTransferredBy(String transferredBy) {
		this.transferredBy = transferredBy;
	}

}

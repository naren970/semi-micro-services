package com.gotracrat.managelocation.utils;

public enum TimeFrame {

	LASTMONTH("LASTMONTH"), LASTYEAR("LASTYEAR"), LASTTWOYEAR("LASTTWOYEAR"), LASTQUARTER("LASTQUARTER"),
	RANGE("RANGE"), QUARTER("QUARTER"), YEAR("YEAR"), MONTH("MONTH");

	private String timeType;

	TimeFrame(String timeType) {
		this.timeType = timeType;
	}

	public String getTimeType() {
		return timeType;
	}

}

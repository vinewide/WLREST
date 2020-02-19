package com.ud.sys.utils;

public enum DefaultErrorMessageEnum {
	STANDARD_ERROR_MESSAGE("We are experiencing issues, please try again"),
	NO_RECORDS_FOUND_MESSAGE("No Records found");

	private String text;

	DefaultErrorMessageEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}

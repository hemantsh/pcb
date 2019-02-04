package com.sc.fe.analyze.util;

public enum ErrorCodes {
// 
	FM0000 ("Unknown error"),
	FM0001 ("Drill file missing"),
	FM0002 ("Signal file missing"),
	FM0003 ("power_ground file missing"),
	FM0004 ("solder_mask file missing"),
	FM0005 ("silk_screen file missing"),
	FM0006 ("solder_paste file missing"),
	FM0007 ("Document file missing"),
	FM0008 ("Component file missing"),
	FM0009 ("Rout file missing");
	
	private ErrorCodes(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String errorMessage;

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

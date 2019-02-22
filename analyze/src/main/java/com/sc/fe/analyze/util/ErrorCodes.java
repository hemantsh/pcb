package com.sc.fe.analyze.util;

public enum ErrorCodes {
    V0000("Unknown fileType"),
    V0001("Drill file missing"),
    V0002("Signal file missing"),
    V0003("power_ground file missing"),
    V0004("solder_mask file missing"),
    V0005("silk_screen file missing"),
    V0006("solder_paste file missing"),
    V0007("Document file missing"),
    V0008("Component file missing"),
    V0009("Rout file missing"),
	V0010("BOM file missing"),
	V0011("Gerber file required"),
	V0012("ODB file required"),
	V0013("X&Y required"),
	V0014("mixed file missing");

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

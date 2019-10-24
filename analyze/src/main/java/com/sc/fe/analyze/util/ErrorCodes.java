package com.sc.fe.analyze.util;

/**
 * ErrorCodes Class is used to set the Code Errors
 *
 * @author Hemant
 */
public enum ErrorCodes {
    V0000("Unknown fileType"),
    V0001("No Excellon Drill Data Found"),
    V0002("Signal file missing"),
    V0003("power_ground file missing"),
    V0004("solder_mask file missing"),
    V0005("silk_screen file missing"),
    V0006("solder_paste file missing"),
    V0007("Document file missing"),
    V0008("Component file missing"),
    V0009("Rout file missing"),
    V0010("BOM file missing"),
    V0012("X&Y required"),
    V0011("Gerber, ODB or IPC2581 file missing"),
    V0013("mixed file missing"),
    V0014("Invalid input for newProject and attachReplace"),
    V0015("Assembly Drawing file missing"),
    V0016("Fabrication Drawing file missing"),
    V0017("ODB or IPC2581 file missing"),
    V0018("Assembly TurnTime Quantity missing"),
    V0019("Fabrication TrunTime Quantity missing"),
    V0020("Drawing file missing"),
	V0021("Project with same zip file name already exist in system.");

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

package com.sc.fe.analyze.util;

public enum ErrorCodes {
    V0000("Unknown fileType"),
    V0001("Drill file"),
    V0002("Signal file missing"),
    V0003("power_ground file missing"),
    V0004("solder_mask file missing"),
    V0005("silk_screen file missing"),
    V0006("solder_paste file missing"),
    V0007("Document file missing"),
    V0008("Component file missing"),
    V0009("Rout file missing"),
    V0010("BOM file"),
    V0012("X&Y file"),
    V0011("Gerber, ODB or IPC2581 file"),
    V0013("mixed file missing"),
    V0014("Invalid input for newProject and attachReplace"),
    V0015("Assembly Drawing file"),
    V0016("Fabrication Drawing file"),
    V0017("ODB or IPC2581 file"),
    V0018("Assembly TurnTime Quantity missing"),
    V0019("Fabrication TrunTime Quantity missing");

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

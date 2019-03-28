package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeMap {

    private static final Map<String, ErrorCodes> errorMap = init();

    private static Map<String, ErrorCodes> init() {
        // TODO Auto-generated method stub
        Map<String, ErrorCodes> temp = new HashMap<String, ErrorCodes>();
        temp.put("drill", ErrorCodes.V0001);
        temp.put("signal", ErrorCodes.V0002);
        temp.put("power_ground", ErrorCodes.V0003);
        temp.put("solder_mask", ErrorCodes.V0004);
        temp.put("silk_screen", ErrorCodes.V0005);
        temp.put("solder_paste", ErrorCodes.V0006);
        temp.put("document", ErrorCodes.V0007);
        temp.put("component", ErrorCodes.V0008);
        temp.put("rout", ErrorCodes.V0009);
        temp.put("bom", ErrorCodes.V0010);
        temp.put("gerber", ErrorCodes.V0011);
        temp.put("odb", ErrorCodes.V0011);
        temp.put("ipc2581", ErrorCodes.V0011);
        temp.put("gerber or odb", ErrorCodes.V0011);
        temp.put("gerber or odb or odb++", ErrorCodes.V0011);
        temp.put("gerber or odb or odb++ or ipc2581", ErrorCodes.V0011);
        temp.put("gerber or odb or ipc2581", ErrorCodes.V0011);
        temp.put("x&y", ErrorCodes.V0012);
        temp.put("x&y or x and y", ErrorCodes.V0012);
        temp.put("x&y or x and y or xandy", ErrorCodes.V0012);
        temp.put("mixed", ErrorCodes.V0013);
        temp.put("invalid_input", ErrorCodes.V0014);
        temp.put("drawing", ErrorCodes.V0015);
        temp.put("assembly_drawing", ErrorCodes.V0015);
        temp.put("fabrication_drawing", ErrorCodes.V0016);
        temp.put("odb or ipc2581", ErrorCodes.V0017);
        temp.put("assembly turnTime qunatity", ErrorCodes.V0018);
        temp.put("fabrication turnTime qunatity", ErrorCodes.V0019);
        return temp;
    }

    public static ErrorCodes getCodeForFileType(String fileType) {
        ErrorCodes code = errorMap.get(fileType.trim().toLowerCase());
        if (code == null) {
            code = ErrorCodes.V0000;
        }
        return code;
    }

}

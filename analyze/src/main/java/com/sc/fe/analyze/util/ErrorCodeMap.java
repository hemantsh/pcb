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
		temp.put("domponent", ErrorCodes.V0008);
		temp.put("rout", ErrorCodes.V0009);
		
		return temp;
	}
	
	public static ErrorCodes getCodeForFileType( String fileType) {
		ErrorCodes code = errorMap.get(fileType);
		if( code == null) {
			code = ErrorCodes.V0000;
		}
		return code;
	}

}

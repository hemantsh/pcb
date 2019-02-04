package com.sc.fe.analyze.util;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeMap {
	
	private static final Map<String, ErrorCodes> errorMap = init();

	private static Map<String, ErrorCodes> init() {
		// TODO Auto-generated method stub

		Map<String, ErrorCodes> temp = new HashMap<String, ErrorCodes>();
		temp.put("drill", ErrorCodes.FM0001);
		temp.put("signal", ErrorCodes.FM0002);
		temp.put("power_ground", ErrorCodes.FM0003);
		temp.put("solder_mask", ErrorCodes.FM0004);
		temp.put("silk_screen", ErrorCodes.FM0005);
		temp.put("solder_paste", ErrorCodes.FM0006);
		temp.put("document", ErrorCodes.FM0007);
		temp.put("domponent", ErrorCodes.FM0008);
		temp.put("Rout", ErrorCodes.FM0009);
		
		return temp;
	}
	
	public static ErrorCodes getCodeForFileType( String fileType) {
		ErrorCodes code = errorMap.get(fileType);
		if( code == null) {
			code = ErrorCodes.FM0000;
		}
		return code;
	}

}

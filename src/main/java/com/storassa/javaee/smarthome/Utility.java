package com.storassa.javaee.smarthome;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	public static String getCurrentTime() {
		
		long yourmilliseconds = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat(Flags.DATE_FORMAT_MEASURE);
		Date resultdate = new Date(yourmilliseconds);

		return sdf.format(resultdate).trim();
	}
}

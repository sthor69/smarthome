package com.storassa.javaee.smarthome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.ejb.EJB;

public class Historizer {

	private static final String TAG = "smarthome";

	@EJB
	private MeasureEJB measureEjb;

	public void init() throws InterruptedException {

		BufferedReader br = null;

		try {

			long current = 0;

			String result = "";

			String line = "";
			
			while (true) {

				br = new BufferedReader(new FileReader(System.getProperty("catalina.home") + "/webapps/smarthome/test.txt"));
				;

				line = br.readLine();
				System.out.println("Curr: " + current + "; in file: " + line);
				if (null != line && Long.parseLong(line) != current) {

					current = Long.parseLong(line);

					while ((line = br.readLine()) != null) {

						if (line.startsWith(TAG)) {
							result = line.substring(15);
						}

						Measure newMeasure = new Measure();
						newMeasure.setTemp(new int[] { Integer.parseInt(result) });
						newMeasure.setTime(System.currentTimeMillis());
						measureEjb.createMeasure(newMeasure);
					}

					Thread.sleep(Flags.MONITOR_DELAY);

				}

			}
		} catch (IOException e) {
//			try {
//				br.close();
//			} catch (IOException e1) {
//
//				throw new RuntimeException(e);
//			}
			throw new RuntimeException(e);
		}

	}
}

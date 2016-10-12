package com.storassa.javaee.smarthome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Stateless;

@Stateless
@LocalBean
@Startup
public class UpdateEJB {

	private static final String TAG = "smarthome";

	Measure newMeasure = new Measure();
	String type = "";
	int measureIdx;

	@EJB
	MeasureEJB measureEjb;

	@PostConstruct
	private void init() {
		System.out.println("UpdateEJB Created");
	}

	@Schedule(second = "*/1", minute = "*", hour = "*", persistent = false)
	public void doWork() {

		System.out.println("New cycle of timer");

		BufferedReader br = null;

		try {

			String[] result = { "", "" };

			String line = "";
			
			int[] temp = new int[2], humidity = new int[2];

			br = new BufferedReader(
					new FileReader(System.getProperty("catalina.home") + "/webapps/smarthome/test.txt"));

			line = br.readLine();

			while (line != null) {

				System.out.println("Read line: " + line);

				// if a new sensor message is found extract the temperature,
				// otherwise set it to null string

				if (line.startsWith(TAG)) {

					result = line.substring(TAG.length() + 1).split(":");

					switch (result[0]) {

					case "room":
						measureIdx = 0;
						break;
					case "chld":
						measureIdx = 1;
						break;
					}
					
					System.out.print("In " + result[0]);

					result = line.substring(15).split(",");
					
				} else

					result = null;

				// if temp is not null, set the measure and log
				// otherwise set temp to 0 and log the missing message
				
				if (null != result) {
					
					temp[measureIdx] = Integer.parseInt(result[0]);
					humidity[measureIdx] = Integer.parseInt(result[1]);
					
					System.out.println(" the temperature is " + temp[measureIdx] +
							" and the humidity is " + humidity[measureIdx]);

				} else {

					temp[measureIdx] = 0;
					humidity[measureIdx] = 0;
				}
				
				newMeasure.setTemp(temp);
				newMeasure.setHumidity(humidity);

				// retrieve current date and time
				long yourmilliseconds = System.currentTimeMillis();
				SimpleDateFormat sdf = new SimpleDateFormat(Flags.DATE_FORMAT_MEASURE);
				Date resultdate = new Date(yourmilliseconds);

				// set the current date and time
				newMeasure.setTime(sdf.format(resultdate).trim());

				// if MeasureEJB got injected, persist the new measure
				// otherwise log the mull EJB
				if (null != measureEjb) {

					System.out.println("Persisting new measure with temp " + newMeasure.getTemp());
					measureEjb.createMeasure(newMeasure);

				} else
					
					System.out.println("NULL Ejb!!!!");

				line = br.readLine();
			}

			br.close();

		} catch (Exception e) {
			System.out.println("Exception thrown in UpdateEJB: " + e.getCause());
		}
	}

}

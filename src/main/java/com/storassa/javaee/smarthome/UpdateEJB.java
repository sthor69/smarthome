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

	String[] places = { "room", "chld" };
	String type = "";
	int measureIdx;
	int[] temp = new int[2], humidity = new int[2];

	@EJB
	MeasureEJB measureEjb;

	@PostConstruct
	private void init() {
		System.out.println("UpdateEJB Created");
	}

	@Schedule(second = "*/1", minute = "*", hour = "*", persistent = false)
	public void doWork() {
		
		Measure newMeasure = new Measure();

		System.out.println("New cycle of timer");

		BufferedReader br = null;

		try {

			String[] result = { "", "" };

			String line = "";		

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
					
					result = line.substring(15).split(",");
					
				} else

					result = null;

				// if temp is not null, set the measure and log
				// otherwise set temp to 0 and log the missing message
				
				if (null != result) {
					
					temp[measureIdx] = Integer.parseInt(result[0]);
					humidity[measureIdx] = Integer.parseInt(result[1]);
					
					System.out.println("In " + places[measureIdx] 
							+ " the temperature is " + temp[measureIdx] +
							" and the humidity is " + humidity[measureIdx]);

				} else {

					temp[measureIdx] = 0;
					humidity[measureIdx] = 0;
				}
				
				// retrieve current date and time
				long yourmilliseconds = System.currentTimeMillis();
				SimpleDateFormat sdf = new SimpleDateFormat(Flags.DATE_FORMAT_MEASURE);
				Date resultdate = new Date(yourmilliseconds);

				// set the current date and time
				newMeasure.setTime(sdf.format(resultdate).trim());

				line = br.readLine();
			}

			// if MeasureEJB got injected, persist the new measure
			// otherwise log the mull EJB
			if (null != measureEjb) {

				newMeasure.setTemp(temp);
				newMeasure.setHumidity(humidity);

				System.out.println("Persisting new measure with temp " + 
				temp[0] + " and " + temp[1] + ", " +
						"humidity " + humidity[0] + " and " + humidity[1]);
				measureEjb.createMeasure(newMeasure);

			} else
				
				System.out.println("NULL Ejb!!!!");

			br.close();

		} catch (Exception e) {
			System.out.println("Exception thrown in UpdateEJB: " + e.getCause());
		}
	}

}

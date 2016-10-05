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

	@EJB
	MeasureEJB measureEjb;

	@PostConstruct
	private void init() {
		System.out.println("UpdateEJB Created");
	}

	@Schedule(minute = "*/1", hour = "*", persistent = false)
	public void doWork() {
		
		System.out.println("Timer fired");
		
		BufferedReader br = null;

		try {

			String[] result = {"",""};

			String line = "";

			br = new BufferedReader(new FileReader(System.getProperty("catalina.home") + "/webapps/smarthome/test.txt"));

			line = br.readLine();
			
			System.out.println("Read lin: " + line);

			while ((line = br.readLine()) != null) {

				Measure newMeasure = new Measure();

				// if a new sensor message is found extract the temperature,
				// otherwise set it to null string
				if (line.startsWith(TAG)) {

					result = line.substring(15).split(",");
					
				} else
					
					result = null;

				// if temp is not null, set the measure and log
				// otherwise set temp to 0 and log the missing message
				if (null != result) {

					newMeasure.setTemp(new int[] { Integer.parseInt(result[0]) });
					newMeasure.setHumidity(new int[] {Integer.parseInt(result[1]) });
					System.out.println("Found new measure with temp: " + result[0]
							+ ", and humidity " + result[1]);

				} else {

					newMeasure.setTemp(new int[] { 0 });
					System.out.println("Sensors are not sending messages");
				}

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
			}

			br.close();

		} catch (Exception e) {
			System.out.println("Exception thrown in UpdateEJB: " + e.getCause());
		}
	}

}

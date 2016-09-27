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
		System.out.println("MeasureEJB Created");
	}

	@Schedule(second = "*/1", minute = "*", hour = "*", persistent = false)
	public void doWork() {
		BufferedReader br = null;

		try {

			String result = "";

			String line = "";

			br = new BufferedReader(new FileReader(System.getProperty("catalina.home") + "/webapps/smarthome/test.txt"));

			line = br.readLine();

			while ((line = br.readLine()) != null) {

				if (line.startsWith(TAG)) {
					result = line.substring(15);
				}

				System.out.println("Found new measure with temp: " + result);

				if ("" != result) {
					Measure newMeasure = new Measure();
					newMeasure.setTemp(new int[] { Integer.parseInt(result) });

					long yourmilliseconds = System.currentTimeMillis();
					SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
					Date resultdate = new Date(yourmilliseconds);
					newMeasure.setTime(sdf.format(resultdate));

					if (null != measureEjb) {

						System.out.println("Persisting new measure with temp " + newMeasure.getTemp());
						measureEjb.createMeasure(newMeasure);

					} else
						System.out.println("NULL Ejb!!!!");
				}
			}

			br.close();

		} catch (Exception e) {
			// try {
			// br.close();
			// } catch (IOException e1) {
			//
			// throw new RuntimeException(e);
			// }
			System.out.println(e.getMessage());
		}
	}

}

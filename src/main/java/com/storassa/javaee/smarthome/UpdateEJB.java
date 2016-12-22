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
	int[] temp = new int[2], 
			humidity = new int[2], 
			water = new int[2];
	

	@EJB
	MeasureEJB measureEjb;

	@EJB
	MonitorEJB monitorEjb;
	
	@PostConstruct
	private void init() {
		System.out.println("UpdateEJB Created");
	}

	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void doWork() {

		System.out.println("New cycle of timer");

		BufferedReader br = null;

		try {

			String[] result = { "", "" };

			String line = "";

			br = new BufferedReader(
					new FileReader(System.getProperty("catalina.home") + "/webapps/smarthome/test.txt"));

			line = br.readLine();

			while (line != null) {

				// if a new sensor message is found extract the temperature,
				// otherwise set it to null string

				if (line.startsWith(TAG)) {

					// extract the fields in the measure

					result = line.substring(TAG.length() + 1).split(":");

					// extract the place of the measure

					switch (result[0]) {

					case "room":
						measureIdx = 0;
						System.out.println("processing room data...");
						break;
					case "chld":
						measureIdx = 1;
						System.out.println("processing chld data...");
						break;
					case "corr":
						measureIdx = 2;
						System.out.println("processing corr data...");
						break;
					}

					// extract the type of measure

					switch (result[1]) {

					case "measure":

						Measure newMeasure = new Measure();

						// extract the measure throwing away the length of the TAG,
						// the length of "measure", the length of the place (room, chld)
						// and the double semicolon

						result = line.substring(TAG.length() + 7 + "measure".length()).split(":");

						temp[measureIdx] = Integer.parseInt(result[0]);
						humidity[measureIdx] = Integer.parseInt(result[1]);
						

						// if MeasureEJB got injected, persist the new measure
						// otherwise log the mull EJB
						
						if (null != measureEjb) {

							newMeasure.setTime(Utility.getCurrentTime());
							newMeasure.setTemp(temp);
							newMeasure.setHumidity(humidity);
							
							measureEjb.createMeasure(newMeasure);

						} else

							System.out.println("NULL MeasureEjb!!!!");
						
						break;

					case "monitor":
						
						Monitor newMonitor = new Monitor();
						
						result = line.substring(TAG.length() + 7 + "monitor".length()).split(",");
						water[measureIdx] = Integer.parseInt(result[0]);
						
						if (null != monitorEjb) {
							
							newMonitor.setWater(water);
							newMonitor.setTime(Utility.getCurrentTime());
							
							monitorEjb.createMonitor(newMonitor);
							
						} else {
							
							System.out.println("NULL MonitorEjb!!!!");

						}
						
						break;
					}

				}

				line = br.readLine();
			}


			br.close();

		} catch (Exception e) {
			System.out.println("Exception thrown in UpdateEJB: " + e.getMessage());
		}
	}
	


}

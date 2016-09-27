package com.storassa.javaee.smarthome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "smarthome";

	long current = 0;

//	@EJB
//	MeasureEJB measureEjb;

//	@Resource(name = "concurrent/scheduledExecutor")
//	private ManagedScheduledExecutorService executorService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String temp = getTemperature();

		request.setAttribute("temp", temp);
		System.out.println("temperature = " + temp);
		request.getRequestDispatcher("/pages/dashboard.jsp").forward(request, response);
	}

	private String getTemperature() throws IOException {

		String result = "";

		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("catalina.home") + "/webapps/smarthome/test.txt"));

		String line = "";

		while ((line = br.readLine()) != null) {
			if (line.startsWith(TAG)) {
				result = line.substring(15);
			}
		}

		br.close();

		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

//	@Override
//	public void init() throws ServletException {
////		
//		System.out.println("init() method fired");
//
//		executorService.scheduleWithFixedDelay(new Runnable() {
//			@Override
//			public void run() {
//				BufferedReader br = null;
//
//				try {
//
//					String result = "";
//
//					String line = "";
//
//					br = new BufferedReader(new FileReader(System.getProperty("catalina.home") + "/webapps/smarthome/test.txt"));
//
//					line = br.readLine();
//
//					System.out.println("Current: " + current + ", read: " + Long.parseLong(line));
//
//					if (null != line && Long.parseLong(line) != current) {
//
//						current = Long.parseLong(line);
//
//						while ((line = br.readLine()) != null) {
//
//							if (line.startsWith(TAG)) {
//								result = line.substring(15);
//							}
//
//							System.out.println("Found new measure with temp: " + result);
//
//							if ("" != result) {
//								Measure newMeasure = new Measure();
//								newMeasure.setTemp(new int[] { Integer.parseInt(result) });
//
//								long yourmilliseconds = System.currentTimeMillis();
//								SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
//								Date resultdate = new Date(yourmilliseconds);
//								newMeasure.setTime(sdf.format(resultdate));
//
//								if (null != measureEjb) {
//
//									System.out.println("Persisting new measure with temp " + newMeasure.getTemp());
//									measureEjb.createMeasure(newMeasure);
//
//								} else
//									System.out.println("NULL EJB!!!!");
//							}
//						}
//
//					}
//
//					br.close();
//
//					Thread.sleep(Flags.MONITOR_DELAY);
//
//				} catch (Exception e) {
//					// try {
//					// br.close();
//					// } catch (IOException e1) {
//					//
//					// throw new RuntimeException(e);
//					// }
//					System.out.println(e.getMessage());
//				}
//			}
//		}, 0, 10, TimeUnit.SECONDS);
//
//	}

	public static void main(String[] args) {
		Dashboard d = new Dashboard();

		try {
			System.out.println(d.getTemperature());
		} catch (Exception e) {

		}
	}

}

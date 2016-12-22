package com.storassa.javaee.smarthome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private static final String TAG = "sh";


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

		List<String[]> fromFile = getTemperature();
		
		String[] roomMeasure = fromFile.get(0);
		int water = 0;

		request.setAttribute("roomTemp", roomMeasure[0]);
		request.setAttribute("roomHum", roomMeasure[1]);
		
		
		request.getRequestDispatcher("/pages/dashboard.jsp").forward(request, response);
	}

	private List<String[]> getTemperature() throws IOException {

		List<String[]> result = new ArrayList<String[]>();

		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("catalina.home") + "/webapps/smarthome/test.txt"));

		String line = "";

		while ((line = br.readLine()) != null) {
            System.out.println("Read: " + line);
			if (line.startsWith(TAG)) {
				result.add(line.substring(TAG.length() + 7 + "measure".length()).split(":"));
				System.out.println("Read from sensor: " + line.substring(TAG.length() + 7 + "measure".length()));
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

}

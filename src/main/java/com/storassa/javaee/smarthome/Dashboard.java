package com.storassa.javaee.smarthome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

}

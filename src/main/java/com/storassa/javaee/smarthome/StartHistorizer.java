package com.storassa.javaee.smarthome;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartHistorizer
 */
@WebServlet(description = "Servlet used to start/stop the Historizer", urlPatterns = { "/starthistorizer" })
public class StartHistorizer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	MeasureEJB measureEjb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartHistorizer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		measureEjb.executeBgThread();

		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

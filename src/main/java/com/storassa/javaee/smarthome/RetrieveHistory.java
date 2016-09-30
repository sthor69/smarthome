package com.storassa.javaee.smarthome;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class RetrieveHistory
 */
@WebServlet("/history")
public class RetrieveHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Measure> measures;

	@EJB
	MeasureEJB measureEjb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetrieveHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String num = request.getParameter("num");
		String date = request.getParameter("date");
		
		if (num != null && !num.equals("")) {

			measures = measureEjb.findMeasures(Integer.parseInt(num));

		} else if (date != null) {
			
			measures = measureEjb.findMeasure(date);
		
		} else {
			
			measures = measureEjb.findMeasures();
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(measures));
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

/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.mobile.platform.server.sample.persistence.jpa.servlet.context.BlueprintAdapter;
import com.sap.mobile.platform.server.sample.persistence.jpa.logic.api.IMyCompanyLogic;
import com.sap.mobile.platform.server.sample.persistence.jpa.logic.api.MyCompanyException;
import com.sap.mobile.platform.server.sample.persistence.jpa.model.Employee;

/**
 * Servlet implementation class Test
 */
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IMyCompanyLogic myCompanyLogic;

	public IMyCompanyLogic getMyCompanyLogic() {
		return this.myCompanyLogic;
	}

	public void setMyCompanyLogic(IMyCompanyLogic myCompanyLogic) {
		this.myCompanyLogic = myCompanyLogic;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {

		setMyCompanyLogic(BlueprintAdapter.getMyCompanyLogic());

		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (myCompanyLogic != null) {
			try {
				doAction(request, response);
			} catch (MyCompanyException mce) {
				response.getWriter().write(mce.getMessage());
			}
		} else {
			response.getWriter().write(
					"Company Logic service reference is null :-(");
		}
	}

	private void usage(HttpServletResponse response) throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("    <pre>");
		sb.append("       Usage:\n");
		sb.append("       ...?action=getAllEmployees\n");
		sb.append("       ...?action=getDepartmentEmployees&deptName=<deptName>\n");
		sb.append("       ...?action=addDepartment&deptName=<deptName>\n");
		sb.append("       ...?action=addEmployee&deptName=<deptName>&firstName=<firstName>&lastName=<lastName>\n");
		sb.append("    </pre>");

		response.getWriter().write(sb.toString());
	}

	private void doAction(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			MyCompanyException {

		writeHead(response);

		String action = request.getParameter("action");

		if (action == null) {
			usage(response);
		} else {
			if (action.equalsIgnoreCase("getAllEmployees")) {
				doGetAllEmployees(request, response);
			} else if (action.equalsIgnoreCase("getDepartmentEmployees")) {
				doGetDepartmentEmployees(request, response);
			} else if (action.equalsIgnoreCase("addDepartment")) {
				doAddDepartment(request, response);
			} else if (action.equalsIgnoreCase("addEmployee")) {
				doAddEmployee(request, response);
			} else {
				usage(response);
			}
		}

		writeTail(response);

		return;
	}

	private void doGetAllEmployees(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			MyCompanyException {

		List<Employee> emplList = myCompanyLogic.getAllCompanyEmployees();

		StringBuilder sb = new StringBuilder();

		sb.append("    <h1>All Employees:</h1>");

		if (emplList != null && emplList.size() > 0) {

			sb.append("    <table>");
			sb.append("      <thead>");
			sb.append("        <th>Department</th>");
			sb.append("        <th>First Name</th>");
			sb.append("        <th>Last Name</th>");
			sb.append("      </thead>");

			for (Employee empl : emplList) {
				sb.append("      <tr>");
				sb.append("        <td>")
						.append(empl.getDepartment().getName()).append("</td>");
				sb.append("        <td>").append(empl.getFirstName())
						.append("</td>");
				sb.append("        <td>").append(empl.getLastName())
						.append("</td>");
				sb.append("      </tr>");
			}

			sb.append("    </table>");
		} else {
			sb.append("<h3>No employees found</h3>");
		}

		response.getWriter().write(sb.toString());
	}

	private void doGetDepartmentEmployees(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			MyCompanyException {

		String deptName = (String) request.getParameter("deptName");

		if (deptName == null) {
			usage(response);
			return;
		}

		List<Employee> emplList = myCompanyLogic
				.getDepartmentEmployees(deptName);

		StringBuilder sb = new StringBuilder();

		sb.append("    <h1>Department ").append(deptName)
				.append(" Employees:</h1>");

		if (emplList != null && emplList.size() > 0) {

			sb.append("    <table>");
			sb.append("      <thead>");
			sb.append("        <th>Department</th>");
			sb.append("        <th>First Name</th>");
			sb.append("        <th>Last Name</th>");
			sb.append("      </thead>");

			for (Employee empl : emplList) {
				sb.append("      <tr>");
				sb.append("        <td>")
						.append(empl.getDepartment().getName()).append("</td>");
				sb.append("        <td>").append(empl.getFirstName())
						.append("</td>");
				sb.append("        <td>").append(empl.getLastName())
						.append("</td>");
				sb.append("      </tr>");
			}

			sb.append("    </table>");
		} else {
			sb.append("<h3>No employees found</h3>");
		}

		response.getWriter().write(sb.toString());
	}

	private void doAddDepartment(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			MyCompanyException {

		String deptName = (String) request.getParameter("deptName");

		if (deptName == null) {
			usage(response);
			return;
		}

		myCompanyLogic.addDepartment(deptName);

		StringBuilder sb = new StringBuilder();

		sb.append("    <h1>Department ").append(deptName)
				.append(" has been added.</h1>");

		response.getWriter().write(sb.toString());
	}

	private void doAddEmployee(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			MyCompanyException {

		String deptName = (String) request.getParameter("deptName");
		String firstName = (String) request.getParameter("firstName");
		String lastName = (String) request.getParameter("lastName");

		if (deptName == null || firstName == null || lastName == null) {
			usage(response);
			return;
		}

		myCompanyLogic.addEmployee(deptName, firstName, lastName);

		StringBuilder sb = new StringBuilder();

		sb.append("    <h1>Employee ").append(firstName).append(" ")
				.append(lastName).append(" has been added.</h1>");

		response.getWriter().write(sb.toString());
	}

	private void writeHead(HttpServletResponse response) throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("<html>");
		sb.append("  <head><title>SAP Mobile Platform :: Server Sample :: Persistence - WAB Tester</title></head>");
		sb.append("  <body>");

		response.getWriter().write(sb.toString());
	}

	private void writeTail(HttpServletResponse response) throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("  </body>");
		sb.append("</html>");

		response.getWriter().write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}

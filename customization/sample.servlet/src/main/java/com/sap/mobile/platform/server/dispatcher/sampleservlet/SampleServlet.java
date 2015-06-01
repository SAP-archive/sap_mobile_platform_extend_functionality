/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.dispatcher.sampleservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author <a href='mailto:Hemal.Pandya@sap.com'>Hemal Pandya</a>
 * @author <a href='mailto:Andrew.Clemons@sap.com'>Andrew Clemons</a>
 */
@SuppressWarnings("serial")
public final class SampleServlet extends GenericServlet {

	public SampleServlet() {
		System.out.println(String.format("<init> %s",this.getClass().getName()));
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		final PrintWriter out = res.getWriter();
		out.println("Service processed by " + getServletName() + " at URL "
				+ url(req));
	}

	private static String url(ServletRequest request) {
		return (request instanceof HttpServletRequest) ? ((HttpServletRequest) request)
				.getRequestURL().toString() : "???";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		super.destroy();
	}
}

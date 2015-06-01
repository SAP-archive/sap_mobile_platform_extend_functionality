/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.dispatcher.samplefilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

/**
 * @author <a href='mailto:hemal.pandya@sap.com'>Hemal Pandya</a>
 * @author <a href='mailto:Andrew.Clemons@sybase.com'>Andrew Clemons</a>
 */
public class Filter2 extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		((HttpServletResponse) response).setHeader(
				"X-SMP-Filter-2-" + System.nanoTime(), "true");

		chain.doFilter(request, response);
	}

}

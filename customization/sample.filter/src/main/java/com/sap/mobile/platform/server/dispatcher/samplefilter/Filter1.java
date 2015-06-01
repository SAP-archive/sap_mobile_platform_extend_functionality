package com.sap.mobile.platform.server.dispatcher.samplefilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

//import com.sap.mobile.platform.server.dispatcher.servlet.Logger;
//import com.sap.mobile.platform.server.dispatcher.servlet.LoggerFactory;


public class Filter1 extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			doFilter((HttpServletRequest) request,
					(HttpServletResponse) response, chain);
		} else {				
			throw new IllegalStateException();
		}
		
	}

	private final static String PUBLIC = "/public";
	private final static String PUBLIC_FILTER = "noauthfilter";

	void doFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException {

			String header = "X-SMP-Filter-" + getFilterName();
			response.setHeader(header,
					request.getRequestURI() + ":" + System.nanoTime());
			if (getFilterName().equals(PUBLIC_FILTER)) {
				String uri = request.getRequestURI();
				if (!uri.startsWith(PUBLIC)) {
					System.out.println(String.format("Request in filter %s does not start with %s, cannot forward: %s",
							PUBLIC_FILTER, PUBLIC, uri));
				} else {
					// this hijacks the /public/** url pattern, so it should behave exactly like
					// the PublicAccessFilter, otherwise test against /public would fail.
					request.setAttribute(
							"com.sap.mobile.platform.server.security.http.anonymous",
							Boolean.TRUE);
					String newUri = uri.substring(PUBLIC.length());
					System.out.println(String.format(PUBLIC_FILTER + " forwarding %s to %s", uri, newUri));
					request.getRequestDispatcher(newUri).forward(request,
							response);
				}
			} else {
				chain.doFilter(request, response);
			}
	
	}

	@Override
	public String toString() {
		return "X-SMP-Filter-" + getFilterName() + "-"
				+ System.identityHashCode(this);
	}

}

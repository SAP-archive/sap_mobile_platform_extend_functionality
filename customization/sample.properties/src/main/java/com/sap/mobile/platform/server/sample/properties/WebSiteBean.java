/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.properties;

import org.springframework.beans.factory.InitializingBean;

public class WebSiteBean implements InitializingBean {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int port = -1;

	private String protocol;

	private String server;

	private String uri;

	public int getPort() {
		return this.port;
	}

	public void setport(int port) {
		this.port = port;
	}

	public String getServer() {
		return this.server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (protocol == null || server == null || port == -1 || uri == null) {
			throw new IllegalArgumentException(
					"The following properties are required; protocol, server, port and uri");
		}
	}

	public void init() {
		StringBuilder url = new StringBuilder();
		url.append(protocol).append("://");
		url.append(server).append(":").append(String.valueOf(port));
		url.append(uri);

		System.out.print("The full URL is: " + url.toString());
	}

}

/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.log;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author all
 */
public final class CustomSessionLog extends AbstractSessionLog implements
		SessionLog {

	/**
	 * SLF4J adapter.
	 */
	public static final Logger LOGGER = LoggerFactory
			.getLogger(CustomSessionLog.class);

	@Override
	public void log(final SessionLogEntry sle) {
		switch (sle.getLevel()) {
		case SEVERE:
			LOGGER.error(sle.getMessage());
			break;
		case WARNING:
			LOGGER.warn(sle.getMessage());
			break;
		case INFO:
			LOGGER.info(sle.getMessage());
			break;
		default:
			LOGGER.debug(sle.getMessage());
			break;
		}
	}
}

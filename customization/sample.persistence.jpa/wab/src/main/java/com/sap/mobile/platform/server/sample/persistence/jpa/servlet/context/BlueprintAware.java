/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.servlet.context;

import org.eclipse.gemini.blueprint.context.BundleContextAware;
import org.osgi.framework.BundleContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author all
 */
public final class BlueprintAware implements ApplicationContextAware,
		BundleContextAware {

	@Override
	public void setApplicationContext(final ApplicationContext appContext) {
		BlueprintAdapter.setContext(appContext);
	}

	@Override
	public void setBundleContext(BundleContext bundleContext) {
		BlueprintAdapter.setBundleContext(bundleContext);
	}
}

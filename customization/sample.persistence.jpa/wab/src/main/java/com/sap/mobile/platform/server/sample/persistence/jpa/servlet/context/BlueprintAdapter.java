/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.servlet.context;

import org.osgi.framework.BundleContext;
import org.springframework.context.ApplicationContext;

import com.sap.mobile.platform.server.sample.persistence.jpa.logic.api.IMyCompanyLogic;

/**
 * Glue code for legacy classes not managed by Spring.
 * 
 * @author all
 */
public final class BlueprintAdapter {

	private static ApplicationContext context;

	private static BundleContext bundleContext;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(final ApplicationContext appContext) {
		context = appContext;
	}

	public static BundleContext getBundleContext() {
		return bundleContext;
	}

	public static void setBundleContext(BundleContext bundleContext) {
		BlueprintAdapter.bundleContext = bundleContext;
	}

	public static IMyCompanyLogic getMyCompanyLogic() {
		return (IMyCompanyLogic) BlueprintAdapter.getContext().getBean(
				"myCompanyLogic");
	}
}

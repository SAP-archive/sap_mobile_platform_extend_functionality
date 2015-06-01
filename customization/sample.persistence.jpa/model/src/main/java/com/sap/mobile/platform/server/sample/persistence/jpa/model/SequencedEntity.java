/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author all
 */
@MappedSuperclass
public class SequencedEntity {

	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SAMPLE")
	@SequenceGenerator(name = "SEQ_SAMPLE", sequenceName = "SEQ_SAMPLE", allocationSize = 50)
	protected Long id;

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_CREATION")
	protected Date createdAt;

	@Basic(optional = true)
	@Column(name = "ID_CUSTOMER_CREATION")
	protected Long createdBy;

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_LAST_UPDATE")
	protected Date lastUpdateAt;

	@Basic(optional = true)
	@Column(name = "ID_CUSTOMER_LAST_UPDATE")
	protected Long lastUpdateBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date value) {
		this.createdAt = value;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long value) {
		this.createdBy = value;
	}

	public Date getLastUpdateAt() {
		return this.lastUpdateAt;
	}

	public void setLastUpdateAt(Date value) {
		this.lastUpdateAt = value;
	}

	public Long getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(Long value) {
		this.lastUpdateBy = value;
	}
}

/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author all
 */
@Entity
@Table(name = "SMP_DEPARTMENT")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Department.findAll", query = "SELECT e FROM Department e"),
		@NamedQuery(name = "Department.findById", query = "SELECT e FROM Department e WHERE e.id = :id"),
		@NamedQuery(name = "Department.findByName", query = "SELECT e FROM Department e WHERE e.name = :name") })
public class Department extends SequencedEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "STR_NAME")
	private String name;

	public Department() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

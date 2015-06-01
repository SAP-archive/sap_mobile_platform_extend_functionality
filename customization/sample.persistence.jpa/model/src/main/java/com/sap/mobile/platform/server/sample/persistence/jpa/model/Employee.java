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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author all
 */
@Entity
@Table(name = "SMP_EMPLOYEE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Employee.findAll", query = "SELECT d FROM Employee d"),
		@NamedQuery(name = "Employee.findById", query = "SELECT d FROM Employee d WHERE d.id = :id"),
		@NamedQuery(name = "Employee.findByLastName", query = "SELECT d FROM Employee d WHERE d.lastName = :lastName") })
public class Employee extends SequencedEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "STR_FIRST_NAME")
	private String firstName;

	@Basic(optional = true)
	@Column(name = "STR_LAST_NAME")
	private String lastName;

	// Using @ManyToOne(optional = false, cascade = CascadeType.ALL) causes
	// store of Employee with existing Department to fail - need to research
	// why the CascadeType.ALL causes this; just use default @ManyToOne as
	// workaround for now
	@JoinColumn(name = "ID_DEPT", referencedColumnName = "ID")
	@ManyToOne
	private Department dept;

	public Employee() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Department getDepartment() {
		return dept;
	}

	public void setDepartment(Department dept) {
		this.dept = dept;
	}

}

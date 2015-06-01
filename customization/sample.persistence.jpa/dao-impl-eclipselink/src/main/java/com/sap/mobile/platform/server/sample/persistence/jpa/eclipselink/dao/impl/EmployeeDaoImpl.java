/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.eclipselink.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.mobile.platform.server.sample.persistence.jpa.dao.api.EmployeeDao;
import com.sap.mobile.platform.server.sample.persistence.jpa.model.Employee;

public class EmployeeDaoImpl extends BaseDaoImpl<Employee, Long> implements
		EmployeeDao {

	static final Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	/**
	 * Default constructor
	 */
	public EmployeeDaoImpl() {
		super();
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	@Override
	public Employee getById(Long id) {
		Employee result = null;
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Employee> query = em
					.createQuery("SELECT e from Employee e WHERE e.id = :id",
							Employee.class);
			query.setParameter("id", id);
			result = (Employee) query.getSingleResult();
		} catch (NoResultException nre) {
			// just return null result
		} finally {
			if (em != null) {
				em.close();
			}
		}

		return result;
	}

	/**
     */
	@Override
	public List<Employee> getAll() {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT e from Employee e", Employee.class)
					.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
     */
	public List<Employee> getByLastName(final String name) {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Employee> query = em.createQuery(
					"SELECT e FROM Employee e WHERE e.lastName = :lastName",
					Employee.class);
			query.setParameter("lastName", name);
			return query.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}

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

import com.sap.mobile.platform.server.sample.persistence.jpa.dao.api.DepartmentDao;
import com.sap.mobile.platform.server.sample.persistence.jpa.model.Department;

public class DepartmentDaoImpl extends BaseDaoImpl<Department, Long> implements
		DepartmentDao {

	static final Logger log = LoggerFactory.getLogger(DepartmentDaoImpl.class);

	/**
	 * Default constructor
	 */
	public DepartmentDaoImpl() {
		super();
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	@Override
	public Department getById(Long id) {
		Department result = null;
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Department> query = em.createQuery(
					"SELECT d from Department d WHERE d.id = :id",
					Department.class);
			query.setParameter("id", id);
			result = (Department) query.getSingleResult();
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
	public List<Department> getAll() {
		EntityManager em = getEntityManager();
		try {
			return em.createQuery("SELECT d from Department d",
					Department.class).getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
     */
	public List<Department> getByName(final String name) {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Department> query = em.createQuery(
					"SELECT d from Department d WHERE d.name = :name",
					Department.class);
			query.setParameter("name", name);
			return query.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}

/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.eclipselink.dao.impl;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sap.mobile.platform.server.sample.persistence.jpa.dao.api.BaseDao;
import com.sap.mobile.platform.server.sample.persistence.jpa.model.SequencedEntity;

public abstract class BaseDaoImpl<T extends SequencedEntity, ID extends Serializable>
		implements BaseDao<T, ID> {

	static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	protected EntityManagerFactory entityManagerFactory;

	/**
	 * Default constructor
	 */
    public BaseDaoImpl() {
        super();
    }

    public void setEntityManagerFactory(EntityManagerFactory value) {
        this.entityManagerFactory = value;
    }

    public void unsetEntityManagerFactory() {
        this.entityManagerFactory = null;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return this.entityManagerFactory;
    }

	@Override
	public T newInstance() {
		try {
			return getEntityClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Error creating instance of class "
					+ getEntityClass(), e);
		}
	}

	/**
     */
	@Override
	public abstract T getById(ID id);

	/**
     */
	@Override
	public abstract List<T> getAll();

	/**
     */
	@Override
	public T store(T object) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			if (object.getId() != null) {
				em.merge(object);
			} else {
				em.persist(object);
			}

			em.getTransaction().commit();

			return object;

		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public T update(T object) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			if (object.getId() != null) {
				em.merge(object);
			}

			em.getTransaction().commit();

			return object;

		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
     */
	public void delete(ID id) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();

			T object = getById(id);

			if (object != null) {
				em.remove(object);
			}

			em.getTransaction().commit();

		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public final void flush() {
		EntityManager em = getEntityManager();
		em.flush();
	}

	protected EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
}

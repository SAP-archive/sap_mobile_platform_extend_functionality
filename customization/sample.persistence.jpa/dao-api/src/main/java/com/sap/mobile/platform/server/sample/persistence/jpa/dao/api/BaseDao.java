/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.dao.api;

import java.io.Serializable;
import java.util.List;

import com.sap.mobile.platform.server.sample.persistence.jpa.model.SequencedEntity;

public interface BaseDao<T extends SequencedEntity, ID extends Serializable> {

	Class<T> getEntityClass();

	T newInstance();

	T getById(ID id);

	List<T> getAll();

	T update(T object);

	T store(T object);

	void delete(ID id);

	void flush();
}

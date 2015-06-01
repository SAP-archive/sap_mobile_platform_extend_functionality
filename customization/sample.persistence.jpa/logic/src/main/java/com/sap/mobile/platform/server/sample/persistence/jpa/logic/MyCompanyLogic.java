/**
 * (c) 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 * 
 * No part of this publication may be reproduced or transmitted in any form or for any purpose 
 * without the express permission of SAP AG.  The information contained herein may be changed 
 * without prior notice.
 */
package com.sap.mobile.platform.server.sample.persistence.jpa.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import com.sap.mobile.platform.server.sample.persistence.jpa.logic.api.IMyCompanyLogic;
import com.sap.mobile.platform.server.sample.persistence.jpa.logic.api.MyCompanyException;
import com.sap.mobile.platform.server.sample.persistence.jpa.dao.api.DepartmentDao;
import com.sap.mobile.platform.server.sample.persistence.jpa.dao.api.EmployeeDao;
import com.sap.mobile.platform.server.sample.persistence.jpa.model.Department;
import com.sap.mobile.platform.server.sample.persistence.jpa.model.Employee;

/**
 * 
 * @author all
 */
public class MyCompanyLogic implements InitializingBean, IMyCompanyLogic {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyCompanyLogic.class);

	@Resource(name = "departmentDao")
	private DepartmentDao departmentDao;

	@Resource(name = "employeeDao")
	private EmployeeDao employeeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (departmentDao == null) {
			throw new IllegalArgumentException("departmentDao is not set");
		} else if (employeeDao == null) {
			throw new IllegalArgumentException("employeeDao is not set");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sap.mobile.platform.server.sample.persistence.logic.api.IMyCompanyLogic
	 * #addDepartment(java.lang.String)
	 */
	@Override
	public void addDepartment(String deptName) throws MyCompanyException {

		List<Department> deptList = departmentDao.getByName(deptName);

		if (deptList != null && deptList.size() > 0) {
			throw new MyCompanyException("Department " + deptName
					+ " already exists");
		}

		Department dept = new Department();
		dept.setName(deptName);

		departmentDao.store(dept);

		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sap.mobile.platform.server.sample.persistence.logic.api.IMyCompanyLogic
	 * #addEmployee(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addEmployee(String deptName, String firstName, String lastName)
			throws MyCompanyException {

		List<Department> deptList = departmentDao.getByName(deptName);

		if (deptList == null || (deptList != null && deptList.size() == 0)) {
			throw new MyCompanyException("Department " + deptName
					+ " does not exist");
		}

		Department dept = null;

		for (Department deptEntry : deptList) {
			if (deptEntry.getName().equals(deptName)) {
				dept = deptEntry;
			}
		}

		Employee empl = new Employee();
		empl.setFirstName(firstName);
		empl.setLastName(lastName);
		empl.setDepartment(dept);

		employeeDao.store(empl);

		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sap.mobile.platform.server.sample.persistence.logic.api.IMyCompanyLogic
	 * #getDepartmentEmployees(java.lang.String)
	 */
	@Override
    public List<Employee> getDepartmentEmployees(String deptName) throws MyCompanyException {

        List<Employee> emplList = employeeDao.getAll();

        List<Employee> listOfDept = new ArrayList<Employee>();

        if (emplList == null || (emplList != null && emplList.size() == 0)) {
            return Collections.emptyList();
        }

        for (Employee emplEntry : emplList) {
            if (emplEntry.getDepartment().getName().equals(deptName)) {
                listOfDept.add(emplEntry);
            }
        }

        return listOfDept;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sap.mobile.platform.server.sample.persistence.logic.api.IMyCompanyLogic
	 * #getAllCompanyEmployees()
	 */
	@Override
	public List<Employee> getAllCompanyEmployees() throws MyCompanyException {

		List<Employee> emplList = employeeDao.getAll();

		if (emplList == null || (emplList != null && emplList.size() == 0)) {
			return Collections.emptyList();
		}

		return emplList;

	}

}

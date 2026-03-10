package com.mcst.module.auth.orm.hibernate.persistence.repository;

import com.mcst.module.auth.orm.hibernate.persistence.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeJpaRepository extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
}

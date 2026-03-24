package com.mcst.module.auth.orm.hibernate.persistence.mapper;

import com.mcst.module.auth.orm.hibernate.persistence.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentMapper extends JpaRepository<Department, String>, JpaSpecificationExecutor<Department> {
}

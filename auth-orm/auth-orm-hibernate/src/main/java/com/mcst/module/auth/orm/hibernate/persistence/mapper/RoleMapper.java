package com.mcst.module.auth.orm.hibernate.persistence.mapper;

import com.mcst.module.auth.orm.hibernate.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleMapper extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
}

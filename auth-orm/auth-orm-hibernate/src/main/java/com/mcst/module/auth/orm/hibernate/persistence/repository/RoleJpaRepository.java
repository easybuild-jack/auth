package com.mcst.module.auth.orm.hibernate.persistence.repository;

import com.mcst.module.auth.orm.hibernate.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleJpaRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
}

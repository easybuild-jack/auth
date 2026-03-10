package com.mcst.module.auth.orm.hibernate.persistence.repository;

import com.mcst.module.auth.orm.hibernate.persistence.model.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleResourceJpaRepository extends JpaRepository<RoleResource, String>, JpaSpecificationExecutor<RoleResource> {
}

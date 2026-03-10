package com.mcst.module.auth.orm.hibernate.persistence.repository;

import com.mcst.module.auth.orm.hibernate.persistence.model.AuthResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthResourceJpaRepository extends JpaRepository<AuthResource, String>, JpaSpecificationExecutor<AuthResource> {
}

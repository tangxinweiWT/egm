package com.pyming.demo.infrastructure.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <PO> extends JpaRepository<PO, String>, JpaSpecificationExecutor<PO> {
}

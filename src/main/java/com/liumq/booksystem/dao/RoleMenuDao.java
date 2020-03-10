package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleMenuDao extends JpaRepository<RoleMenu, Integer>, JpaSpecificationExecutor<RoleMenu> {
}

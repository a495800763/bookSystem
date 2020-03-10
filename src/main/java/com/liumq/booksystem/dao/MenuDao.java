package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuDao extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {
}

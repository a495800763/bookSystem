package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookDao extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
  //JpaRepository Jpa简单查询
  // JpaSpecificationExecutor Jpa复杂动态条件查询
}

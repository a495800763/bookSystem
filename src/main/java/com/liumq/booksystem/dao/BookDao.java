package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookDao extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query(value = "select * from book where id =?1", nativeQuery = true)
    Book findId(Integer id);

    @Query(value = "delete  from book where id =?1", nativeQuery = true)
    void deleteById(Integer id);
}

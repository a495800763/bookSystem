package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookTypeDao extends JpaRepository<BookType, Integer>, JpaSpecificationExecutor<BookType> {

    @Query(value="select * from BookType where id =?1",nativeQuery = true)
     BookType findId (Integer id);
}

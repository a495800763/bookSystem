package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfigDao extends JpaRepository<Config,Integer> {
    @Query(value="select * from config where id = ?1",nativeQuery = true)
     Config findId(Integer id);
}

package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    @Query(value="select * from user where name = ?1",nativeQuery = true)
    User findByName (String name );

    @Query(value="select * from user where id = ?1",nativeQuery = true)
    User findId (Integer id);
}

package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RoleDao extends JpaRepository<Role,Integer>, JpaSpecificationExecutor<Role> {

    @Query(value = "select * from role where is = ?1",nativeQuery = true)
    Role findId (Integer id);
}

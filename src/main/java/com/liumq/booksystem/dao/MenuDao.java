package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {

    @Query(value="select * from menu where id =?1",nativeQuery = true)
     Menu findId (Integer id);

    List<Menu> findBypId(Integer pId);
}

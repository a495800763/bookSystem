package com.liumq.booksystem.service;

import com.liumq.booksystem.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {


     Integer update(Menu menu);

     Integer add(Menu menu);

     List<Menu> list(Map<String,Object> map, Integer page, Integer pageSize);

     Long getTotal(Map<String,Object> map);

     Menu findById(Integer id);

     Integer delete(Integer id);

     List<Menu> findByPId (Integer pId);
}

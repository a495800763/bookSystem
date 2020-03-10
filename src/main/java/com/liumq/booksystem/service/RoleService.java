package com.liumq.booksystem.service;

import com.liumq.booksystem.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {


     void add (Role role);

     Integer update(Role role);


     List<Role>  list(Map<String, Object> map, Integer page, Integer pageSize);

     Long getTotal(Map<String, Object> map);





}

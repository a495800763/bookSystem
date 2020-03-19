package com.liumq.booksystem.service;

import com.liumq.booksystem.entity.User;
import java.util.List;
import java.util.Map;

public interface UserService {


    User findByName (String name);

    User findById (Integer id);

    void save(User user);

    void update(User user);

    List<User> list(Map<String,Object> map , Integer page, Integer pageSize);

    Long getTotal (Map<String,Object> map);

    void delete (Integer id);


}

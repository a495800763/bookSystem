package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.ConfigDao;
import com.liumq.booksystem.dao.UserDao;
import com.liumq.booksystem.entity.User;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public interface UserService {


    User findByName (String name);

    void save(User user);

    void update(User user);

    List<User> list(Map<String,Object> map , Integer page, Integer pageSize);

    Long getTotal (Map<String,Object> map);


}

package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.ConfigDao;
import com.liumq.booksystem.dao.UserDao;
import com.liumq.booksystem.entity.User;

import javax.annotation.Resource;

public interface UserService {


    User findByName (String name);
}

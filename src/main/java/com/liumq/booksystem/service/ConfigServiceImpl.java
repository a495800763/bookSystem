package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.ConfigDao;
import com.liumq.booksystem.entity.Config;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("ConfigService")
public class ConfigServiceImpl implements  ConfigService {


    @Resource
    private ConfigDao configDao;


    @Override
    public Config findById(Integer id) {
       return  configDao.findId(id);
    }
}

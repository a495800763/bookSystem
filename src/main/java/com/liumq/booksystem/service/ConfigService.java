package com.liumq.booksystem.service;

import com.liumq.booksystem.entity.Config;
import org.springframework.stereotype.Component;


public interface ConfigService {
    Config findById(Integer id);
}

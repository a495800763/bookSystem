package com.liumq.booksystem.service;

import com.liumq.booksystem.entity.BookType;

import java.util.List;
import java.util.Map;

public interface BookTypeService {

    void update(BookType bookType);

    List<BookType> listByCondition(Map<String, Object> map, Integer page, Integer pageSize);

    Long getTotal(Map<String, Object> map);

    void add(BookType bookType);

    void deleteById(Integer id);

    BookType findId(Integer id);
}

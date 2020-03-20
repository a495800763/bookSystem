package com.liumq.booksystem.service;

import com.liumq.booksystem.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    void update(Book book);

    List<Book> listByCondition(Map<String, Object> map, Integer page, Integer pageSize);

    Long getTotal(Map<String, Object> map);

    void add(Book book);

    void deleteById(Integer id);

    Book findId(Integer id);

}

package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.BookDao;
import com.liumq.booksystem.entity.Book;
import com.liumq.booksystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("BookService")
public class BookServiceImpl implements BookService {

    @Resource
    private BookDao bookDao;

    @Override
    public void update(Book book) {
        Book origin = bookDao.findId(book.getId());

        book = replace(book, origin);
        bookDao.save(book);
        return;
    }

    @Override
    public List<Book> listByCondition(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "orderNo");
        Page<Book> list = bookDao.findAll(pageable);
        List<Book> books = list.getContent();
        return books;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return bookDao.count();
    }

    @Override
    public void add(Book book) {
        bookDao.save(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book findId(Integer id) {
        return bookDao.findId(id);
    }

    /**
     * 数据补全
     * @param curr
     * @param origin
     * @return
     */
    private Book replace(Book curr, Book origin) {
        if (curr.getName() == null) {
            curr.setName(origin.getName());
        }
        if (curr.getOrderNo() == null) {
            curr.setOrderNo(origin.getOrderNo());
        }
        if (curr.getAuthor() == null) {
            curr.setAuthor(origin.getAuthor());
        }
        if (curr.getCreateDateTime() == null) {
            curr.setCreateDateTime(origin.getCreateDateTime());
        }
        if (curr.getImageUrl() == null) {
            curr.setImageUrl(origin.getImageUrl());
        }
        if (curr.getPress() == null) {
            curr.setPress(origin.getPress());
        }
        if (curr.getPrice() == null) {
            curr.setPrice(origin.getPrice());
        }
        if (curr.getUpdateDateTime() == null) {
            curr.setUpdateDateTime(origin.getUpdateDateTime());
        }
        if (curr.getBookType() == null) {
            curr.setBookType(origin.getBookType());
        }

        return curr;
    }
}

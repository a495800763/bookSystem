package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.BookTypeDao;
import com.liumq.booksystem.entity.BookType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("BookTypeService")
public class BookTypeServiceImpl implements BookTypeService {

    @Resource
    private BookTypeDao bookTypeDao;

    @Override
    public void update(BookType bookType) {
        BookType origin = bookTypeDao.findId(bookType.getId());

        bookType = replace(bookType, origin);
        bookTypeDao.save(bookType);
        return;
    }

    @Override
    public List<BookType> listByCondition(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "orderNo");
        Page<BookType> list = bookTypeDao.findAll(pageable);
        List<BookType> bookTypes = list.getContent();
        return bookTypes;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return bookTypeDao.count();
    }

    @Override
    public void add(BookType bookType) {
          bookTypeDao.save(bookType);
    }

    @Override
    public void deleteById(Integer id) {
        bookTypeDao.deleteById(id);
    }

    @Override
    public BookType findId(Integer id) {
         return bookTypeDao.findId(id);
    }


    private BookType replace(BookType curr, BookType origin) {
        if (curr.getName() == null) {
            curr.setName(origin.getName());
        }
        if (curr.getOrderNo() == null) {
            curr.setOrderNo(origin.getOrderNo());
        }
        if (curr.getUpdateDateTime() == null) {
            curr.setUpdateDateTime(origin.getUpdateDateTime());
        }
        if (curr.getCreateDateTime() == null) {
            curr.setCreateDateTime(origin.getCreateDateTime());
        }
        return curr;
    }
}

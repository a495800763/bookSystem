package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.BookDao;
import com.liumq.booksystem.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        Page<Book> books = bookDao.findAll(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (map.get("bookType") != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("bookType"), map.get("bookType")));
                }
                return predicate;
            }
        }, pageable);
        return  books.getContent();
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        Long count = bookDao.count(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (map.get("bookType") != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("bookType"), map.get("bookType")));
                }
                return predicate;
            }
        });
        return count;
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

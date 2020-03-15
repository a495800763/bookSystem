package com.liumq.booksystem.service;


import com.liumq.booksystem.dao.MenuDao;
import com.liumq.booksystem.entity.Menu;
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

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public Integer update(Menu menu) {
        Menu origin = menuDao.findId(menu.getId());
        menu = replace(menu, origin);
        menuDao.save(menu);
        return 1;
    }

    @Override
    public Integer add(Menu menu) {
        menuDao.save(menu);
        return 1;
    }

    @Override
    public List<Menu> list(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "orderNo");
        Page<Menu> pages = menuDao.findAll(new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (map.get("pId") != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("pId"), map.get("pId")));
                }
                return predicate;
            }
        }, pageable);
        return pages.getContent();
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        Long count = menuDao.count(new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (map.get("pId") != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("pId"), map.get("pId")));
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public Menu findById(Integer id) {
        return menuDao.findId(id);
    }

    @Override
    public Integer delete(Integer id) {
        menuDao.delete(menuDao.findId(id));
        return 1;
    }

    @Override
    public List<Menu> findByPId(Integer pId) {
       return menuDao.findByPId(pId);
    }


    public Menu replace(Menu curr, Menu origin) {
        if (curr.getId() == null) {
            curr.setId(origin.getId());
        }
        if (curr.getName() == null) {
            curr.setName(origin.getName());
        }
        if (curr.getIcon() == null) {
            curr.setIcon(origin.getIcon());
        }
        if (curr.getOrderNo() == null) {
            curr.setOrderNo(origin.getOrderNo());
        }
        if (curr.getPermissions() == null) {
            curr.setPermissions(origin.getPermissions());
        }
        if (curr.getpId() == null) {
            curr.setpId(origin.getpId());
        }
        if (curr.getState() == null) {
            curr.setState(origin.getState());
        }
        if (curr.getType() == null) {
            curr.setType(origin.getType());
        }
        if (curr.getUrl() == null) {
            curr.setUrl(origin.getUrl());
        }
        return curr;
    }
}

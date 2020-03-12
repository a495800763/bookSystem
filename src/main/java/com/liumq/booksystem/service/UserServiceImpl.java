package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.UserDao;
import com.liumq.booksystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findId(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        User origin = userDao.findId(user.getId());

        user = replace(user, origin);
        userDao.save(user);
        return;
    }

    @Override
    public List<User> list(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "orderNo");
        Page<User> list = userDao.findAll(pageable);
        List<User> users = list.getContent();
        return users;
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return userDao.count();
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteById(id);
    }

    /**
     * fillInfo
     *
     * @param curr
     * @param origin
     * @return
     */
    public User replace(User curr, User origin) {
        if (curr.getName() == null) {
            curr.setName(origin.getName());
        }
        if (curr.getPwd() == null) {
            curr.setPwd(origin.getPwd());
        }
        if (curr.getTrueName() == null) {
            curr.setTrueName(origin.getTrueName());
        }
        if (curr.getRemark() == null) {
            curr.setRemark(origin.getRemark());
        }
        if (curr.getOrderNo() == null) {
            curr.setOrderNo(origin.getOrderNo());
        }
        if (curr.getCreateDateTime() == null) {
            curr.setCreateDateTime(origin.getCreateDateTime());
        }
        if (curr.getRole() == null) {
            curr.setRole(origin.getRole());
        }
        if (curr.getUpdateDateTime() == null) {
            curr.setUpdateDateTime(origin.getUpdateDateTime());
        }
        return curr;

    }
}

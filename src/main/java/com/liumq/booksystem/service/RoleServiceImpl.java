package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.MenuDao;
import com.liumq.booksystem.dao.RoleDao;
import com.liumq.booksystem.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private MenuDao menuDao;

    @Override
    public void add(Role role) {
        roleDao.save(role);
    }

    @Override
    public Integer update(Role role) {
        Role origin = roleDao.findId(role.getId());
        role = replace(role, origin);
        roleDao.save(role);
        return 1;

    }

    @Override
    public Role findId(Integer id) {
        return roleDao.findId(id);
    }

    @Override
    public List<Role> list(Map<String, Object> map, Integer page, Integer pageSize) {
        Pageable pageable =  PageRequest.of(page,pageSize, Sort.Direction.ASC,"orderNo");
        Page<Role> list= roleDao.findAll(pageable);
        return list.getContent();
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return roleDao.count();
    }

    @Override
    public void delete(Integer id) {
        roleDao.deleteById(id);
    }


    public Role replace(Role curr, Role origin) {
        if (curr.getCreateDateTime() == null) {
            curr.setCreateDateTime(origin.getCreateDateTime());
        }
        if (curr.getName() == null) {
            curr.setName(origin.getName());
        }
        if (curr.getOrderNo() == null) {
            curr.setOrderNo(origin.getOrderNo());
        }
        if (curr.getUpdateDateTime() == null) {
            curr.setUpdateDateTime(origin.getUpdateDateTime());
        }

        return curr;
    }

}

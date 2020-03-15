package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.RoleMenuDao;
import com.liumq.booksystem.entity.RoleMenu;

public class RoleMenuServiceImpl implements RoleMenuService {


    private RoleMenuDao roleMenuDao;


    @Override
    public RoleMenu findByRoleIdAndMenuId(Integer roleId, Integer menuId) {
        return roleMenuDao.findByRoleIdAndMenuId(roleId, menuId);
    }

    @Override
    public Integer deleteByRoleId(Integer roleId) {
        return roleMenuDao.deleteByRoleId(roleId);
    }

    @Override
    public RoleMenu findId(Integer id) {
        return roleMenuDao.findId(id);
    }
}

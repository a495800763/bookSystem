package com.liumq.booksystem.service;

import com.liumq.booksystem.entity.RoleMenu;

public interface RoleMenuService {

    RoleMenu findByRoleIdAndMenuId(Integer roleId, Integer menuId);

    Integer deleteByRoleId(Integer roleId);

    RoleMenu findId(Integer id);

    Integer add(RoleMenu roleMenu);
}

package com.liumq.booksystem.service;

import com.liumq.booksystem.dao.MenuDao;
import com.liumq.booksystem.dao.RoleDao;
import com.liumq.booksystem.entity.Role;
import com.liumq.booksystem.entity.RoleMenu;
import com.liumq.booksystem.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private MenuDao menuDao;

    @Resource
    private RoleMenuService roleMenuService;

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

    @Override
    public Integer updateMenu(Integer roleId, String menuIds) {
        String [] idsStr = menuIds.split(",");
        RoleMenu roleMenu;

        roleMenuService.deleteByRoleId(roleId);

        for(int i =0;i<idsStr.length;i++)
        {
            if(StringUtil.isNotEmpty(idsStr[i]))
            {
                roleMenu = new RoleMenu();
                roleMenu.setRole(roleDao.findId(roleId));
                roleMenu.setMenu(menuDao.findId(Integer.parseInt(idsStr[i])));
                roleMenuService.add(roleMenu);
            }
        }


        Role role = roleDao.findId(roleId);
        role.setUpdateDateTime(new Date());
        roleDao.save(role);

        return 1;
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

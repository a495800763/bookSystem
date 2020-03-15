package com.liumq.booksystem.dao;

import com.liumq.booksystem.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface RoleMenuDao extends JpaRepository<RoleMenu, Integer>, JpaSpecificationExecutor<RoleMenu> {

    /**
     * 根据两种Id 查询是否有对应的关联
     * @param roleId
     * @param menuId
     * @return
     */
    @Query(value = "select * from RoleMenu  where roleId=?1 and menuId = ?2",nativeQuery = true)
    RoleMenu findByRoleIdAndMenuId(Integer roleId,Integer menuId);

    /**
     * 设置权限时需要先清空之前的权限
     * @param roleId
     * @return
     */
    @Modifying
    @Transactional
    @Query(value="delete from RoleMenu  where roleId =?1",nativeQuery = true)
    Integer deleteByRoleId(Integer roleId);


    @Query(value = "select * from RoleMenu  where id = ?1",nativeQuery = true)
    RoleMenu findId(Integer id);
}

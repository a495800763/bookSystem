package com.liumq.booksystem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    //parentId
    private Integer pId;

    @Column(length = 50)
    private String name;

    @Column(length = 200)
    private String url;   //菜单地址

    @Column(length = 10)
    private Integer state; //菜单节点类型

    @Column(length = 100)
    private String icon; //图标

    @Column(length = 100)
    private String permissions;//对应的shiro权限

    @Column(length = 10)
    private Integer type;//选项卡还是窗口打开

    public String getDivId() {
        return divId;
    }

    public void setDivId(String divId) {
        this.divId = divId;
    }

    @Column(length = 50)
    private String divId; // layui 菜单Id

    @NotNull(message = "排序号不能为空")
    @Column(length = 10)
    private Integer orderNo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}

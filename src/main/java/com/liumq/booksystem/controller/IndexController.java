package com.liumq.booksystem.controller;

import com.liumq.booksystem.entity.Menu;
import com.liumq.booksystem.entity.RoleMenu;
import com.liumq.booksystem.entity.User;
import com.liumq.booksystem.service.MenuService;
import com.liumq.booksystem.service.RoleMenuService;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private ServletContext servletContext;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuService roleMenuService;

    @RequestMapping("/")
    public String index1(HttpServletResponse res, HttpServletRequest req) {
        return "redirect:/login";
    }


    @RequestMapping("/index")
    public String index(HttpServletResponse res, HttpServletRequest req) {
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletResponse res, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        String userAgent = req.getHeader("User-Agent");
        mav.setViewName("pc/login/login");
        return mav;
    }


    @RequestMapping("/admin/main")
    public ModelAndView adminMain(HttpServletResponse res, HttpServletRequest req) throws Exception {
        ModelAndView mav = new ModelAndView();
        String userAgent = req.getHeader("User-Agent");
        mav.setViewName("/admin/main");

        //使用shiro 从session 中得到user信息
        User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
        if (currentUser.getRole() == null) {
            return mav;
        }
        //根据当前用户对应角色展示菜单
        Map<String, Object> map = new HashMap<>();
        map.put("pId", -1);
        List<Menu> menuList = menuService.list(map, 0, 100);
        List<JSONObject> list = new ArrayList<>();
        for (Menu menu : menuList) {
            RoleMenu roleMenu = roleMenuService.findByRoleIdAndMenuId(currentUser.getRole().getId(), menu.getId());
            if(roleMenu!=null)
            {
                JSONObject node = new JSONObject();
                node.put("id",menu.getId());
                node.put("text",menu.getName());
                node.put("url",menu.getUrl());
                node.put("type",menu.getType());
                node.put("icon",menu.getIcon());
                node.put("divId",menu.getDivId());
                node.put("children",getChildren(menu.getId(),currentUser.getRole().getId()));
                list.add(node);
            }

        }

        mav.addObject("treeList",list);
        return mav;
    }

    private Object getChildren(Integer pId, Integer roleId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("pId", pId);
        List<Menu> menuList = menuService.list(map, 0, 100);
        List<JSONObject>  list = new ArrayList<>();
        for(Menu menu:menuList)
        {
            RoleMenu roleMenu = roleMenuService.findByRoleIdAndMenuId(roleId,menu.getId());
            if(roleMenu!=null)
            {
                JSONObject node = new JSONObject();
                node.put("id",menu.getId());
                node.put("text",menu.getName());
                node.put("url",menu.getUrl());
                node.put("type",menu.getType());
                node.put("icon",menu.getIcon());
                node.put("divId",menu.getDivId());
                list.add(node);
            }
        }
        return list;
    }
}

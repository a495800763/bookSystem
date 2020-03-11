package com.liumq.booksystem.controller.backstage;

import com.liumq.booksystem.entity.Role;
import com.liumq.booksystem.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/houtai/role")
public class BackStageRoleController {

    @Resource
    private RoleService roleService;


    @RequestMapping("/manage")
    public ModelAndView manage() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle", "角色管理");
        mav.addObject("title", "角色管理");
        mav.setViewName("/admin/page/role/role_manage");
        return mav;
    }

    @RequestMapping("/manage")
    public ModelAndView add() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_test", "添加");
        mav.addObject("save_url", "/admin/role/add");
        mav.setViewName("/admin/page/role/add_update");
        return mav;
    }

    @RequestMapping("/manage")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();

        Role role = roleService.findId(id);

        mav.addObject("role", role);
        mav.addObject("btn_test", "修改");
        mav.addObject("save_url", "/admin/role/update?id=" + id);
        mav.setViewName("/admin/page/role/add_update");
        return mav;
    }
}

package com.liumq.booksystem.controller.backstage;

import com.liumq.booksystem.dao.UserDao;
import com.liumq.booksystem.entity.Role;
import com.liumq.booksystem.entity.User;
import com.liumq.booksystem.service.RoleService;
import com.liumq.booksystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/houtai/user")
public class BackStageUserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @RequestMapping("/manage")
    public ModelAndView manage() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle", "用户管理");
        mav.addObject("title", "用户管理");
        mav.setViewName("/admin/page/user/user_manage");
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = null;
        List<Role> roleList = roleService.list(map, 0, 1000);
        mav.addObject("roleList", roleList);
        mav.addObject("flag", true);
        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/user/add");
        mav.setViewName("/admin/page/user/add_update");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();

        Map<String, Object> map = null;
        List<Role> roleList = roleService.list(map, 0, 1000);
        mav.addObject("roleList", roleList);

        User user = userService.findById(id);
        mav.addObject("user", user);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/user/update?id=" + id);
        mav.setViewName("/admin/page/user/add_update");
        return mav;
    }

    @RequestMapping("/set_new_pwd")
    public ModelAndView serNewPwd(@RequestParam(value = "id", required = false) Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        User user = userService.findById(id);
        mav.addObject("user", user);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/user/set_new_pwd?id=" + id);
        mav.setViewName("/admin/page/user/set_new_pwd");
        return mav;

    }

}

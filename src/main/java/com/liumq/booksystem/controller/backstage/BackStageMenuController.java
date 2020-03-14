package com.liumq.booksystem.controller.backstage;

import com.liumq.booksystem.entity.Menu;
import com.liumq.booksystem.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/houtai/menu")
public class BackStageMenuController {
    @Resource
    private MenuService menuService;

    @RequestMapping("/manage")
    public ModelAndView manage(@RequestParam(value = "pId",required = false) Integer pId) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle", "菜单管理");
        mav.addObject("title", "菜单管理");
        mav.addObject("pId",pId);
        mav.setViewName("/admin/page/menu/menu_manage");
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add(@RequestParam(value = "pId",required = false) Integer pId) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/menu/add");
        mav.addObject("pId",pId);
        mav.setViewName("/admin/page/menu/add_update");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView();

        Menu menu = menuService.findById(id);

        mav.addObject("menu", menu);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/menu/update?id=" + id);
        mav.addObject("pId",menu.getpId());
        mav.setViewName("/admin/page/menu/add_update");
        return mav;
    }
}

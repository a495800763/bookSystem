package com.liumq.booksystem.controller.backstage;

import com.liumq.booksystem.entity.BookType;
import com.liumq.booksystem.service.BookTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/houtai/book/type")
public class BackStageBookTypeController {

    @Resource
    private BookTypeService bookTypeService;

    @RequestMapping("/manage")
    public ModelAndView manage() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "图书管理");
        mav.setViewName("/admin/page/booktype/booktype_manage");
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/book/type/add");
        mav.setViewName("/admin/page/booktype/add_update");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView();

        BookType bookType = bookTypeService.findId(id);

        mav.addObject("bookType", bookType);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/book/type/update?id=" + id);
        mav.setViewName("/admin/page/booktype/add_update");
        return mav;
    }
}

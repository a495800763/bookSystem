package com.liumq.booksystem.controller.backstage;


import com.liumq.booksystem.entity.Book;
import com.liumq.booksystem.entity.BookType;
import com.liumq.booksystem.service.BookService;
import com.liumq.booksystem.service.BookTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/houtai/book")
public class BackStageBookController {

    @Resource
    private BookService bookService;
    @Resource
    private BookTypeService bookTypeService;

    @RequestMapping("/manage")
    public ModelAndView manage() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "图书管理");
        mav.setViewName("/admin/page/book/book_manage");
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        List<BookType> bookTypeList = bookTypeService.listByCondition(map, 0, 100);
        mav.addObject("bookTypeList", bookTypeList);
        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/book/add");
        mav.setViewName("/admin/page/book/add_update");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mav = new ModelAndView();

        Book book = bookService.findId(id);
        Map<String, Object> map = new HashMap<>();
        List<BookType> bookTypeList = bookTypeService.listByCondition(map, 0, 100);
        mav.addObject("bookTypeList", bookTypeList);
        mav.addObject("book", book);
        mav.addObject("btn_text", "修改");
        mav.addObject("save_url", "/admin/book/update?id=" + id);
        mav.setViewName("/admin/page/book/add_update");
        return mav;
    }
}

package com.liumq.booksystem.controller.admin;

import com.liumq.booksystem.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/book")
public class AdminBookController {

    @Resource
    private BookService bookService;
}

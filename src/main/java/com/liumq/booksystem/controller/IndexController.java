package com.liumq.booksystem.controller;

import com.liumq.booksystem.util.BrowserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/")
    public String index_1(HttpServletResponse res, HttpServletRequest req) {
        return "redirect:/login";
    }


    @RequestMapping("/index")
    public String index(HttpServletResponse res, HttpServletRequest req) {
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletResponse res, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        String UserAgent = req.getHeader("User-Agent");
        mav.setViewName("pc/login/login");
        return mav;
    }


    @RequestMapping("/admin/main")
    public ModelAndView admin_main(HttpServletResponse res, HttpServletRequest req) throws Exception {
        ModelAndView mav = new ModelAndView();
        String UserAgent = req.getHeader("User-Agent");
        mav.setViewName("/admin/main");
        return mav;
    }
}

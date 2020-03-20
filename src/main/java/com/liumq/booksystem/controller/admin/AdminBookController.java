package com.liumq.booksystem.controller.admin;

import com.liumq.booksystem.entity.Book;
import com.liumq.booksystem.service.BookService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/book")
public class AdminBookController {

    @Resource
    private BookService bookService;

    @RequestMapping("/add")
    @ResponseBody
    public JSONObject add(@Valid Book book, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();
        //检查是否满足条件
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            //这里两个是取当前存入数据库的时间插入数据库
            book.setCreateDateTime(new Date());
            // user.setUpdateDateTime(new Date());
            bookService.add(book);
            result.put("seccess", true);
            result.put("msg", "添加成功");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@Valid Book book, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            book.setUpdateDateTime(new Date());
            bookService.update(book);
            result.put("success", true);
            result.put("msg", "添加成功");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    HttpServletResponse response,
                                    HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Book> bookList = bookService.listByCondition(map, page-1, limit);
        long total = bookService.getTotal(map);
        map.put("data", bookList);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public JSONObject delete(@RequestParam(value = "ids", required = false) String ids) throws Exception {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            bookService.deleteById(Integer.parseInt(idsStr[i]));
        }
        result.put("success", true);
        return result;
    }
}

package com.liumq.booksystem.controller.admin;

import com.liumq.booksystem.entity.BookType;
import com.liumq.booksystem.service.BookTypeService;
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
@RequestMapping("/admin/book/type")
public class AdminBookTypeController {

    @Resource
    private BookTypeService  bookTypeService;


    @ResponseBody
    @RequestMapping("/add")
    public JSONObject add(@Valid BookType bookType, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();

        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            bookType.setCreateDateTime(new Date());
            bookTypeService.add(bookType);
            result.put("success", true);
            result.put("msg", "添加成功");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public JSONObject update(@Valid BookType bookType, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            bookType.setUpdateDateTime(new Date());
            bookTypeService.update(bookType);
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

        Integer currentPage = page - 1;
        List<BookType> bookTypeList = bookTypeService.listByCondition(map, currentPage, limit);
        long total = bookTypeService.getTotal(map);
        map.clear();
        map.put("data", bookTypeList);
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
            bookTypeService.deleteById(Integer.parseInt(idsStr[i]));
        }
        result.put("success", true);
        return result;
    }


}

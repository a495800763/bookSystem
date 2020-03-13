package com.liumq.booksystem.controller.admin;


import com.liumq.booksystem.entity.Menu;
import com.liumq.booksystem.entity.Role;
import com.liumq.booksystem.service.MenuService;
import com.liumq.booksystem.util.StringUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {

    @Resource
    private MenuService menuService;


    @ResponseBody
    @RequestMapping("/add")
    public JSONObject add(@Valid Menu menu, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();

        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            menuService.add(menu);
            result.put("success", true);
            result.put("msg", "添加成功");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public JSONObject update(@Valid Menu menu, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
        } else {

            menuService.update(menu);
            result.put("success", true);
            result.put("msg", "添加成功");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "pId", required = false) String pId,
                                    HttpServletResponse response,
                                    HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtil.isNotEmpty(pId)) {
            map.put("pId", pId);
        }

        Integer currentPage = page - 1;
        List<Menu> userList = menuService.list(map, currentPage, limit);
        long total = menuService.getTotal(map);
        map.clear();
        map.put("data", userList);
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
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < idsStr.length; i++) {
            try {
                map.put("pId", Integer.parseInt(idsStr[i]));
                List<Menu> menuList = menuService.list(map, 0, 100);
                for (Menu m : menuList) {
                    menuService.delete(m.getId());
                }

                menuService.delete(Integer.parseInt(idsStr[i]));
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "有用户正在使用此菜单");
                return result;
            }
        }

        result.put("success", true);
        return result;
    }
}

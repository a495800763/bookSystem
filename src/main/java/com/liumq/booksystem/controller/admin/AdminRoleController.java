package com.liumq.booksystem.controller.admin;

import com.liumq.booksystem.entity.Role;
import com.liumq.booksystem.service.RoleService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/admin/role")
public class AdminRoleController {

    @Resource
    private RoleService roleService;


    @ResponseBody
    @RequestMapping("/add")
    public JSONObject add(@Valid Role role, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();

        role.setCreateDateTime(new Date());

        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            roleService.add(role);
            result.put("success", true);
            result.put("msg","添加成功");
        }
        return result;
    }
}

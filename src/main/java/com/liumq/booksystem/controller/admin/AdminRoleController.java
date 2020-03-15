package com.liumq.booksystem.controller.admin;

import com.liumq.booksystem.entity.Menu;
import com.liumq.booksystem.entity.Role;
import com.liumq.booksystem.entity.RoleMenu;
import com.liumq.booksystem.entity.User;
import com.liumq.booksystem.service.MenuService;
import com.liumq.booksystem.service.RoleMenuService;
import com.liumq.booksystem.service.RoleService;
import jdk.vm.ci.meta.Value;
import net.sf.json.JSON;
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
import java.util.*;

@Controller
@RequestMapping("/admin/role")
public class AdminRoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuService roleMenuService;

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
            result.put("msg", "添加成功");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public JSONObject update(@Valid Role role, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
        } else {
            role.setUpdateDateTime(new Date());
            roleService.update(role);
            result.put("success", true);
            result.put("msg", "添加成功");
        }
        return result;
    }

    /**
     * @param page  默认1
     * @param limit 每页的数据量
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    HttpServletResponse response,
                                    HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        Integer currentPage = page - 1;
        List<Role> userList = roleService.list(map, currentPage, limit);
        long total = roleService.getTotal(map);
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
        for (int i = 0; i < idsStr.length; i++) {
            try {
                roleService.delete(Integer.parseInt(idsStr[i]));
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "有用户正在使用此角色");
                return result;
            }
        }
        result.put("success", true);
        return result;
    }

    /**
     * 拿节点数据供layui 的tree 组件使用
     *
     * @param roleId
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getCheckedMenuData")
    public List<JSONObject> getCheckedTreeMenu(@RequestParam(value = "roleId", required = false) Integer roleId, HttpServletResponse response) throws Exception {
        List<JSONObject> list = new ArrayList<JSONObject>();
        //拿根节点
        List<Menu> menuList = menuService.findByPId(-1);

        for (Menu menu : menuList) {
            JSONObject node = new JSONObject();
            node.put("id", menu.getId());
            node.put("title", menu.getName());
            node.put("spread", true);
            node.put("children", getchildren(menu.getId(), roleId));
            list.add(node);
        }
        return list;
    }

    /**
     * 辅助方法 用于得到treenode 的children
     *
     * @param
     * @param roleId
     * @return
     * @throws Exception
     */
    private List<JSONObject> getchildren(Integer pId, Integer roleId) throws Exception {
        List<Menu> menuList = menuService.findByPId(pId);
        List<JSONObject> list = new ArrayList<JSONObject>();
        for (Menu menu : menuList) {
            JSONObject node = new JSONObject();
            node.put("id", menu.getId());
            node.put("title", menu.getName());
            //           node.put("state", "opend");
//            RoleMenu roleMenu = roleMenuService.findByRoleIdAndMenuId(roleId,menu.getId());
//            if(roleMenu==null)
//            {
//                node.put("checked", false);
//            }else{
//                node.put("checked", true);
//            }
//            node.put("children", getchildren(menu.getId(), roleId));
            list.add(node);
        }
        return list;
    }
}

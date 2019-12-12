package cn.jiujiu.controller;

import cn.jiujiu.entity.User;
import cn.jiujiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @描述
 * @日期 2019/9/5
 * @作者 liyz
 */
@RestController
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述 查询所有用户的分页功能
     * @author  liyz
     * @date    2019/9/19
     * @param   page 要查询的页码
     * @param   rows 每页展示多少条数据
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("paging")
    public Map<String,Object> queryByPaging(Integer page, Integer rows,String _search,
                              String searchField, String searchOper, String searchString){
        Map<String, Object> users = userService.queryByPaging(page, rows,_search,searchField,searchOper,searchString);
        return users;
    }

    /**
     * 功能描述  用户列表的增删改查
     * @author  liyz
     * @date    2019/10/8
     * @param   oper 前台传过来的编辑（添加/修改/删除）,
     * @param   user 传过来的要编辑的对象,
     * @param   id 要编辑对象的id
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper,User user,String [] id){

        //声明map存放本方法的返回值
        Map<String, Object> map = new HashMap<>();

        //如果是添加操作
        if("add".equals(oper)){
            userService.insertUser(user);
            map.put("msg","用户添加成功");
        }
        if("edit".equals(oper)){
            userService.updateUser(user);
            map.put("msg","用户修改成功");
        }
        if("del".equals(oper)){
            for (int i = 0 ; i < id.length ; i++){
                userService.deleteUserById(id[i]);
            }
            map.put("msg","用户删除成功");
        }
        return map;
    }

    /**
     * 功能描述 查询所有客户的公司名称拼接成html在前端页面展示
     * @author  liyz
     * @date    2019/9/19
     * @return  String
     */
    @RequestMapping("selectCompanyFromUser")
    public String selectCompanyFromUser() {
        List<String> list = userService.selectAllCompanyFromUser();
        StringBuilder builder = new StringBuilder();
        builder.append("<select>");
        for (int i = 0; i < list.size(); i++) {
            builder.append("<option value='" + list.get(i) + "'>" + list.get(i) + "</option>");
        }
        builder.append("</select>");
        return builder.toString();
    }
}

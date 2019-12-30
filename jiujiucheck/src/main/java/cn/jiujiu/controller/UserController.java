package cn.jiujiu.controller;

import cn.jiujiu.entity.User;
import cn.jiujiu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @描述
 * @日期 2019/9/5
 * @作者 liyz
 */
@RestController
@RequestMapping("userController")
public class UserController {


    private final Logger logger = LoggerFactory.getLogger(UserController.class);
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

    /**
     * 功能描述  用户登录的controller
     * @author  liyz
     * @date    2019/12/27
     * @param   username 用户名, password 密码
     * @return  java.util.Map<java.lang.String,java.lang.String>
     */
    @RequestMapping("userLogin")
    public Map<String,String> userLogin(String username, String password, HttpSession session){

        //定义空map作为本方法的返回值，存放管理员登录返回的结果信息
        Map<String, String> map = new HashMap<>();
        User user = userService.userLogin(username, password);
        if(user==null){
            map.put("msg","账号密码错误");
        }else{
            session.setAttribute("id",user.getId());
            logger.debug("用户id为"+user.getId()+"的用户登录成功");
            map.put("msg","ok");
        }
        return map;
    }
}

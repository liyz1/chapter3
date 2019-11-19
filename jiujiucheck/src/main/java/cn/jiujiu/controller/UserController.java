package cn.jiujiu.controller;

import cn.jiujiu.entity.User;
import cn.jiujiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
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
     * 功能描述 查询所有用户信息
     * @author  liyz
     * @date    2019/9/19
     * @param   []
     * @return  java.util.List
     */
    @RequestMapping("selectAllUserFromUser")
    public List selectAllUserFromUser() {
        List<User> list = userService.selectAllUserFromUser();
        return list;
    }

    /**
     * 功能描述 查询所有用户的分页功能
     * @author  liyz
     * @date    2019/9/19
     * @param   [page 要查询的页码, rows 每页展示多少条数据]
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("paging")
    public Map<String,Object> queryByPaging(Integer page, Integer rows){
        Map<String, Object> users = userService.queryByPaging(page, rows);
        return users;
    }

    /**
     * 功能描述  用户列表的增删改查
     * @author  liyz
     * @date    2019/10/8
     * @param   [oper 前台传过来的编辑（添加/修改/删除）, user传过来的要编辑的对象, id要编辑对象的id[]]
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper,User user,String [] id){

        //声明map存放本方法的返回值
        Map<String, Object> map = new HashMap<>();

        //如果是添加操作
        if("add".equals(oper)){
            //为新添加的对象分配uuid并组装
            String uuid = UUID.randomUUID().toString();
            user.setId(uuid);
            String salt = UUID.randomUUID().toString();
            user.setSalt(salt);
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String registerDate = simpleDateFormat.format(date);
            user.setRegisterTime(registerDate);
            userService.insertUser(user);
        }
        if("edit".equals(oper)){
            userService.updateUser(user);
        }
        return null;
    }
}

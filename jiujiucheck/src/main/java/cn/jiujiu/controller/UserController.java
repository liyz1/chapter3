package cn.jiujiu.controller;

import cn.jiujiu.entity.User;
import cn.jiujiu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @描述
 * @日期 2019/9/5
 * @作者 liyz
 */
@Controller
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test(){
        return "back/q";
    }
    @RequestMapping("/test2")
    public String test2(){
        return "front/homePage";
    }

    @RequestMapping("/test3")
    public String test3(){
        return "back/backHome";
    }

    @ResponseBody
    @RequestMapping("selectAllUserFromUser")
    public List selectAllUserFromUser() {
        List<User> list = userService.selectAllUserFromUser();
        return list;
    }

}

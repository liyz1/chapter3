package cn.jiujiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @描述
 * @日期 2019/9/5
 * @作者 liyz
 */
@Controller
@RequestMapping("/test")
public class UserController {
    @RequestMapping("/test")
    public String test(){
        return "q";
    }
    @RequestMapping("/test2")
    public String test2(){
        return "front/homePage";
    }

    @RequestMapping("/test3")
    public String test3(){
        return "back/backHome";
    }
}

package cn.jiujiu.controller;

import cn.jiujiu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @描述 管理员控制器
 * @日期 2019/9/17
 * @作者 liyz
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    /**
     * 功能描述  管理员登录的controller
     * @author  liyz
     * @date    2019/9/17
     * @param   [username 用户名, password 密码, verificationCode 验证码, session]
     * @return  java.util.Map<java.lang.String,java.lang.String>
     */
    @RequestMapping("adminLogin")
    public Map<String,String> adminLogin(String username, String password, String userVerificationCode, HttpSession session){

        //定义空map作为本方法的返回值，存放管理员登录返回的结果信息
        Map<String, String> map = new HashMap<>();

        //拿到后台生成的验证码
        String backVerificationCode =(String) session.getAttribute("verificationCode");
        //比对验证码
        if(backVerificationCode.equals(userVerificationCode)){
            //比对账号
            Map<String, String> result = adminService.adminLogin(username, password);
            return result;
        }else{
            map.put("msg","验证码有误");
        }
        return map;
    }
}

package cn.jiujiu.service;

import java.util.Map;

/**
 * @描述
 * @日期 2019/9/17
 * @作者 liyz
 */

public interface AdminService {
    //管理员后台登录
    public Map<String,String> adminLogin(String username,String password);
}

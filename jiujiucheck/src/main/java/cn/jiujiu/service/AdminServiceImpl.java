package cn.jiujiu.service;

import cn.jiujiu.DAO.AdminDAO;
import cn.jiujiu.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述
 * @日期 2019/9/17
 * @作者 liyz
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDAO adminDAO;
    /**
     * 功能描述 管理员登录的service
     * @author  liyz
     * @date    2019/9/17
     * @param   [username 用户名, password 密码]
     * @return  java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, String> adminLogin(String username, String password) {

        //新建map放置登录结果的返回值
        Map<String, String> map = new HashMap<>();

        //查询是否有这个账号
        Integer count = adminDAO.selectAdminByUsername(username);

        //如果账号存在，查询是否有这个用户
        if(count==1){
            Admin admin = adminDAO.selectAdminByUsernameAndPassword(username, password);
            if(admin!=null){
                map.put("msg","登录成功");
            }else{
                map.put("msg","密码错误");
            }
        }else{
            map.put("msg","账号错误");
        }
        return map;
    }
}

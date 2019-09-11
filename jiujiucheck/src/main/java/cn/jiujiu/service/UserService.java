package cn.jiujiu.service;

import cn.jiujiu.entity.User;

import java.util.List;

/*
 *
 * 功能描述 user表的service
 * @author  liyz
 * @date    2019/9/11
 */
public interface UserService {
    //查询所有
    public List<User> selectAllUserFromUser();
}

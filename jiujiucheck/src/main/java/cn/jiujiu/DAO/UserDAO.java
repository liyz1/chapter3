package cn.jiujiu.DAO;

import cn.jiujiu.entity.User;

import java.util.List;

/*
 *
 * 功能描述  查询用户表的dao
 * @author  liyz
 * @date    2019/9/11
 */
public interface UserDAO {
    //查询所有用户
    public List<User> selectAllUserFromUser();
}

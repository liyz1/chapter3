package cn.jiujiu.DAO;

import cn.jiujiu.entity.User;
import org.apache.ibatis.annotations.Param;

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
    //分页查询用户表
    public List<User> selectByPaging(@Param("start") Integer start,@Param("rows") Integer rows);
    //查询总条数
    public Integer selectRecords();
    //添加一个新用户
    public void insertUser(User user);
}

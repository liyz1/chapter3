package cn.jiujiu.DAO;

import cn.jiujiu.entity.Admin;
import cn.jiujiu.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.print.DocFlavor;
import java.util.List;

/*
 *
 * 功能描述  查询用户表的dao
 * @author  liyz
 * @date    2019/9/11
 */
public interface UserDAO {

    /*************前台接口**************/
    //分页查询用户表
    public List<User> selectByPaging(@Param("start") Integer start,@Param("rows") Integer rows);
    //根据公司名称模糊查询
    public List<User> likeUserByCompany(@Param("company")String company);
    //根据客户名称模糊查询
    public List<User> likeUserByNickname(@Param("nickname")String nickname);
    //查询总条数
    public Integer selectRecords();
    //添加一个新用户
    public void insertUser(User user);
    //根据uuid修改用户
    public void updateUser(User user);
    //根据id删除一个用户
    public void deleteUserById(String id);
    //查询所有公司名称
    public List<String> selectAllCompanyFromUser();
    //根据公司名称查询客户信息
    public User selectUserFromUser(String company);

    /*************后台接口**************/
    //查询账号是否存在
    public Integer selectUserByUsername(String username);
    //根据账号密码查询
    public User selectUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}

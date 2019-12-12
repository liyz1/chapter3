package cn.jiujiu.service;

import cn.jiujiu.entity.User;

import java.util.List;
import java.util.Map;

/*
 *
 * 功能描述 user表的service
 * @author  liyz
 * @date    2019/9/11
 */
public interface UserService {
    //查询所有
    public List<String> selectAllCompanyFromUser();
    //分页查询
    public Map<String,Object> queryByPaging(Integer page,Integer rows,String _search,
                              String searchField, String searchOper, String searchString);
    //注册新用户
    public void insertUser(User user);
    //根据id修改用户信息
    public void updateUser(User user);
    //根据id删除一个用户
    public void deleteUserById(String id);
}

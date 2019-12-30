package cn.jiujiu.service;

import cn.jiujiu.DAO.UserDAO;
import cn.jiujiu.entity.Admin;
import cn.jiujiu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @描述 UserService的实现类
 * @日期 2019/9/11
 * @作者 liyz
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * 功能描述 分页展示所有用户信息的service
     * jqGrid自带搜索哦操作符：“eq”等于, “ne”不等于, “bw”开始于, “bn”不开始于 ,“ew”结束于,
     * “en”不结束于, “cn”包含, “nc”不包含, “nu”不存在, “nn”存在, “in”属于, “ni”不属于,暂
     * 时使用“cn”包含一个。
     * @author  liyz
     * @date    2019/9/19
     * @param   page  要查询的页数
     * @param   rows 每页展示的条数
     * @param   _search 查询操作符_search的值
     * @param   searchField 模糊查询属性名称
     * @param   searchOper 是否为模糊查询[“eq”, “ne”, “bw”, “bn”, “ew”, “en”, “cn”, “nc”, “nu”, “nn”, “in”, “ni”]
     * @param   searchString 模糊查询字符串
     * @return  java.util.List<cn.jiujiu.entity.User>符合查询结果的集合
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryByPaging(Integer page, Integer rows,String _search,
                               String searchField,String searchOper, String searchString) {
        //计算起始下标
        Integer start = (page-1)*rows;
        //拿到符合条件的结果集
        List<User> users = null;
        if("true".equals(_search)){
            //当搜索框传过来的值为true时进行条件查询
            if("company".equals(searchField)){
                //公司名称包含所写字段
                users = userDAO.likeUserByCompany(searchString);
            }
            if("nickname".equals(searchField)){
                //客户名称包含所写字段
                users = userDAO.likeUserByNickname(searchString);
            }
        }else{
            //当搜索框传过来的值不为true时进行查询所有
            users = userDAO.selectByPaging(start, rows);
        }

        //拿到总记录数
        Integer records = userDAO.selectRecords();
        //拿到总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;

        //创建map填数据返回
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",users);
        return map;
    }

    /**
     * 功能描述 添加用户信息
     * @author  liyz
     * @date    2019/09/11
     * @param   user
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void insertUser(User user) {

        //为新添加的对象分配uuid并组装
        String uuid = UUID.randomUUID().toString();
        user.setId(uuid);
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String registerDate = simpleDateFormat.format(date);
        user.setRegisterTime(registerDate);

        userDAO.insertUser(user);
    }

    /**
     * 功能描述 根据id修改用户信息
     * @author  liyz
     * @date    2019/10/28
     * @param   user
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    /**
     * 功能描述 根据id删除用户信息
     * @author  liyz
     * @date    2019/10/28
     * @param   id 要删除的用户的id
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void deleteUserById(String id) {
        userDAO.deleteUserById(id);
    }


    /**
     * 功能描述 展示所有客户的名称信息
     * @author  liyz
     * @date    2019/9/19
     * @return  java.util.List<cn.jiujiu.entity.User>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<String> selectAllCompanyFromUser() {
        List<String> list = userDAO.selectAllCompanyFromUser();
        return list;
    }
    /**
     * 功能描述 用户登录的service
     * @author  liyz
     * @date    2019/9/17
     * @param   username 用户名, password 密码
     * @return  java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User userLogin(String username, String password) {

        //查询是否有这个账号
        Integer count = userDAO.selectUserByUsername(username);

        //如果账号存在，查询是否有这个用户
        if(count==1) {
            User user = userDAO.selectUserByUsernameAndPassword(username, password);
            return user;
        }else{
            return null;
        }
    }
}

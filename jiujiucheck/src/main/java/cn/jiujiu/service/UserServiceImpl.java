package cn.jiujiu.service;

import cn.jiujiu.DAO.UserDAO;
import cn.jiujiu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 功能描述 展示所有用户信息的service
     * @author  liyz
     * @date    2019/9/19
     * @param   []
     * @return  java.util.List<cn.jiujiu.entity.User>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> selectAllUserFromUser() {
        List<User> list = userDAO.selectAllUserFromUser();
        return list;
    }

    /**
     * 功能描述 分页展示所有用户信息的service
     * @author  liyz
     * @date    2019/9/19
     * @param   [Integer page 要查询的页数, Integer rows 每页展示的条数]
     * @return  java.util.List<cn.jiujiu.entity.User>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryByPaging(Integer page, Integer rows) {

        //计算起始下标
        Integer start = (page-1)*rows;
        //拿到符合条件的结果集
        List<User> users = userDAO.selectByPaging(start, rows);
        System.out.println("符合条件的结果"+users);
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
     * @param   [user]
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    /**
     * 功能描述 根据id修改用户信息
     * @author  liyz
     * @date    2019/10/28
     * @param   [user]
     * @return  void
     */
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
}

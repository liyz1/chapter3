package cn.jiujiu.service;

import cn.jiujiu.DAO.UserDAO;
import cn.jiujiu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> selectAllUserFromUser() {
        List<User> list = userDAO.selectAllUserFromUser();
        System.out.println(list);
        return list;
    }
}

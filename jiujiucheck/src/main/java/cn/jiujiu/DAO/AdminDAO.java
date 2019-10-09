package cn.jiujiu.DAO;

import cn.jiujiu.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * @描述 admin的DAO
 * @日期 2019/9/17
 * @作者 liyz
 */
public interface AdminDAO {
    //查询账号是否存在
    public Integer selectAdminByUsername(String username);
    //根据账号密码查询
    public Admin selectAdminByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}

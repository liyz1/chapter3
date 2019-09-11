package cn.jiujiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * @描述 Admin实体类
 * @日期 2019/5/6
 * @作者 liyz
 */
@AllArgsConstructor
@Data
public class Admin implements Serializable {

    private Integer id;         //管理员id
    private String username;     //管理员账户
    private String password;    //管理员密码
    private String status;      //管理员状态
}

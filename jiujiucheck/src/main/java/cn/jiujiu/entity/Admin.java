package cn.jiujiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @描述 Admin实体类
 * @日期 2019/5/6
 * @作者 liyz
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin implements Serializable {
    private Integer id;         //管理员id
    private String username;    //管理员账号
    private String password;    //管理员密码
    private String status;      //管理员状态     1-超级管理员    2-普通管理员
}

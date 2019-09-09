package cn.jiujiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @描述 User实体类
 * @日期 2019/5/6
 * @作者 liyz
 */
@AllArgsConstructor
@Data
public class User implements Serializable {
    private Integer id;         //用户编号
    private String userName;    //用户姓名
    private String password;    //用户密码
    private String nickName;    //用户昵称
    private String address;     //用户地址
    private String phone;       //手机号码
}

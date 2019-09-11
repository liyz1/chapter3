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
    private String company;     //用户公司
    private String username;    //用户名
    private String salt;        //盐值
    private String password;    //用户密码
    private String nickName;    //用户昵称
    private String address;     //用户地址
    private String phone;       //手机号码
}

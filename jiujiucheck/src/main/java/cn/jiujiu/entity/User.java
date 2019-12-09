package cn.jiujiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @描述 User实体类
 * @日期 2019/5/6
 * @作者 liyz
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    private String id;          //用户编号
    private String company;     //用户公司
    private String username;    //用户名
    private String salt;        //盐值
    private String password;    //用户密码
    private String nickname;    //用户昵称
    private String registerTime;//用户注册时间
    private String status;      //用户状态      1-激活    2-冻结
    private String address;     //用户地址
    private String phone;       //手机号码
    private String remark;      //备注
}

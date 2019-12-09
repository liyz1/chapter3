package cn.jiujiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @描述 员工实体类
 * @日期 2019/12/09
 * @作者 liyz
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Staff implements Serializable {
    private String id;          //员工编号
    private String name;        //员工姓名
    private String phone;       //员工电话
    private String username;    //用户名
    private String password;    //密码
    private String salt;        //盐值
    private String status;      //状态 1-启用 2-冻结
    private String position;    //职位类型
    private String remark;      //备注
}

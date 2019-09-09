package cn.jiujiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 Product实体类
 * @日期 2019/5/6
 * @作者 liyz
 */
@AllArgsConstructor
@Data
public class Product implements Serializable {

    private Integer id;                 //产品id
    private String productName;         //产品名称
    private String paperType;           //纸张类型
    private int amount;                 //产品数量
    private String currentSchedule;     //当前进度
    private Date createDate;            //订单创建时间
    private double price;               //产品金额
    private Date prepareCompleteDate;   //预计完工时间
    private String effectPicture;       //效果图
    private String note;                //备注
    private int userId;                 //产品的用户id
    private String status;              //订单状态
    private String salesman;            //所属业务员
}

package cn.jiujiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述 Order实体类
 * @日期 2019/5/6
 * @作者 liyz
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order implements Serializable {

    private String id;                  //产品id
    private String orderName;           //产品名称
    private String paperType;           //纸张类型
    private int amount;                 //产品数量
    private String createDate;          //订单创建时间
    private double price;               //订单单个产品金额
    private String prepareBeginDate;    //预计开工时间
    private String prepareCompleteDate;   //预计完工时间
    private String status;              //订单状态
    private String packageMode;         //打包方式 1-装箱 2-装袋
    private String salesman;            //所属业务员
    private String userId;              //产品的用户id
    private String remake;              //备注
}

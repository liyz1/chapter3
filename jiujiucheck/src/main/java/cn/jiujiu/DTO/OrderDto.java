package cn.jiujiu.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @描述 订单列表辅助类
 * @日期 2019/12/12
 * @作者 liyz
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements Serializable {
    private String id;                  //产品id
    private String orderName;           //产品名称
    private String paperType;           //纸张类型
    private int amount;                 //产品数量
    private String createDate;            //订单创建时间
    private BigDecimal price;               //订单单个产品金额
    private String prepareBeginDate;    //预计开工时间
    private String prepareCompleteDate;   //预计完工时间
    private String effectPicture;       //效果图
    private String status;              //订单状态
    private String packageMode;         //打包方式 1-装箱 2-装袋
    private String salesman;            //所属业务员
    private String designer;            //所属设计师
    private String businessAssistant;   //所属业务助理
    private String company;             //所属客户的公司名称
    private String remake;              //备注
}

package cn.jiujiu.DTO;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @描述 订单列表辅助类
 * @日期 2019/12/12
 * @作者 liyz
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements Serializable {
    @ExcelIgnore
    private String id;                  //产品id
    @Excel(name = "产品名称")
    private String orderName;           //产品名称
    @Excel(name = "纸张类型")
    private String paperType;           //纸张类型
    @Excel(name = "产品数量")
    private int amount;                 //产品数量
    @Excel(name = "订单创建时间")
    private String createDate;            //订单创建时间
    @Excel(name = "订单单个产品金额")
    private BigDecimal price;               //订单单个产品金额
    @Excel(name = "预计开工时间")
    private String prepareBeginDate;    //预计开工时间
    @Excel(name = "计完工时间")
    private String prepareCompleteDate;   //预计完工时间
    @ExcelIgnore
    private String effectPicture;       //效果图
    @Excel(name = "订单状态")
    private String status;              //订单状态
    @Excel(name = "打包方式")
    private String packageMode;         //打包方式 1-装箱 2-装袋
    @Excel(name = "业务员")
    private String salesman;            //所属业务员
    @Excel(name = "设计师")
    private String designer;            //所属设计师
    @Excel(name = "业务助理")
    private String businessAssistant;   //所属业务助理
    @Excel(name = "客户名称")
    private String company;             //所属客户的公司名称
    @Excel(name = "备注")
    private String remake;              //备注
}

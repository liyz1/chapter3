package cn.jiujiu.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.jiujiu.DTO.OrderDto;
import cn.jiujiu.service.OrderService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述 订单列表的增删改查
 * @日期 2019/12/05
 * @作者 liyz
 */
@RestController
@RequestMapping("OrderController")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 功能描述 查询所有订单的分页功能
     * @author  liyz
     * @date    2019/12/05
     * @param   page 要查询的页码
     * @param   rows 每页展示多少条数据
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("paging")
    public Map<String,Object> queryByPaging(Integer page, Integer rows, String _search,
                                            String searchField, String searchOper, String searchString){
        System.out.println("controller");
        Map<String, Object> orders = orderService.queryByPaging(page, rows,_search,searchField,searchOper,searchString);
        return orders;
    }

    /**
     * 功能描述  订单列表的增删改查
     * @author  liyz
     * @date    2019/12/05
     * @param   oper 前台传过来的编辑（添加/修改/删除）,
     * @param   orderDto 传过来的要编辑的对象,
     * @param   id 要编辑对象的id
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, OrderDto orderDto, String [] id){

        //声明map存放本方法的返回值
        Map<String, Object> map = new HashMap<>();

        //如果是添加操作
        if("add".equals(oper)){
            System.out.println(orderDto.toString());
            orderService.insertOrder(orderDto);
            map.put("msg","订单添加成功");
        }
        if("edit".equals(oper)){
            orderService.updateOrder(orderDto);
            map.put("msg","订单修改成功");
        }
        if("del".equals(oper)){
            for (int i = 0 ; i < id.length ; i++){
                orderService.deleteOrderById(id[i]);
            }
            map.put("msg","订单删除成功");
        }
        return map;
    }
    /**
     * 功能描述  订单列表的表格导出
     * @author  liyz
     * @date    2019/12/13
     */
    @RequestMapping("orderPrint")
    public void orderPrint() throws IOException {
        List<OrderDto> list = orderService.selectAllUnFinishedFromOrder();
        for (OrderDto orderDto:list){
            if("1".equals(orderDto.getStatus())){orderDto.setStatus("设计");}
            if("2".equals(orderDto.getStatus())){orderDto.setStatus("印刷");}
            if("3".equals(orderDto.getStatus())){orderDto.setStatus("覆膜");}
            if("4".equals(orderDto.getStatus())){orderDto.setStatus("烫金");}
            if("5".equals(orderDto.getStatus())){orderDto.setStatus("过油");}
            if("6".equals(orderDto.getStatus())){orderDto.setStatus("压纹");}
            if("7".equals(orderDto.getStatus())){orderDto.setStatus("模切");}
            if("8".equals(orderDto.getStatus())){orderDto.setStatus("粘盒");}
            if("9".equals(orderDto.getStatus())){orderDto.setStatus("打包");}
            if("10".equals(orderDto.getStatus())){orderDto.setStatus("发货");}

            if("1".equals(orderDto.getPackageMode())){orderDto.setPackageMode("装箱");}
            if("2".equals(orderDto.getPackageMode())){orderDto.setPackageMode("装袋");}
        }

        //拼接文件名称
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String title = simpleDateFormat.format(date);
        //导出之前先清除原有的文件
        File file = new File("D:/" + title + "订单表.xls");
        if (file.exists()){
            file.delete();
        }
        //创建对象并导出
        SXSSFWorkbook order = (SXSSFWorkbook) ExcelExportUtil.exportBigExcel(new ExportParams("订单表", "正在进行订单"), OrderDto.class, list);
        order.write(new FileOutputStream("D:/"+title+"订单表.xls"));
        //清除内存中的缓存
        order.dispose();
        order.close();

    }
}

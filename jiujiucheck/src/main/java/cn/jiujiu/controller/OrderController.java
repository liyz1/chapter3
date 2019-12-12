package cn.jiujiu.controller;

import cn.jiujiu.DTO.OrderDto;
import cn.jiujiu.entity.Order;
import cn.jiujiu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

}

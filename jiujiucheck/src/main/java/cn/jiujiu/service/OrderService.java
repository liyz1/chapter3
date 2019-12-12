package cn.jiujiu.service;

import cn.jiujiu.DTO.OrderDto;
import cn.jiujiu.entity.Order;
import cn.jiujiu.entity.User;

import java.util.List;
import java.util.Map;

/*
 *
 * 功能描述  order表的service
 * @author  liyz
 * @date    2019/12/05
 */
public interface OrderService {
    //分页查询
    public Map<String,Object> queryByPaging(Integer page, Integer rows, String _search,
                                            String searchField, String searchOper, String searchString);
    //添加新订单
    public void insertOrder(OrderDto orderDto);
    //根据id修改订单信息
    public void updateOrder(OrderDto orderDto);
    //根据id删除一个订单
    public void deleteOrderById(String id);
}

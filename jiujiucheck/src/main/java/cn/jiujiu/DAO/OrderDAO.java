package cn.jiujiu.DAO;

import cn.jiujiu.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDAO {
    //分页查询订单表
    public List<Order> selectByPaging(@Param("start") Integer start, @Param("rows") Integer rows);
    //根据订单名称模糊查询
    public List<Order> likeOrderByOrderName(@Param("orderName")String orderName);
    //查询总条数
    public Integer selectRecords();
    //添加一个新订单
    public void insertOrder(Order order);
    //根据uuid修改订单
    public void updateOrder(Order order);
    //根据id删除一个订单
    public void deleteOrderById(String id);
}

package cn.jiujiu.DAO;

import cn.jiujiu.DTO.OrderDto;
import cn.jiujiu.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDAO {

    /*************后台接口**************/
    //分页查询订单表
    public List<OrderDto> selectByPaging(@Param("start") Integer start, @Param("rows") Integer rows);
    //根据订单名称模糊查询
    public List<OrderDto> likeOrderByOrderName(@Param("orderName")String orderName);
    //查询总条数
    public Integer selectRecords();
    //添加一个新订单
    public void insertOrder(Order order);
    //根据uuid修改订单
    public void updateOrder(Order order);
    //根据id删除一个订单
    public void deleteOrderById(String id);
    //查询当前未完成的所有订单（导出报表）
    public List<OrderDto> selectAllUnFinishedFromOrder();

    /*************前台接口**************/
    //根据userId查询订单
    public List<OrderDto> selectUnFinishedOrderByUserId(String userId);
}

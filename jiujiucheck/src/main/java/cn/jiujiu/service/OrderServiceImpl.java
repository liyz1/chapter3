package cn.jiujiu.service;

import cn.jiujiu.DAO.OrderDAO;
import cn.jiujiu.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @描述 OrderService的实现类
 * @日期 2019/12/05
 * @作者 liyz
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    /**
     * 功能描述 分页展示所有用户信息的service
     * jqGrid自带搜索操作符：“eq”等于, “ne”不等于, “bw”开始于, “bn”不开始于 ,“ew”结束于,
     * “en”不结束于, “cn”包含, “nc”不包含, “nu”不存在, “nn”存在, “in”属于, “ni”不属于,暂
     * 时使用“cn”包含一个。
     * @author  liyz
     * @date    2019/9/19
     * @param   page  要查询的页数
     * @param   rows 每页展示的条数
     * @param   _search 查询操作符_search的值
     * @param   searchField 模糊查询属性名称
     * @param   searchOper 是否为模糊查询
     * @param   searchString 模糊查询字符串
     * @return  java.util.List<cn.jiujiu.entity.User>符合查询结果的集合
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryByPaging(Integer page, Integer rows,String _search,
                               String searchField,String searchOper, String searchString) {
        System.out.println("service");
        //计算起始下标
        Integer start = (page-1)*rows;
        //拿到符合条件的结果集
        List<Order> orders = null;
        if("true".equals(_search)){
            //当搜索框传过来的值为true时进行条件查询
            if("orderName".equals(searchField)){
                //公司名称包含所写字段
                orders = orderDAO.likeOrderByOrderName(searchString);
            }
        }else{
            //当搜索框传过来的值不为true时进行查询所有
            orders = orderDAO.selectByPaging(start, rows);
        }

        //拿到总记录数
        Integer records = orderDAO.selectRecords();
        //拿到总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;

        //创建map填数据返回
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",orders);
        return map;
    }

    /**
     * 功能描述 添加订单信息
     * @author  liyz
     * @date    2019/12/05
     * @param   order
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void insertOrder(Order order) {

        //为新添加的对象分配uuid并组装
        String uuid = UUID.randomUUID().toString();
        order.setId(uuid);

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String createDate = simpleDateFormat.format(date);
        order.setCreateDate(createDate);
        System.out.println(order);
        orderDAO.insertOrder(order);
    }

    /**
     * 功能描述 根据id修改订单信息
     * @author  liyz
     * @date    2019/12/05
     * @param   order
     * @return  void
     */
    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    /**
     * 功能描述 根据id删除订单信息
     * @author  liyz
     * @date    2019/12/05
     * @param   id 要删除的用户的id
     * @return  void
     */
    @Override
    public void deleteOrderById(String id) {
        orderDAO.deleteOrderById(id);
    }
}

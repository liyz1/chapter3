package cn.jiujiu.service;

import cn.jiujiu.DAO.OrderDAO;
import cn.jiujiu.DAO.StaffDAO;
import cn.jiujiu.DAO.UserDAO;
import cn.jiujiu.DTO.OrderDto;
import cn.jiujiu.entity.Order;
import cn.jiujiu.entity.User;
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

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StaffDAO staffDAO;

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
     * @return  java.util.List<cn.jiujiu.entity.Order>符合查询结果的集合
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryByPaging(Integer page, Integer rows,String _search,
                               String searchField,String searchOper, String searchString) {
        //计算起始下标
        Integer start = (page-1)*rows;
        //拿到符合条件的结果集
        List<OrderDto> orders = null;
        if("true".equals(_search)){
            //当搜索框传过来的值为true时进行条件查询
            if("orderName".equals(searchField)){
                //订单名称包含所写字段
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
     * @param   orderDto
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void insertOrder(OrderDto orderDto) {
        Order order = new Order();
        String uuid = UUID.randomUUID().toString();
        order.setId(uuid);
        order.setOrderName(orderDto.getOrderName());
        order.setPaperType(orderDto.getPaperType());
        order.setAmount(orderDto.getAmount());
        order.setPrice(orderDto.getPrice());
        order.setPrepareBeginDate(orderDto.getPrepareBeginDate());
        order.setPrepareCompleteDate(orderDto.getPrepareCompleteDate());
        order.setStatus(orderDto.getStatus());
        order.setPackageMode(orderDto.getPackageMode());
        order.setRemark(orderDto.getRemark());

        //添加业务员id
        String salesmanId = staffDAO.selectIdByName(orderDto.getSalesman());
        order.setSalesmanId(salesmanId);
        //添加设计师id
        String designerId = staffDAO.selectIdByName(orderDto.getDesigner());
        order.setDesignerId(designerId);
        //添加业务助理id
        String businessAssistantId = staffDAO.selectIdByName(orderDto.getBusinessAssistant());
        order.setBusinessAssistantId(businessAssistantId);
        //添加客户id
        User user = userDAO.selectUserFromUser(orderDto.getCompany());
        order.setUserId(user.getId());

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String createDate = simpleDateFormat.format(date);
        order.setCreateDate(createDate);
        orderDAO.insertOrder(order);
    }

    /**
     * 功能描述 根据id修改订单信息。用户输入的是客户的公司名称，在这里转化为公司id插入表格
     * @author  liyz
     * @date    2019/12/05
     * @param   orderDto
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void updateOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setOrderName(orderDto.getOrderName());
        order.setPaperType(orderDto.getPaperType());
        order.setAmount(orderDto.getAmount());
        order.setCreateDate(orderDto.getCreateDate());
        order.setPrice(orderDto.getPrice());
        order.setPrepareBeginDate(orderDto.getPrepareBeginDate());
        order.setPrepareCompleteDate(orderDto.getPrepareCompleteDate());
        order.setStatus(orderDto.getStatus());
        order.setPackageMode(orderDto.getPackageMode());
        order.setRemark(orderDto.getRemark());
        //添加业务员id
        String salesmanId = staffDAO.selectIdByName(orderDto.getSalesman());
        order.setSalesmanId(salesmanId);
        //添加设计师id
        String designerId = staffDAO.selectIdByName(orderDto.getDesigner());
        order.setDesignerId(designerId);
        //添加业务助理id
        String businessAssistantId = staffDAO.selectIdByName(orderDto.getBusinessAssistant());
        order.setBusinessAssistantId(businessAssistantId);
        //添加客户id
        User user = userDAO.selectUserFromUser(orderDto.getCompany());
        order.setUserId(user.getId());

        orderDAO.updateOrder(order);
    }

    /**
     * 功能描述 根据id删除订单信息
     * @author  liyz
     * @date    2019/12/05
     * @param   id 要删除的订单的id
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void deleteOrderById(String id) {
        orderDAO.deleteOrderById(id);
    }

    /**
     * 功能描述 查询当前未完成的所有订单（导出报表）
     * @author  liyz
     * @date    2019/12/13
     * @return  List<OrderDto>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<OrderDto> selectAllUnFinishedFromOrder() {
        List<OrderDto> list = orderDAO.selectAllUnFinishedFromOrder();
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
        return list;
    }

    /**
     * 功能描述 前台用户查询自己的订单信息
     * @author  liyz
     * @date    2019/12/27
     * @return  List<OrderDto>
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<OrderDto> selectUnFinishedOrderByUserId(String userId) {
        List<OrderDto> list = orderDAO.selectUnFinishedOrderByUserId(userId);
        for (OrderDto orderDto:list){
            if("1".equals(orderDto.getPackageMode())){orderDto.setPackageMode("装箱");}
            if("2".equals(orderDto.getPackageMode())){orderDto.setPackageMode("装袋");}
        }
        return list;
    }
}

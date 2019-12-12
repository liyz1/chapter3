package cn.jiujiu.controller;

import cn.jiujiu.entity.Staff;
import cn.jiujiu.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述 员工列表的增删改查
 * @日期 2019/12/09
 * @作者 liyz
 */
@RestController
@RequestMapping("StaffController")
public class StaffController {

    @Autowired
    StaffService staffService;

    /**
     * 功能描述 查询所有员工的分页功能
     * @author  liyz
     * @date    2019/12/05
     * @param   page 要查询的页码
     * @param   rows 每页展示多少条数据
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("paging")
    public Map<String,Object> queryByPaging(Integer page, Integer rows, String _search,
                                            String searchField, String searchOper, String searchString){
        Map<String, Object> orders = staffService.queryByPaging(page, rows,_search,searchField,searchOper,searchString);
        return orders;
    }

    /**
     * 功能描述  员工列表的增删改查
     * @author  liyz
     * @date    2019/12/05
     * @param   oper 前台传过来的编辑（添加/修改/删除）,
     * @param   order 传过来的要编辑的对象,
     * @param   id 要编辑对象的id
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Staff order, String [] id){

        //声明map存放本方法的返回值
        Map<String, Object> map = new HashMap<>();

        //如果是添加操作
        if("add".equals(oper)){
            System.out.println(order.toString());
            staffService.insertStaff(order);
            map.put("msg","员工添加成功");
        }
        if("edit".equals(oper)){
            staffService.updateStaff(order);
            map.put("msg","员工修改成功");
        }
        if("del".equals(oper)){
            for (int i = 0 ; i < id.length ; i++){
                staffService.deleteStaffById(id[i]);
            }
            map.put("msg","员工删除成功");
        }
        return map;
    }

    /**
     * 功能描述 查询所有业务员拼接成html在前端页面展示
     * @author  liyz
     * @date    2019/9/19
     * @return  String
     */
    @RequestMapping("selectAllSalesmanFromStaff")
    public String selectAllSalesmanFromStaff() {
        List<String> list = staffService.selectAllSalesman();
        StringBuilder builder = new StringBuilder();
        builder.append("<select>");
        for (int i = 0; i < list.size(); i++) {
            builder.append("<option value='" + list.get(i) + "'>" + list.get(i) + "</option>");
        }
        builder.append("</select>");
        return builder.toString();
    }

    /**
     * 功能描述 查询所有客户的公司名称拼接成html在前端页面展示
     * @author  liyz
     * @date    2019/9/19
     * @return  String
     */
    @RequestMapping("selectAllDesignerFromStaff")
    public String selectAllDesignerFromStaff() {
        List<String> list = staffService.selectAllDesigner();
        StringBuilder builder = new StringBuilder();
        builder.append("<select>");
        for (int i = 0; i < list.size(); i++) {
            builder.append("<option value='" + list.get(i) + "'>" + list.get(i) + "</option>");
        }
        builder.append("</select>");
        return builder.toString();
    }

    /**
     * 功能描述 查询所有客户的公司名称拼接成html在前端页面展示
     * @author  liyz
     * @date    2019/9/19
     * @return  String
     */
    @RequestMapping("selectAllBusinessAssistantFromStaff")
    public String selectAllBusinessAssistantFromStaff() {
        List<String> list = staffService.selectAllBusinessAssistant();
        StringBuilder builder = new StringBuilder();
        builder.append("<select>");
        for (int i = 0; i < list.size(); i++) {
            builder.append("<option value='" + list.get(i) + "'>" + list.get(i) + "</option>");
        }
        builder.append("</select>");
        return builder.toString();
    }
}

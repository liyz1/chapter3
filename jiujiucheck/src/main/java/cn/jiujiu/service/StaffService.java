package cn.jiujiu.service;

import cn.jiujiu.entity.Staff;

import java.util.Map;

/*
 *
 * 功能描述  staff表的service
 * @author  liyz
 * @date    2019/12/09
 */
public interface StaffService {
    //分页查询
    public Map<String,Object> queryByPaging(Integer page, Integer rows, String _search,
                                            String searchField, String searchOper, String searchString);
    //添加新订单
    public void insertStaff(Staff staff);
    //根据id修改订单信息
    public void updateStaff(Staff staff);
    //根据id删除一个订单
    public void deleteStaffById(String id);
}

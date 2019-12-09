package cn.jiujiu.service;

import cn.jiujiu.DAO.StaffDAO;
import cn.jiujiu.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @描述 StaffService的实现类
 * @日期 2019/12/09
 * @作者 liyz
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

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
     * @return  java.util.List<cn.jiujiu.entity.Staff>符合查询结果的集合
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryByPaging(Integer page, Integer rows,String _search,
                               String searchField,String searchOper, String searchString) {
        //计算起始下标
        Integer start = (page-1)*rows;
        //拿到符合条件的结果集
        List<Staff> list = null;
        if("true".equals(_search)){
            //当搜索框传过来的值为true时进行条件查询
            if("name".equals(searchField)){
                //员工名称包含所写字段
                list = staffDAO.likeStaffByStaffName(searchString);
            }
        }else{
            //当搜索框传过来的值不为true时进行查询所有
            list = staffDAO.selectByPaging(start, rows);
        }

        //拿到总记录数
        Integer records = staffDAO.selectRecords();
        //拿到总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;

        //创建map填数据返回
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    /**
     * 功能描述 添加员工信息
     * @author  liyz
     * @date    2019/12/09
     * @param   staff
     * @return  void
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void insertStaff(Staff staff) {

        //为新添加的对象分配uuid并组装
        String uuid = UUID.randomUUID().toString();
        staff.setId(uuid);
        String salt = UUID.randomUUID().toString();
        staff.setSalt(salt);
        staffDAO.insertStaff(staff);
    }

    /**
     * 功能描述 根据id修改员工信息
     * @author  liyz
     * @date    2019/12/09
     * @param   Staff
     * @return  void
     */
    @Override
    public void updateStaff(Staff staff) {
        System.out.println(staff);
        staffDAO.updateStaff(staff);
    }

    /**
     * 功能描述 根据id删除员工信息
     * @author  liyz
     * @date    2019/12/09
     * @param   id 要删除的员工的id
     * @return  void
     */
    @Override
    public void deleteStaffById(String id) {
        staffDAO.deleteStaffById(id);
    }
}

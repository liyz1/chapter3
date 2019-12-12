package cn.jiujiu.DAO;

import cn.jiujiu.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffDAO {
    //分页查询员工表
    public List<Staff> selectByPaging(@Param("start") Integer start, @Param("rows") Integer rows);
    //根据员工名称模糊查询
    public List<Staff> likeStaffByStaffName(@Param("name") String name);
    //查询总条数
    public Integer selectRecords();
    //添加一个新员工
    public void insertStaff(Staff staff);
    //根据uuid修改员工信息
    public void updateStaff(Staff staff);
    //根据id删除一个员工信息
    public void deleteStaffById(String id);
    //查询所有业务员信息
    public List<String> selectAllSalesman();
    //查询所有设计师信息
    public List<String> selectAllDesigner();
    //查询所有业务助理信息
    public List<String> selectAllBusinessAssistant();
    //根据名称查询id
    public String selectIdByName(String name);
}

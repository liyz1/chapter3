package cn.jiujiu.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.jiujiu.DTO.OrderDto;
import cn.jiujiu.service.OrderService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

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
    /**
     * 功能描述  订单列表的表格导出。
     * 1在服务器端生成文件
     * 2下载到本地
     * @author  liyz
     * @date    2019/12/13
     */
    @RequestMapping("orderPrint")
    public void orderPrint(HttpServletResponse response) throws IOException {
        //在服务器端生成文件
        List<OrderDto> list = orderService.selectAllUnFinishedFromOrder();

        //将表格导出到服务器
        SXSSFWorkbook order = (SXSSFWorkbook) ExcelExportUtil.exportBigExcel(new ExportParams("订单表", "正在进行订单"), OrderDto.class, list);
        //order.write(new FileOutputStream("D:/订单表.xls"));
        order.write(new FileOutputStream("/usr/local/订单表.xls"));

        //下载到本地
        //File file = new File("D:/订单表.xls");
        File file = new File("/usr/local/订单表.xls");
        if(file.exists()){
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            //拼接下载后的文件名
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String filename = simpleDateFormat.format(date)+"订单表.xls";

            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            //文件输入流
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            //输出流
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //清除内存中的缓存和硬盘上的文件
        file.delete();
        order.dispose();
        order.close();

    }
    /**
     * 功能描述  前台用户查询自己的订单
     * @author  liyz
     * @date    2019/12/26
     */
    @RequestMapping("selectOrderByUserId")
    public List<OrderDto> selectOrderByUserId(HttpSession session){
        String userId =(String) session.getAttribute("id");
        List<OrderDto> list = orderService.selectUnFinishedOrderByUserId(userId);
        return  list;
    }
}

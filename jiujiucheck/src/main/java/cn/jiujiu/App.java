package cn.jiujiu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @描述 jiujiucheck入口类
 * @日期 2019/5/6
 * @作者 liyz
 */
@SpringBootApplication
//指定dao位置从而创建dao的动态代理类
@MapperScan(basePackages = "cn.jiujiu.DAO")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}

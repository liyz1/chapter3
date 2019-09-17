package cn.jiujiu.controller;

import cn.jiujiu.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @描述 验证码控制器
 * @日期 2019/9/17
 * @作者 liyz
 */
@RestController
@RequestMapping("/verificationCode")
public class VerificationCodeController {
    @RequestMapping("getVerificationCode")
    public void getCheckCode(HttpSession session, HttpServletResponse response){

        //利用验证码工具类生成验证码
        String verificationCode = ValidateImageCodeUtils.getSecurityCode();
        //将验证码存在session中
        session.setAttribute("verificationCode",verificationCode);
        //根据验证码字符串绘制验证码图片
        BufferedImage image = ValidateImageCodeUtils.createImage(verificationCode);
        //使用流打印验证码图片
        ServletOutputStream outputStream =null;
        try {
            outputStream=response.getOutputStream();
            //参数：1.验证码图片对象 2.图片格式 3.图片输出流
            ImageIO.write(image,"png",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

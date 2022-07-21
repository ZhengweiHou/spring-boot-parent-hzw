package com.hzw.learn.springboot.cas.server.controller;

import com.hzw.learn.springboot.cas.server.utils.CheckCodeUtil;
import com.pig4cloud.captcha.GifCaptcha;
import com.pig4cloud.captcha.SpecCaptcha;
import com.pig4cloud.captcha.base.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName CheckCodeController
 * @Description TODO
 * @Author houzw
 * @Date 2022/7/21
 **/

@RestController
public class CheckCodeController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("getcheckcode")
    public void getCheckCode(HttpServletRequest request, HttpServletResponse response) {

        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
//        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        GifCaptcha specCaptcha = new GifCaptcha(130, 48, 5);

        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        // 验证码存入session
        request.getSession().setAttribute("checkcode", specCaptcha.text().toLowerCase());

        log.debug("checkcode:{}",specCaptcha.text().toLowerCase());
        // 输出图片流
        try {
            specCaptcha.out(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

package com.hzw.learn.casserver.cas.controller;

import java.awt.Font;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.pig4cloud.captcha.GifCaptcha;
import com.pig4cloud.captcha.base.Captcha;

public class CaptchaController extends AbstractController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 设置请求头为输出图片类型
		response.setContentType("image/gif");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 三个参数分别为宽、高、位数
//        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
		GifCaptcha specCaptcha = new GifCaptcha(130, 48, 5);

		// 设置字体
		specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32)); // 有默认字体，可以不用设置
		// 设置类型，纯数字、纯字母、字母数字混合
		specCaptcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
		// 验证码存入session
		request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());

		log.debug("checkcode:{}", specCaptcha.text().toLowerCase());
		// 输出图片流
		try {
			specCaptcha.out(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}

package com.cs.sms.client;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * 图片验证码servlet
 *
 */
public class CaptchaServlet extends HttpServlet {
    private static final long serialVersionUID = -90304944339413093L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
//        SpecCaptcha captcha = new SpecCaptcha(130, 48, 6);
        GifCaptcha captcha = new GifCaptcha(130, 48, 6);

        // 设置内置字体
        try {
            captcha.setFont(Captcha.FONT_10);
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        CaptchaUtil.out(captcha, request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

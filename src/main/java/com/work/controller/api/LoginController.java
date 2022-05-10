package com.work.controller.api;

import com.work.pojo.Admin;
import com.work.service.AdminService;
import com.work.service.impl.AdminServiceImpl;
import com.work.utils.FastJsonUtils;
import com.work.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login",value = {"/api/login"})
public class LoginController extends HttpServlet {

    private final AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result res = login(request);
        FastJsonUtils.writeJson(res,response);
    }

    private Result login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");

        HttpSession session = request.getSession();
        String  correctCaptcha = (String) session.getAttribute("captcha");
        if ("".equals(correctCaptcha) || null == correctCaptcha) {
            return Result.fail().message("验证码失效，请刷新后重试");
        }
        if (!correctCaptcha.equalsIgnoreCase(captcha)) {
            return Result.fail().message("验证码有误,请重新输入");
        }

        // 从session域中移除现有验证码
        session.removeAttribute("correctCaptcha");
        try {
            Admin admin = adminService.login(username,password);
            if(null != admin)
                return Result.ok();
            else
                throw new RuntimeException("用户名或者密码有误");
        } catch (RuntimeException e) {
            return Result.fail().message(e.getMessage());
        }
    }
}

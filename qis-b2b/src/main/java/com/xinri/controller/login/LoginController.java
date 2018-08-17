package com.xinri.controller.login;

import com.qis.common.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController extends BaseController {
    //跳转登录页
    @RequestMapping(value="login",method= RequestMethod.GET)
    public String submitLogin( Model model) {
        return "/login";
    }


    //登录请求
    @RequestMapping(value="login",method= RequestMethod.POST)
    public String login(String username, String password, Model model) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            return "redirect:/main";
        } catch (Exception e) {
            logger.info( e.getMessage());
            model.addAttribute("code",500);
            model.addAttribute("msg","登录信息错误，请重新登录！");
            return "/login";
        }

    }


    //跳转登录页
    @RequestMapping(value="main",method= RequestMethod.GET)
    public String index( Model model) {
        return "login/form";
    }
}

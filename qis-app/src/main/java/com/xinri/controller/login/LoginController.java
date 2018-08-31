package com.xinri.controller.login;

import com.qis.common.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController extends BaseController {
    //跳转登录页
    @RequestMapping(value="login",method= RequestMethod.GET)
    public String submitLogin( Model model) {
        Subject s = SecurityUtils.getSubject();
		return s.isRemembered() || s.isAuthenticated() ? "redirect:main" : "login";

    }
//    //登录请求
//    @RequestMapping(value="login",method= RequestMethod.POST)
//    public String login(String username, String password, Boolean rememberMe, Model model) {
//        try {
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//            if(rememberMe!=null&&rememberMe==true)token.setRememberMe(rememberMe);
//            Subject currentUser = SecurityUtils.getSubject();
//            currentUser.login(token);
//            return "redirect:/main";
//        } catch (Exception e) {
//            logger.info( e.getMessage());
//            model.addAttribute("code",500);
//            model.addAttribute("msg","登录信息错误，请重新登录！");
//            return "/login";
//        }
//
//    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
        return "login";
    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String toLogin(HttpServletRequest request, Model model){
//        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
//        String error = null;
//        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
//            error = "用户名/密码错误";
//        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
//            error = "用户名/密码错误";
//        } else if(exceptionClassName != null) {
//            error = "其他错误：" + exceptionClassName;
//        }
//        model.addAttribute("error", error);
//        return "login";  // 跳转到登录页面
//    }


    //跳转登录页
    @RequestMapping(value="main",method= RequestMethod.GET)
    public String index( Model model) {
        return "main";
    }
}

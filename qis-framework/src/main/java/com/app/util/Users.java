package com.app.util;
import com.app.ShiroUser;
import org.apache.shiro.SecurityUtils;


public class Users{
    /**
     * 取出Shiro中的当前用户LoginName.
     */
    public static String name(){
        return ShiroUser().getName();
    }
    public static Long id(){
        return ShiroUser().getId();
    }
    /**
     * 取出Shiro中的当前用户LoginName.
     */
    public static String logiName(){
        return ShiroUser().getLoginName();
    }
    public static ShiroUser ShiroUser(){
        ShiroUser user=(ShiroUser)SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
package com.qis.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xushuangyong on 14-5-29.
 */
public class Utils{
    /**
     * 判断一个字符串是否为空
     *
     * @param str
     *
     * @return
     */
    public static boolean isEmptyStr(String str){
        if(str==null){
            return true;
        }
        str=str.trim();
        return str.isEmpty();
    }

    /**
     * 对集合去重 （无顺序）
     *
     * @param arlList 带去重的集合
     *
     * @return 返回去重后的集合
     */
    public static List removeDuplicate(List arlList){
        if(Utils.isEmpityCollection(arlList)){
            return new ArrayList();
        }
        HashSet h=new HashSet(arlList);
        arlList.clear();
        arlList.addAll(h);
        return arlList;
    }
    /**
     * 判断对象是否为空
     *
     * @param obj
     *
     * @return
     */
    public static boolean isNotNull(Object obj){
        if(obj!=null){
            return true;
        }
        return false;
    }
    /**
     * 判断集合是否为空
     *
     * @param c 待判断的集合
     *
     * @return集合为空,返回true ,反之 false
     */
    public static boolean isEmpityCollection(Collection c){
        if(c==null||c.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isEmptyStr(Object s){
        return (s==null)||(s.toString().trim().length()==0);
    }
    /**
     * 判断传入参数是否为空,空字符串""或"null"或"<null> 为了兼容ios的空获取到<null>字符串
     *
     * @param s 待判断参数
     *
     * @return true 空 <br>
     * false 非空
     */
    public static boolean isEmptyString(Object s){
        return (s==null)||(s.toString().trim().length()==0)||s.toString().trim().equalsIgnoreCase("null")||s.toString().trim().equalsIgnoreCase("<null>");
    }
    /**
     * 对传入参数进行判断是否为空,为空则返回"",反之返回传入参数
     *
     * @param v 传入参数
     *
     * @return 处理后的参数
     */
    public static String filterNullValue(String v){
        return isEmptyString(v)?"":v;
    }

    /**
     * 验证手机号码、邮箱，邮编的合法性
     *
     * @param objValue 手机号码、邮箱，邮编
     *
     * @return 返回手机号码、邮箱，邮编的合法性 true 合法， false 不合法
     */
    public static Boolean CheckParamValidity(String objValue,String tip){
        if(isEmptyString(objValue)){
            return false;
        }
        String regExp = "^((13[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0,5-9]))\\d{8}$";//默认手机
        if("1".equals(tip)){//邮箱
            regExp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        }else if("2".equals(tip)){//邮编
            regExp = "[1-9]\\d{5}(?!\\d)";
        }
        Pattern p=Pattern.compile(regExp);
        Matcher m=p.matcher(filterNullValue(objValue));
        return m.matches();
    }

    public static List deepCopy2(List src) throws IOException, ClassNotFoundException{
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in =new ObjectInputStream(byteIn);
        List dest = (List)in.readObject();
        return dest;
    }
}


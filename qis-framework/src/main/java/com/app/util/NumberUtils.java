package com.app.util;

import java.util.Random;

public class NumberUtils {

    /**
     * 生成随机数
     * @param j
     * @return
     */
    public static String getNumber(int j){
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
        //随机生成数字，并添加到字符串
        for(int i=0;i<j;i++){
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}

package com.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusMsgUtils {
	public static final String SUCCEEDEDCODE_200 = "200"; //
	public static final String ERRORCODE_400 = "400";
	public static final String ERRORCODE_8001 = "8001";

	
	public enum ResponseCode {
		SUCCEEDED_200(SUCCEEDEDCODE_200, "OK"),
		ERROR_400(ERRORCODE_400, "操作失败，请联系管理员"),
		ERROR_8001(ERRORCODE_8001, "操作异常");

		private String name;
		private String code;

		private ResponseCode(String code, String name) {
			this.name = name;
			this.code = code;
		}

		public static String getName(String code) {
			for (ResponseCode rcode : ResponseCode.values()) {
				if (code.equals(rcode.code)) {
					return rcode.name;
				}
			}
			return null;
		}
	}
	
	public static String dateToWeek(Date mdate) {  
	    int b = mdate.getDay();  
	    Date fdate;  
	    String list = "";  
	    Long fTime = mdate.getTime() - b * 24*3600000;  
	    for(int a = 1; a <= 7; a++) {  
	        fdate = new Date();  
	        fdate.setTime(fTime + (a * 24*3600000)); //һ�ܴ���һ��ʼ�㣬��ʹ�ô˷�ʽ
		    DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		    String weekdays=format.format(fdate);
		    if(a==7){
		    	list+="'"+weekdays+"'";
		    }else{
		    	list+="'"+weekdays+"'"+",";
		    }
	        
	    }
	    return list;
	} 

}

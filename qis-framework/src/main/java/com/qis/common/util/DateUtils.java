/**
 */
package com.qis.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author 
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 * "yyyyQ","yyyy
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			String strPattern = pattern[0].toString();
			if(strPattern.equals("Q")){//2016Q1
				return DateFormatUtils.format(date, "yyyy") + "Q" + getQuarter(date);
			}else if(strPattern.equals("H")){//2016Q1
				return DateFormatUtils.format(date, "yyyy") + getHalfYear(date);				
			}else{
				formatDate = DateFormatUtils.format(date, strPattern);
			}
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	 /**
     * @Title: 传入一个日期参数，返回该日期对应的季度：1，2，3，4
	 * @Description: 传入一个日期参数，返回该日期对应的季度：1，2，3，4
	 * @author baddyzhou
     * @return
     */ 
	public static Integer getQuarter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

	    int currentMonth = c.get(Calendar.MONTH) + 1;
	    try {
	        if (currentMonth >= 1 && currentMonth <= 3)
	            return 1;
	        else if (currentMonth >= 4 && currentMonth <= 6)
	            return 2;
	        else if (currentMonth >= 7 && currentMonth <= 9)
	            return 3;
	        else if (currentMonth >= 10 && currentMonth <= 12)
	            return 4;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
        return 0;  
	}
	
	 /**
     * @Title: 传入一个日期参数，返回该日期所在半年：上，下
	 * @Description: 传入一个日期参数，返回该日期所在半年：上，下
	 * @author baddyzhou
     * @return
     */    
    public static String getHalfYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 6){
                return "上";
            }else if (currentMonth >= 7 && currentMonth <= 12){
            	return "下";
            }
         } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
        
    }
    
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 得到当前日期和时间字符串 格式（HHmmss）
	 */
	public static String getCurrentTime() {
		return formatDate(new Date(), "HHmmss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date parseDateByFormat(String dateStr,String format){
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param 
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 判断传入的日期是否为当天
	 * @param date
	 * @return
	 */
	public static boolean isSameDay(Date date) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String ds1 = sdf.format(date);
	    String ds2 = sdf.format(new Date());
	    if (ds1.equals(ds2)) {
	        return true;
	    } else {
	        return false;
	    }
	}
	/**
	 *  传入的date格式为2016-1
	 * @Title: getThisMonthFirstDay
	 * @Description: 获取前月的第一天
	 * @author yaogai
	 * @return
	 */
	public static String getThisMonthFirstDay(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//获取前月的第一天
		String[] dateString =  date.split("-");
		int year = Integer.parseInt(dateString[0].trim());
		int month = Integer.parseInt(dateString[1].trim());
		Calendar c = Calendar.getInstance(); 
		c.set(Calendar.YEAR,year);
		c.set(Calendar.MONTH, month-1);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(c.getTime());
        return firstDay;
        
	}
	/**
	 * 传入的date格式为2016-1-1
	 * @Title: getThisMonthLastDay
	 * @Description: 获取当前月的最后一天
	 * @author yaogai
	 * @return
	 */
	public static String getThisMonthLastDay(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//获取前月的最后一天
		String[] dateString =  date.split("-");
		int year = Integer.parseInt(dateString[0].trim());
		int month = Integer.parseInt(dateString[1].trim());
		Calendar c = Calendar.getInstance();  
		c.set(Calendar.YEAR,year);
		c.set(Calendar.MONTH, month-1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        String lastDay = format.format(c.getTime());
        return lastDay;
	}
	
	/**
	 *  获取当前月的第一天
	 * @Title: getCurrentMonthFirstDay
	 * @Description: 获取当前月的第一天
	 * @author 
	 * @return
	 */
	public static String getCurrentMonthFirstDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();  
		//c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String firstDay = format.format(c.getTime());
        return firstDay;
	}
	/**
	 *  获取当前月的最后一天
	 * @Title: getCurrentMonthFirstDay
	 * @Description: 获取当前月的最后一天
	 * @author 
	 * @return
	 */
	public static String getCurrentMonthLastDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();  
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String lastDay = format.format(c.getTime());
        return lastDay;
	}
	
	 /**
     * @Title: 传入一个日期参数，返回该日期所在月份的第一天
	 * @Description: 传入一个日期参数，返回该日期所在月份的第一天
	 * @author baddyzhou
     * @return
     */
    public static Date getMonthStartTime(Date date) { 	
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        try {
            c.set(Calendar.DATE, 1);  
            
            return parseDate(formatDate(c.getTime(),"yyyy-MM-dd")+ " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

	
	/**
    * @Title: 传入一个日期参数，返回该日期所在月份的最后一天
	* @Description: 传入一个日期参数，返回该日期所在月份的最后一天
	* @author baddyzhou
    * @return
    */
    public static Date getMonthEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        try {
        	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH)); 
            return parseDate(formatDate(c.getTime(),"yyyy-MM-dd") + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }
    
	 /**
     * @Title: 传入一个日期参数，返回该日期所在季度的第一天
	 * @Description: 传入一个日期参数，返回该日期所在季度的第一天
	 * @author baddyzhou
     * @return
     */
    public static Date getQuarterStartTime(Date date) { 	
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);  
            
            return parseDate(formatDate(c.getTime(),"yyyy-MM-dd")+ " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

	
	/**
    * @Title: 传入一个日期参数，返回该日期所在季度的最后一天
	* @Description: 传入一个日期参数，返回该日期所在季度的最后一天
	* @author baddyzhou
    * @return
    */
    public static Date getQuarterEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            return parseDate(formatDate(c.getTime(),"yyyy-MM-dd") + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

	 /**
     * @Title: 传入一个日期参数，返回该日期所在半年的第一天
	 * @Description: 传入一个日期参数，返回该日期所在半年的第一天
	 * @author baddyzhou
     * @return
     */    
    public static Date getHalfYearStartTime(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 6){
                c.set(Calendar.MONTH, 0);
            }else if (currentMonth >= 7 && currentMonth <= 12){
                c.set(Calendar.MONTH, 6);
            }
            c.set(Calendar.DATE, 1);
            
            return parseDate(formatDate(c.getTime(),"yyyy-MM-dd")+ " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
        
    }
	 /**
     * @Title: 传入一个日期参数，返回该日期所在半年的最后一天
	 * @Description: 传入一个日期参数，返回该日期所在半年的最后一天
	 * @author baddyzhou
     * @return
     */ 
    public static Date getHalfYearEndTime(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 6){
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            }else if (currentMonth >= 7 && currentMonth <= 12){
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            
            return parseDate(formatDate(c.getTime(),"yyyy-MM-dd") + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }
    
	 /**
     * @Title: 传入一个日期参数，返回该日期所在年的第一天
	 * @Description: 传入一个日期参数，返回该日期所在年的第一天
	 * @author baddyzhou
     * @return
     */    
    public static Date getYearStartTime(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
       
        try {
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            return parseDate(formatDate(c.getTime(),"yyyy-MM-dd")+ " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
        
    }
	 /**
     * @Title: 传入一个日期参数，返回该日期所在年的最后一天
	 * @Description: 传入一个日期参数，返回该日期所在年的最后一天
	 * @author baddyzhou
     * @return
     */ 
    public static Date getYearEndTime(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        try {
            c.set(Calendar.MONTH, 11);
            c.set(Calendar.DATE, 31);
            return parseDate(formatDate(c.getTime(),"yyyy-MM-dd") + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }    
    

    /**
     * 根据相差的时间返回时间
     * @param date
     * @param day
     * @return
     */
    public static Date getToDate(Date date,Double day){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.DATE, day.intValue());
    	return c.getTime();
    }
    
    /**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (Double.valueOf(afterTime - beforeTime)) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 对指定的日期进行运算
	 * @param date
	 * @return
	 */
	public static Date add(Date day, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(field, amount);
		return calendar.getTime();
	}	
	/**
	 * 获取当前时间的下一个月
	 * @param date
	 * @return
	 */
	public static Date getNextMonthDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}
	
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String dateToStr(Date date) {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	}

	/**
	 *
	 * @Title: getAddDate
	 * @Description: 传入一个整数（天数），返回一个日期，该日期为当前日期+传入天数
	 * @author yaogai
	 * @param days
	 * @return
	 */
	public static Date getAddDate(int days){
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+ days );
		return calendar.getTime();
	}

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date strToDate(String str) {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
		   date = format.parse(str);
	   } catch (ParseException e) {
		   e.printStackTrace();
	   }
	   return date;
	}
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
		//String hm = DateFormatUtils.format(new Date(), "HH:mm");
		//System.out.println(hm);
//		getThisMonthFirstDay("2016-07");getThisMonthLastDay("2016-5");
		String ss = "2016-12-01";
//		Date date = new Date();
		Date date = parseDate(ss);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		System.out.println(c.getTime());
    	c.add(Calendar.MONTH, 1);
    	System.out.println(c.getTime());
//    	System.out.println(Math.round(0.9));
//    	System.out.println(Math.rint(0.9));
//    	System.out.println(Math.ceil(Double.valueOf(82800000)/86400000));
//    	System.out.println(82800000/86400000);
	}
	
}

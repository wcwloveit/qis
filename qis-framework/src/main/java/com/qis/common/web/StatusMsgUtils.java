package com.qis.common.web;

public class StatusMsgUtils {
	public static final String SUCCEEDEDCODE_200 = "200"; // – OK – 一切正常
	public static final String ERRORCODE_400 = "400";
	public static final String ERRORCODE_8001 = "8001";
	public static final String ERRORCODE_300= "300";
	public static final String ERRORCODE_301= "301";
	public static final String ERRORCODE_302= "302";
	public static final String ERRORCODE_303= "303";
	
	public static final String ERRORCODE_403_1 = "403.1";
	public static final String ERRORCODE_403_2 = "403.2";
	
	public static final String ERRORCODE_404_1 = "404.1";
	public static final String ERRORCODE_404_2 = "404.2";
	
	public static final String ERRORCODE_405_1 = "405.1";	
	public static final String ERRORCODE_405_2 = "405.2";

	public static final String ERRORCODE_406_1 = "406.1";	
	public static final String ERRORCODE_406_2 = "406.2";
	public static final String ERRORCODE_406_3 = "406.3";
	public static final String ERRORCODE_406_4 = "406.4";
	public static final String ERRORCODE_406_5 = "406.5";
	public static final String ERRORCODE_406_6 = "406.6";
	public static final String ERRORCODE_406_7 = "406.7";
	public static final String ERRORCODE_406_8 = "406.8";
	
	public static final String ERRORCODE_500_1 = "500.1";
	public static final String ERRORCODE_500_2 = "500.2";
	public static final String ERRORCODE_500_3 = "500.3";
	public static final String ERRORCODE_500_4 = "500.4";
	public static final String ERRORCODE_500_5 = "500.5";
	public static final String ERRORCODE_500_6 = "500.6";
	public static final String ERRORCODE_500_7 = "500.7";
	public static final String ERRORCODE_500_8 = "500.8";
	public static final String ERRORCODE_500_9 = "500.9";
	public static final String ERRORCODE_500_10 = "500.10";
	public static final String ERRORCODE_500_11 = "500.11";
	public static final String ERRORCODE_500_12 = "500.12";
	public static final String ERRORCODE_500_13 = "500.13";
	public static final String ERRORCODE_500_14 = "500.14";
	public static final String ERRORCODE_500_15 = "500.15";
	public static final String ERRORCODE_500_16 = "500.16";
	public static final String ERRORCODE_500_17 = "500.17";
	
	public static final String ERRORCODE_510_1="510.1";
	public static final String ERRORCODE_510_2="510.2";

	public static final String ERRORCODE_511_1="511.1";
	public static final String ERRORCODE_511_2="511.2";
	public static final String ERRORCODE_511_3="511.3";


	
	public enum ResponseCode {
		SUCCEEDED_200(SUCCEEDEDCODE_200, "OK"),
		ERROR_400("400", "操作失败，请联系管理员！"),
		ERROR_8001("8001","操作失败"), 
		ERROR_300("300","excel导入检测不通过！"), 
		ERROR_301("301","excel导入失败，请稍后重试！"), 
		ERROR_302("302","导出数据为空，请稍后重试！"), 
		ERROR_303("303","excel导出失败，请稍后重试！"), 
		
		ERROR_403_1("403.1", "不可以抢别人的线索"), 
		ERROR_403_2("403.2", "登录名已存在"), 
		
		ERROR_404_1("404.1", "指定的员工不存在"), 
		ERROR_404_2("404.2", "指定的登录名不存在"),
		ERROR_404_3("500.1","传递参数错误或漏传"),
		
		ERROR_405_1(ERRORCODE_405_1,"指定员工已是该部门的Leader"),
		ERROR_405_2(ERRORCODE_405_2,"指定部门不存在"),

		ERROR_406_1(ERRORCODE_406_1,"用户名不存在！"),
		ERROR_406_2(ERRORCODE_406_2,"密码错误！"),
		ERROR_406_3(ERRORCODE_406_3,"会话失效，请重新登录！"),
		ERROR_406_4(ERRORCODE_406_4,"密码不能为空！"),
		ERROR_406_5(ERRORCODE_406_5,""),
		ERROR_406_6(ERRORCODE_406_6,"二维码已失效，请扫描最新的二维码！"),
		ERROR_406_7(ERRORCODE_406_7,"文件夹名称已存在，请重新输入！"),
		ERROR_406_8(ERRORCODE_406_8,"输入的旧密码错误！"),

		ERROR_510_1(ERRORCODE_510_1,"无该颜色的车辆信息"),
		ERROR_510_2(ERRORCODE_510_2,"手机号重复！"),


		ERROR_500_3("500.3","用户名或密码为空！"),
		ERROR_500_4(ERRORCODE_500_4,"该车已被零售，如需入库，请走退货流程！"),
		ERROR_500_5(ERRORCODE_500_5,"以下车辆条码在库存中已经存在："),
		ERROR_500_6(ERRORCODE_500_6,"该车辆已经被出售！"),
		ERROR_500_7(ERRORCODE_500_7,"该车辆处于调拨状态中，无法进行该操作！"),
		ERROR_500_8(ERRORCODE_500_8,"该车辆不在库存！"),
		ERROR_500_9("500.9","该订单的状态为已审核状态，不能取消订单！"),
		ERROR_500_10("500.10","SMS系统无该经销商，订单提交失败！"),
		ERROR_500_11("500.11","该车辆已经入库，请换一辆车辆入库！"),
		ERROR_500_12(ERRORCODE_500_12,"该车辆在系统中不存在，请先将该车辆杂项入库！"),
		ERROR_500_13(ERRORCODE_500_13,"该车辆不存在此门店！请从以下经销商调拨到该门店："),
		ERROR_500_14(ERRORCODE_500_14,"该车辆不存在此门店！请从以下门店调拨到该门店："),
		ERROR_500_15(ERRORCODE_500_15,"无此车辆信息！"),
		ERROR_500_16(ERRORCODE_500_16,"以下车辆条码的车辆由于在e尚的产品库不存在，入库异常："),
		ERROR_500_17(ERRORCODE_500_17,"SMS系统提交配件订单接口异常！"),



		ERROR_511_1(ERRORCODE_511_1,"入库失败，入库数据异常！"),
		ERROR_511_2(ERRORCODE_511_2,"登录账号已存在！"),
		ERROR_511_3(ERRORCODE_511_3,"网点名称或联系电话已存在。");

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
}

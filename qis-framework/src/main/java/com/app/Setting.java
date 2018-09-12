package com.app;

/**
 * @author xsy
 */
public class Setting implements java.io.Serializable {
	public static final String INVALID_CLIENT_DESCRIPTION = "qis";
	public static final String LINE_SEPEAC = "#¤∮ΘΨ".toUpperCase() + "";
	public static final String PAGE_SIZE = "10";
	private static final long serialVersionUID = 8014468696683553229L;
	// 登录接口所用的常量 start
	public static final String DELETE = "del";
	public static final String UPDATE = "update";
	public static final String ADD = "add";
	// public static final int cookieTime=1*30*24*3600;
	public static final String SUCCESSSTAT = "200";
	public static final String FAIURESTAT = "500";
	public static final String K1 = "K1";
	public static final String K2 = "K2";
	public static final String RESULTCODE = "resCode";// resCode为200为成功,500为失败
	public static final String MESSAGE = "msg";
	// 登录接口所用的常量 end
	public static final String UPPERANDROIDPHONE = "ANDROID";
	public static final String IOSPHONE = "IOS";
	public static final String IPHONE = "IPHONE";
	// 获得联系人时按中文进行排序
	// public static final String
	// CONTACTORDERSQL="order by nlssort(translate(lower( substr($NAME$,0,1)),'abcdefghijklmnopqrstuvwxyz','啊八嚓滴娥发噶哈哈几亏拉吗呐偶破七然仨他他哇西呀杂'),'NLS_SORT=SCHINESE_PINYIN_M') ";
	public static final String INWORK = "在职"; //
	public static final String NOTWORK = "离职";
	// 服务器后台保存文件地址 begin
	public static final String BASEADDRESS = "/uf/";
	//系统文件
	public static final String BASEADDRESS_SYSTEM = "/uf/system";
	public static final String BIIMGMESSAGE = BASEADDRESS + "/bi";
	//缩略图
	public static final String BASEADDRESS_SMALL = "/uf/small";
	//原图
	public static final String BASEADDRESS_ORIGINAL = "/uf/original";

	//b2b系统文件
	public static final String BASEADDRESS_FILE = "/uf/b2bfile";


	// 服务器后台保存文件地址 end

	// 应用下载页面访问地址前缀
	public static final String appViewsUrl = "/jd";
	public static final String appQrcode ="/appQrcode";
	// 自动生成的文件的下载地址,生成文件地址: appDownLoadUrl+应用的创建时间.jsp
	public static final String appDownLoadUrl = "/WEB-INF/views" + appViewsUrl;// jsp_download

	// 二维码存放路径
	public static final String qrcodeimg = BASEADDRESS + "/qrcodeimg";

	// 自动部署ZIP包存放路径
	public static final String zipFunc = BASEADDRESS + "/functionzip";

	// 自动部署ZIP包备份路径
	public static final String zipFuncBack = BASEADDRESS + "/functionzip-backup";

	// 存放 用户、职责、公司导入的excel
	public static final String downloadExcel = "/download";

	// excel 导出文件的存放地址
	public static final String excelAddress = "/excel";

	// 推送消息的类型,消息的uri字段 start
	public static final String CLEARCLIENT = "clearClient"; // 清除用户
	public static final String CLEARDEVICE = "clearDevice"; // 清除设备
	public static final String UPGRADE = "upgrade_"; // 在线更新
	public static final String PHONEOFFLINE = "OFFLINE"; // iphone挤下线消息
	public static final String ANDROIDOFFLINE = "dwline"; // android挤下线消息
	// 推送消息的类型,消息的uri字段 end

	public static final String USERIMAGE = BASEADDRESS + "employee/photo/";// 用户图像存放位置

	// 系统生成密码常量 begin
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final String PASSWORD = "123456";// 系统默认初始密码
	public static final Integer POICELLLENGTH = 64000;// POI支持excel
														// 导出的最大行数为65536

	// 知识共享文档类型
	public static final String KNOWLEDGE_DOC = "DOC";// 文档类型

	//营销中心编码
	public static final String SALE_CENTRE_CODE = "yxzx";// 营销中心code

	public static final Long DISTRIBUTOR_ORGANIZATION_SUP_ID = -1l;

	// 系统生成密码常量 end
	public enum OnlineType {
		online("1") // 在线
		, off("0"); // 离线
		private String value;

		private OnlineType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	/**
	 * 状态的枚举类型
	 */
	public enum WorkStatusType {
		usable("可用"), purchased("已购买"), prototype("原型"), introduce("介绍"), common("公用"), unusable("不可用"), publicPackage("公共包"), test("测试"), company("企业"), proterotype(
				"原生态");// ,v1_demo("v1_demo"),v3_online("v3_在线"),v3_offline("v3_离线");
		private final String s_type;

		WorkStatusType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 状态的枚举类型
	 */
	public enum DeleteStatusType {
		device("设备擦除"), account("账号擦除"), nodelete("正常"); // 正常登录
		private final String s_type;

		DeleteStatusType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 账号类型的枚举类型
	 */
	public enum AccountType {
		QQ("QQ"), EMAIL("邮箱"), MOBILE("手机");
		private final String stype;

		AccountType(final String stype) {
			this.stype = stype;
		}

		/**
		 * @return Returns the stype.
		 */
		public String getTypeName() {
			return stype;
		}
	}

	/**
	 * 首页图片跳转状态的枚举类型
	 */
	public enum ApplicationJumpType {
		TOAPP("应用"), TOTYPE("类别");
		private final String sype;

		ApplicationJumpType(final String sype) {
			this.sype = sype;
		}

		/**
		 * @return Returns the sype.
		 */
		public String getTypeName() {
			return sype;
		}
	}

	/**
	 * 状态的枚举类型
	 */
	public enum CategoryNameType {
		// A("移动事业部"),B("盈诺德内部"), C("外部");
		移动事业部("A"), 盈诺德内部("B"), 外部("C");
		private final String type;

		CategoryNameType(final String s_type) {
			this.type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return type;
		}
	}

	/**
	 * 所有‘是否’（如 是否精品，是否发布）状态的枚举类型
	 */
	public enum CommonStatusType {
		YES("yes"), NO("no");
		private final String sype;

		CommonStatusType(final String sype) {
			this.sype = sype;
		}

		/**
		 * @return Returns the sype.
		 */
		public String getTypeName() {
			return sype;
		}
	}

	/**
	 * 对appstore 状态的枚举类型
	 */
	public enum ActionStatusType {
		RATING("打分"), COMMENT("评论"), ALL("评论和打分");
		private final String sype;

		ActionStatusType(final String sype) {
			this.sype = sype;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return sype;
		}
	}

	/**
	 * 发送平台的枚举类型
	 */
	public enum PlatformType {
		IPHONE("苹果"), ANDROID("安卓");
		private final String s_type;

		PlatformType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 发送状态的枚举类型
	 */
	public enum SendStatus {
		send("已发送"), receive("已接受"), outline("不在线"), fail("发送失败");
		private final String s_type;

		SendStatus(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 发送类型的枚举类型
	 */
	public enum SendType {
		CONTENT("文字"), IMAGE("图片"), FILE("文件"), AVI("录音"), PLACE("位置"), ADDPERSON("增加人员"), REMOVEPERSON("移除人员"), EXITPERSON("自动退出"), LOOKEXP("表情");
		private final String s_type;

		SendType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	public enum TopicType {
		CUSTOMER("意见反馈跟客服的聊天话题"), COMMONTOPIC("正常聊天的话题");
		private final String s_type;

		TopicType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 发送平台的枚举类型
	 */
	public enum TreeLeafEnum {
		LEAFNODE("叶子节点"), NONLEAFNODE("非叶子节点");
		private final String s_type;

		TreeLeafEnum(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 发送平台的枚举类型
	 */
	public enum UserPerview {
		SUPERADMIN("超级管理员"), COMMONADMIN("普通管理员");
		private final String s_type;

		UserPerview(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 蓝牙设备 状态对应的枚举类型
	 */
	public enum ChannelStatusType {
		AVAILABLE("可用"), DISABLE("禁用");
		private final String sype;

		ChannelStatusType(final String sype) {
			this.sype = sype;
		}

		/**
		 * @return Returns the sype.
		 */
		public String getTypeName() {
			return sype;
		}
	}

	/**
	 * 蓝牙设备 设备类型对应的枚举类型
	 */
	public enum ChannelType {
		BATTERY("电池"), AC("交流电");
		private final String sype;

		ChannelType(final String sype) {
			this.sype = sype;
		}

		/**
		 * @return Returns the sype.
		 */
		public String getTypeName() {
			return sype;
		}
	}

	/**
	 * 下载渠道状态的枚举类型
	 */
	public enum ChanStatusType {
		others("其他"), appstore("APPSTORE"), qrcodeImage("二维码"), weixin("微信");
		private final String u_type;

		ChanStatusType(final String u_type) {
			this.u_type = u_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return u_type;
		}
	}

	/**
	 * 状态的枚举类型
	 */
	public enum LikeStatusType {
		interesting("感兴趣"), good("赞一下"), nothing("未进行操作"), download("已下载"), codeNum("验证码");
		private final String u_type;

		LikeStatusType(final String u_type) {
			this.u_type = u_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return u_type;
		}
	}

	/**
	 * 类名称: [PlateformType] 类描述: [证书平台枚举类型] 创建时间: [2014-5-27 上午11:13:09]
	 */
	public enum PlateformType {
		IOS("IOS"), ANDROID("ANDROID");
		private final String m_type;

		PlateformType(final String m_type) {
			this.m_type = m_type;
		}

		/**
		 * @return Returns the m_type.
		 */
		public String getM_type() {
			return m_type;
		}
	}

	public enum ThirdSystemType {
		assistSystem("辅系统"), mainSystem("主系统");
		private final String type;

		ThirdSystemType(final String u_type) {
			this.type = u_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return type;
		}
	}

	public enum VersionType {
		ANDROID("Android_手机版"), ANDROID_PAD("Android_平板"), IPHONE("IPHONE"), IPAD("IPAD"), PC("PC");
		private final String m_type;

		VersionType(final String m_type) {
			this.m_type = m_type;
		}

		/**
		 * @return Returns the m_type.
		 */
		public String getM_type() {
			return m_type;
		}
	}

	/**
	 * 性别状态的枚举类型
	 */
	public enum SexType {
		man("男性"), woman("女性"), unkown("未知");
		// ,v1_demo("v1_demo"),v3_online("v3_在线"),v3_offline("v3_离线");
		private final String s_type;

		SexType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	public enum CommentAuthType {
		anyone, authorization, none
	}

	/**
	 * 操作日记类型
	 */
	public enum OptType {
		NEWS, APPROVAL, NOTICE
	}

	/**
	 * IOS/ANDROID发送消息的类别
	 */
	public enum MessageType {
		systemmes("系统消息"), intermes("接口推送消息"), clearuser("账号擦除"), cleardevice("设备擦除"), onlineupdate("在线更新"), sys("ios系统信息");
		private final String m_type;

		MessageType(final String m_type) {
			this.m_type = m_type;
		}

		public String getM_type() {
			return m_type;
		}
	}

	/**
	 * IOS/ANDROID 发送消息的状态
	 */
	public enum MsgState {
		nosend("未发送"), send("已发送"), received("已接收"); // /,lookover("已查看");
		private final String m_state;

		MsgState(final String m_state) {
			this.m_state = m_state;
		}

		public String getM_state() {
			return m_state;
		}
	}

	/**
	 * 反馈意见问题表
	 */
	public enum FeedProblenType {
		problem("问题"), answer("答案");
		private final String m_state;

		FeedProblenType(final String m_state) {
			this.m_state = m_state;
		}

		public String getM_state() {
			return m_state;
		}
	}

	/**
	 * 团队建议的枚举
	 */
	public enum ActiveType {
		ENABLE("启用"), DISABLE("禁用");
		private final String s_type;

		ActiveType(final String s_type) {
			this.s_type = s_type;
		}

		public String getTypeName() {
			return s_type;
		}
	}

	public enum StationType {
		ENABLE("在职"), DISABLE("离职");
		private final String s_type;

		StationType(final String s_type) {
			this.s_type = s_type;
		}

		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * IPHONE推送类型表示
	 */
	public enum Xtype {
		UPGRADE("版本更新"), EBS("EBS推送信息"), OFFLINE("强制下线");
		private final String xtype;

		Xtype(final String xtype) {
			this.xtype = xtype;
		}

		public String getTypeName() {
			return xtype;
		}
	}

	/**
	 * 保存、提交状态的枚举类型
	 */
	public enum SaveSubmitStatusType {
		SAVE("未提交"), SUBMIT("已提交");
		private final String s_type;

		SaveSubmitStatusType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 销售计划负责人的枚举类型
	 */
	public enum SalePlanChargePersonType {
		YEAR_OVER("年度总体目标负责人"), SIGN_BILL("负责人"), PLACE("下单计划责任人"), ORDER("下单责任人"), PROJECT("项目经理"), CONTRACT("合同推进责任人"), TICKET("开票计划责任人"), RECEIVE("回款计划责任人");
		private final String s_type;

		SalePlanChargePersonType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 销售计划的枚举类型
	 */
	public enum SalePlanType {
		YEAR_OVER("年度总体计划"), SIGN_BILL("签单计划"), PLACE("下单计划"), TICKET("开票计划"), RECEIVE("回款计划");
		private final String s_type;

		SalePlanType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 销售出货的枚举类型
	 */
	public enum SaleStoreType {
		SAMPLE("样板"), BATCH("批量");
		private final String s_type;

		SaleStoreType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 销售的枚举类型
	 */
	public enum SaleType {
		YEAR("年计划"), QUARTER("季计划"), MONTH("月计划"), YEAR_MON("年月计划"), YEAR_MED("年月编辑计划"), QUART_MON("季月计划"), QUART_MED("季月编辑计划");
		private final String s_type;

		SaleType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 项目重要信息的枚举类型
	 */
	public enum ProjectMoreObjectType {
		PROJECT("项目"), BID_DOC("标书"), PLAN("计划");// 项目、标书、计划
		private final String s_type;

		ProjectMoreObjectType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 项目重要信息的枚举类型
	 */
	public enum ProjectMoreType {
		PRODUCT("产品"), DOOR_SHEET("门板"), TABLE_FACE("台面");// 产品/门板/台面
		private final String s_type;

		ProjectMoreType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 开票枚举类型
	 */
	public enum InvioceActualType {
		TICKET("开票"), RECEIVE("回款");// 开票\回款
		private final String s_type;

		InvioceActualType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 开票选项枚举类型
	 */
	public enum InvioceActualInvoiceType {
		PRE_PAY("预付款"), PROGRESS("进度款"), SETTLE("结算款"), WARRANTY("质保金");
		private final String s_type;

		InvioceActualInvoiceType(final String s_type) {
			this.s_type = s_type;
		}

		/**
		 * @return Returns the s_type.
		 */
		public String getTypeName() {
			return s_type;
		}
	}

	public enum ShowHidePhone {
		show("显示手机号"), hide("隐藏手机号");
		private final String xtype;

		ShowHidePhone(final String xtype) {
			this.xtype = xtype;
		}

		public String getTypeName() {
			return xtype;
		}
	}

	/**
	 * 管理员类型
	 * 
	 * @author Wayen
	 *
	 */
	public enum ManagerCode {
		Admin("Admin");
		private final String code;

		ManagerCode(final String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}

	public enum LogOperationType {
		appPublish("应用发布"), login("登录"), logout("登出"), clearDevice("设备删除"), customerExport("客户导出"), authAssign("分配权限");
		private final String option_type;

		LogOperationType(final String option_type) {
			this.option_type = option_type;
		}

		public String getOption_type() {
			return option_type;
		}
	}

	/**
	 * 竞争对手中图片分类的枚举
	 */
	public enum CompetitorImgType {
		DOORWAY("门头"), GALLERY("展厅"), OTHER("其他");
		private final String s_type;

		CompetitorImgType(final String s_type) {
			this.s_type = s_type;
		}

		public String getTypeName() {
			return s_type;
		}
	}

	/**
	 * 网点维修人员类型
	 */
	public enum ServicerTypeEnum {
		engineer("维修工程师","engineer"), leader("网点维修负责人","leader"),engineerAndLeader("工程师兼维修负责人","engineerAndLeader");
		private String name;
		private String code;
		private ServicerTypeEnum(String name, String code) {
			this.name = name;
			this.code = code;
		}

		// 普通方法
		public static String getName(String code) {
			for (ServicerTypeEnum c : ServicerTypeEnum.values()) {
				if (c.getCode() == code) {
					return c.name;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(int index) {
			this.code = code;
		}
	}
}

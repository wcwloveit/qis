/**
 */
package com.qis.common.web;

import com.qis.common.beanvalidator.BeanValidators;
import com.qis.common.mapper.JsonMapper;
import com.qis.common.persistence.Page;
import com.qis.common.util.DateUtils;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
//import javax.validation.ConstraintViolationException;
//import javax.validation.ValidationException;
//import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 控制器支持类
 * @author 
 * @version 
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());


//	/**
//	 * 验证Bean实例对象
//	 */
//	@Autowired
//	protected Validator validator;
//
	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
//	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
//		try{
//			BeanValidators.validateWithException(validator, object, groups);
//		}catch(ConstraintViolationException ex){
//			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
//			list.add(0, "数据验证失败：");
//			addMessage(model, list.toArray(new String[]{}));//待完善
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
//	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
//		try{
//			BeanValidators.validateWithException(validator, object, groups);
//		}catch(ConstraintViolationException ex){
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
//	protected boolean beanValidator(Object object, Class<?>... groups) {
//		try {
//			BeanValidators.validateWithException(validator, object, groups);
//		} catch (ConstraintViolationException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
//	protected void addMessage(Model model, String... messages) {
//		StringBuilder sb = new StringBuilder();
//		for (String message : messages){
//			sb.append(message).append(messages.length>1?"<br/>":"");
//		}
//		model.addAttribute("message", sb.toString());
//	}
	
	/**
	 * 添加Flash消息
	 * @param messages
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object), "application/json");
	}
	
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}


	/**
	 * 构建返回的json数据
	 * @param code 状态码
	 * @param msg  返回消息
	 * @param data 数据
	 * @param response
	 * @return
	 */
	protected String responseJsonData(String code, String msg, Object data,HttpServletResponse response) {
		Map<String, Object> dataMap = Maps.newHashMap();
		dataMap.put("code", code);
		dataMap.put("msg", msg);
		
		if(code.startsWith("20")){//成功的状态码才返回数据
			if(data instanceof Page){
				Page page = (Page) data;
				dataMap.put("totalSize", page.getTotalSize());
				dataMap.put("data", page.getData());
			}else{
				if(data!=null)
				dataMap.put("data", data);
			}
		}
		return renderString(response,dataMap);
	}


	
	
	
	
	
	/**
	 * 
	 * @param response
	 * @param flag  成功失败的标记
	 * @param errorCode  错误时对应的错误编码
	 * @param data   需要返回的数据，如没有则填写null
	 * @return  null用于发送数据了
	 */                                    
	protected String renderStringForDataMapBase64(HttpServletResponse response,int flag,String errorCode,Object data){
		Map<String, Object> dataMap = Maps.newHashMap();
		dataMap.put("code", "200");
		dataMap.put("msg", StatusMsgUtils.ResponseCode.getName("200"));
		dataMap.put("isEncoded", 1);
		if (flag <=0) {
			dataMap.put("code",errorCode);
			dataMap.put("msg", StatusMsgUtils.ResponseCode.getName(errorCode));
		}
		if(data!=null){
			//dataMap.put("data",data);
			if(data instanceof Page){
				Page page = (Page) data;
				dataMap.put("totalSize", page.getTotalSize());
				String baseStr = null;
				try {
					baseStr = new String(Base64.encodeBase64(JsonMapper.toJsonString(page.getData()).getBytes("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("Base64 Error"+e.getMessage());
				}
				dataMap.put("data", baseStr);
			}else{
				if(data!=null){
					String baseStr = null;
				try {
					baseStr = new String(Base64.encodeBase64(JsonMapper.toJsonString(data).toString().getBytes("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("Base64 Error"+e.getMessage());
				}
				dataMap.put("data", baseStr);
				}
			}
		}
		return renderString(response,dataMap);
	}
	
	/**
	 * 
	 * @param response
	 * @param flag  成功失败的标记
	 * @param errorCode  错误时对应的错误编码
	 * @param data   需要返回的数据，如没有则填写null
	 * @return  null用于发送数据了
	 */ 
	protected String renderStringForDataMap(HttpServletResponse response,int flag,String errorCode,Object data){
		Map<String, Object> dataMap = Maps.newHashMap();
		dataMap.put("code", "200");
		dataMap.put("msg", StatusMsgUtils.ResponseCode.getName("200"));
		if (flag <=0) {
			dataMap.put("code",errorCode);
			dataMap.put("msg", StatusMsgUtils.ResponseCode.getName(errorCode));
		}
		if(data!=null){
			dataMap.put("data",data);
		}
		return renderString(response,dataMap);
	}
	
	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class})//, ConstraintViolationException.class, ValidationException.class
    public String bindException() {  
        return "error/400";
    }
	
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}
	
	/**
	 * 构建返回的json数据
	 * @param code 状态码
	 * @param msg  返回消息
	 * @param data 数据
	 * @param response
	 * @return Base64码
	 */
	protected String responseJsonDataBase64(String code, String msg,
			Object data, HttpServletResponse response) {
		Map<String, Object> dataMap = Maps.newHashMap();
		dataMap.put("code", code);
		dataMap.put("msg", msg);
		dataMap.put("isEncoded", 1);

		if (code.startsWith("20")) {// 成功的状态码才返回数据
			if (data instanceof Page) {
				Page page = (Page) data;
				dataMap.put("totalSize", page.getTotalSize());
				String baseStr = null;
				try {
					baseStr = new String(Base64.encodeBase64(JsonMapper
							.toJsonString(page.getData()).getBytes("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("Base64 Error" + e.getMessage());
				}
				dataMap.put("data", baseStr);
			} else {
				if (data != null) {
					String baseStr = null;
					try {
						baseStr = new String(Base64.encodeBase64(JsonMapper
								.toJsonString(data).toString()
								.getBytes("UTF-8")));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("Base64 Error" + e.getMessage());
					}
					dataMap.put("data", baseStr);
				}
			}
		}
		return renderString(response, dataMap);
	}
	
	/**
	 * 客户端返回JSON字符串的BASE64
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderStringBase64ResultMap(HttpServletResponse response, Map<String, Object> resultMap) {
		String baseStr = null;
		try {
			baseStr = new String(Base64.encodeBase64(JsonMapper.toJsonString(resultMap.get("data")).getBytes("UTF-8")));
			resultMap.put("data", baseStr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("Base64 Error"+e.getMessage());
		}
		return renderString(response, resultMap);
	}
	
}

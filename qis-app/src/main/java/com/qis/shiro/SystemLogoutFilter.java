package com.qis.shiro;

//import com.coracle.xsimple.service.safety.LogService;

import com.qis.ShiroUser;
import com.qis.common.util.StringUtils;
import com.xinri.po.logs.LoginLogs;
import com.xinri.service.logs.ILoginLogsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SystemLogoutFilter extends LogoutFilter {

	@Autowired
	protected ILoginLogsService loginLogsService;

	
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// 在这里执行退出系统前需要清空的数据
		HttpServletRequest req = (HttpServletRequest) request;
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Subject subject = getSubject(request, response);
		String ip = req.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			// 若以上三种方式均为获取到ip则证明获得客户端并没有采用反向代理直接使用getRemoteAddr()获取客户端的ip地址
			ip = request.getRemoteAddr();
		}
		try {

			LoginLogs loginLogs=new LoginLogs();
			loginLogs.setIpAddress(ip);
			loginLogs.setUserId(user.getId());
			loginLogs.setIsEffective(user.getType());
			loginLogs.setDataTypeId(36L);
			loginLogsService.saveOrUpdate(loginLogs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String redirectUrl = getRedirectUrl(request, response, subject);
		try {
			subject.logout();
			StringBuffer content= new StringBuffer();
			content.append("来自IP为[").append(ip).append("]的用户");
		//	content.append("[").append(user.getName()).append("]").append("在[").append(DateUtil.getInstance().getDateStr(new Date(), "yyyy-MM-dd HH:mm")).append("]做了[");
		//	content.append(Setting.LogOperationType.logout.getOption_type()).append("]").append("行为");
		//	logService.saveLog(user,Setting.LogOperationType.logout.name(), content.toString());
		} catch (SessionException ise) {
			ise.printStackTrace();
		}
		issueRedirect(request, response, redirectUrl);
		return false;

	}

	//public void setLogService(LogService logService) {
	//	this.logService = logService;
//	}
}

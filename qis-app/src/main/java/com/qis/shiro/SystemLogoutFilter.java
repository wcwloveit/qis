package com.qis.shiro;

//import com.coracle.xsimple.service.safety.LogService;

import com.qis.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SystemLogoutFilter extends LogoutFilter {

//	protected LogService logService;

	
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// 在这里执行退出系统前需要清空的数据
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Subject subject = getSubject(request, response);
		String ip = SecurityUtils.getSubject().getSession().getHost();
		//String ip = "";
		try {
		//	ip = Utils.getIpAddr((HttpServletRequest) request);
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

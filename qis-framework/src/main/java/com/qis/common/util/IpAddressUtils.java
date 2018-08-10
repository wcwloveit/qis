/**
 */
package com.qis.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取IP地址
 * 
 * @author
 * @version
 */
public class IpAddressUtils {

	public static String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}

		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-real-ip");//获取nginx的真实ip
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		if (ip.startsWith("10.")) {// 如果是10开头，那么取远程IP
			ip = request.getRemoteAddr();
		}

		if (ip.indexOf(",") != -1) {
			ip = ip.replaceAll(" ", "");
			String[] subIp = ip.split(",");
			if (ip.indexOf("127.0.0.1") != -1) {
				if (subIp.length == 2) {
					for (int i = 0; i < subIp.length; i++) {
						if (!subIp[i].equals("127.0.0.1")) {
							ip = subIp[i];
							break;
						}
					}
				}
			} else {
				if (subIp.length >= 1) {
					ip = subIp[0];
				}
			}
		}
		return ip;
	}
}

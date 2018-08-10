package com.qis.common.util;

import java.util.Set;

/**
 * 
 * 版权所有：2016-圆舟科技
 * 项目名称：pm-framework   
 *
 * 类描述：
 * 类名称：com.coracle.common.util.JedisHandle     
 * 创建人：闫志刚
 * 创建时间：2016年9月6日 上午10:27:32   
 * 修改人：
 * 修改时间：2016年9月6日 上午10:27:32   
 * 修改备注：   
 * @version   V1.0
 */
public interface JedisHandle {

	public String set(final String key, final Object value) throws Exception;
	
	public Object get(final String key) throws Exception;
	
	public String delete(final String key) throws Exception;
	
	public Set<byte[]> getKeys(final String key) throws Exception;
	
}
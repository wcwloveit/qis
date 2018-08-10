package com.qis.common.util;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClusterCommand;

import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * 
 * 版权所有：2016-圆舟科技
 * 项目名称：pm-framework   
 *
 * 类描述：redis 分布式 java客户端处理
 * 类名称：com.coracle.common.util.JedisClusterHandle     
 * 创建人：闫志刚
 * 创建时间：2016年9月6日 上午10:26:25   
 * 修改人：
 * 修改时间：2016年9月6日 上午10:26:25   
 * 修改备注：   
 * @version   V1.0
 */
public class JedisClusterHandle implements JedisHandle {

	private static Logger logger = Logger.getLogger(JedisClusterHandle.class);
	
	@Override
	public String set(final String key, final Object value) throws UnsupportedEncodingException {
		final byte[] keyByte = key.getBytes(JedisUtil.charsert);
		return new JedisClusterCommand<String>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public String execute(Jedis jedis) {
				String result = null;
				try {
					result = jedis.set(keyByte, SerializeUtil.serialize(value));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (JedisUtil.expireTime > 0) jedis.expire(keyByte, JedisUtil.expireTime);
				return result;
			}
		}.run(key);
	}

	@Override
	public Object get(final String key) throws UnsupportedEncodingException {
		final byte[] keyByte = key.getBytes(JedisUtil.charsert);
		return new JedisClusterCommand<Object>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public Object execute(Jedis jedis) {
				Object obj = null;
				try {
					obj = SerializeUtil.unserialize(jedis.get(keyByte));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return obj;
			}
		}.run(key);
	}

	@Override
	public String delete(final String key) throws UnsupportedEncodingException {
		final byte[] keyByte = key.getBytes(JedisUtil.charsert);
		return new JedisClusterCommand<String>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public String execute(Jedis jedis) {
				return jedis.del(keyByte).toString();
			}
		}.run(key);
	}

	@Override
	public Set<byte[]> getKeys(final String key) throws UnsupportedEncodingException {
		final byte[] keyByte = key.getBytes(JedisUtil.charsert);
		return new JedisClusterCommand<Set<byte[]>>(JedisUtil.connectionHandler, JedisUtil.maxRedirections) {
			@Override
			public Set<byte[]> execute(Jedis jedis) {
				return jedis.keys(keyByte);
			}
		}.run(key);
	}
	
}
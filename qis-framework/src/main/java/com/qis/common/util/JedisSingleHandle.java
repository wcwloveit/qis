package com.qis.common.util;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * 
 * 版权所有：2016-圆舟科技
 * 项目名称：pm-framework   
 *
 * 类描述：jedis 单机版 处理
 * 类名称：com.coracle.common.util.JedisSingleHandle     
 * 创建人：闫志刚
 * 创建时间：2016年9月6日 上午10:24:10   
 * 修改人：
 * 修改时间：2016年9月6日 上午10:24:10   
 * 修改备注：   
 * @version   V1.0
 */
public class JedisSingleHandle implements JedisHandle {
	
	private static Logger logger = Logger.getLogger(JedisSingleHandle.class);

	@Override
	public String set(String key, Object value) throws Exception {
		Jedis jedis = null;
		String result = "";
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			byte[] jedisKeyByte = key.getBytes(JedisUtil.charsert);
			result = jedis.set(jedisKeyByte, SerializeUtil.serialize(value));
			if (JedisUtil.expireTime > 0)
				jedis.expire(jedisKeyByte, JedisUtil.expireTime);
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnBrokenResource(jedis);
		}
		return result;
	}

	@Override
	public Object get(String key) throws UnsupportedEncodingException {
		Jedis jedis = null;
		Object value = null;
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			byte[] bytes = jedis.get(key.getBytes(JedisUtil.charsert));
			if(null != bytes)
			value = SerializeUtil.unserialize(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnBrokenResource(jedis);
		}
		return value;
	}

	@Override
	public String delete(String key) throws UnsupportedEncodingException {
		Jedis jedis = null;
		Object value = null;
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			value = jedis.del(key.getBytes(JedisUtil.charsert));
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnBrokenResource(jedis);
		}
		return value.toString();
	}

	@Override
	public Set<byte[]> getKeys(String key) throws UnsupportedEncodingException {
		Jedis jedis = null;
		Set<byte[]> value = null;
		try {
			jedis = JedisUtil.jedisPool.getResource();
			jedis.select(JedisUtil.dbIndex);
			value = jedis.keys(key.getBytes(JedisUtil.charsert));
		} finally {
			if (jedis != null) JedisUtil.jedisPool.returnBrokenResource(jedis);
		}
		return value;
	}
	
}
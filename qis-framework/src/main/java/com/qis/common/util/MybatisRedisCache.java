package com.qis.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * 版权所有：2016-圆舟科技
 * 项目名称：pm-framework   
 *
 * 类描述：Mybatis Redis 缓存实现
 * 类名称：com.coracle.common.util.MybatisRedisCache     
 * 创建人：闫志刚
 * 创建时间：2016年9月6日 上午9:42:35   
 * 修改人：
 * 修改时间：2016年9月6日 上午9:42:35   
 * 修改备注：   
 * @version   V1.0
 */
public class MybatisRedisCache implements Cache {

	private static Logger logger = Logger.getLogger(MybatisRedisCache.class);
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private String id;

	public MybatisRedisCache(final String id) {
		if (id == null) throw new IllegalArgumentException("初始化MybatisRedisCache必须传入Mapper ID");
		this.id = id;
	}

	private String getKey(Object key) {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(":");
		sb.append(DigestUtils.md5Hex(String.valueOf(key)));
		return sb.toString();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getSize() {
		int result = 0;
		try {
			String jedisKey = id + ":*";
			Set<byte[]> keys = JedisUtil.getKeys(jedisKey);
			if (null != keys && !keys.isEmpty()) result = keys.size();
			logger.info("MybatisRedisCache getSize：" + jedisKey);
		} catch (Exception e) {
			logger.error("查询缓存size发生异常[" + id + "]: " + e.getMessage());
		}
		return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		try {
			String jedisKey = getKey(key);
			JedisUtil.set(jedisKey, value);
			logger.info("MybatisRedisCache putObject：" + jedisKey);
		} catch (Exception e) {
			logger.error("添加缓存发生异常[" + id + "]: " + e.getMessage());
		}
	}

	@Override
	public Object getObject(Object key) {
		Object value = null;
		try {
			String jedisKey = getKey(key);
			value = JedisUtil.get(jedisKey);
			logger.info("MybatisRedisCache getObject：" + jedisKey);
		} catch (Exception e) {
			logger.error("获取缓存发生异常[" + id + "]: " + e.getMessage());
		}
		return value;
	}

	@Override
	public Object removeObject(Object key) {
		Object value = null;
		try {
			String jedisKey = getKey(key);
			value = JedisUtil.delete(jedisKey);
			logger.info("MybatisRedisCache removeObject：" + jedisKey);
		} catch (Exception e) {
			logger.error("移除缓存发生异常[" + id + "]: " + e.getMessage());
		}
		return value;
	}

	@Override
	public void clear() {
		try {
			String jedisKey = id + ":*";
			Set<byte[]> keys = JedisUtil.getKeys(jedisKey);
			for (byte[] key : keys)
				JedisUtil.delete(new String(key));
			logger.info("MybatisRedisCache clear：" + jedisKey);
		} catch (Exception e) {
			logger.error("清理缓存发生异常[" + id + "]: " + e.getMessage());
		}
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}
	
}
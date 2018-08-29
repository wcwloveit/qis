package com.qis.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisService {
	
	private RedisTemplate<String, Serializable> redisTemplate;

	public RedisTemplate<String, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// 加入缓存
	public void add(String key, byte[] value) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		ValueOperations<String, Serializable> valueops = redisTemplate.opsForValue();
		valueops.set(key, value);
	}
	
	// 加入字符串缓存
	public void add(String key, String value) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		ValueOperations<String, Serializable> valueops = redisTemplate.opsForValue();
		valueops.set(key, value);
	}

	// 加入缓存，并设置缓存时间
	public void add(String key, String value, long timeout, TimeUnit unit) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		ValueOperations<String, Serializable> valueops = redisTemplate.opsForValue();
		valueops.set(key, value, timeout, unit);
	}
	
	// 加入缓存，并设置缓存时间
	public void add(String key, byte[] value, long timeout, TimeUnit unit) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		ValueOperations<String, Serializable> valueops = redisTemplate.opsForValue();
		valueops.set(key, value, timeout, unit);
	}

	// 如果这个key不在redis中就设置
	public boolean setIfAbsent(String key, byte[] value) {
		ValueOperations<String, Serializable> valueops = redisTemplate.opsForValue();
		boolean result = valueops.setIfAbsent(key, value);
		return result;
	}

	// 获取缓存的对象
	public String getString(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		ValueOperations<String, Serializable> valueops = redisTemplate.opsForValue();
		String value =  (String) valueops.get(key);
		return value;
	}
	// 获取缓存的对象
	public byte[] getValue(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		ValueOperations<String, Serializable> valueops = redisTemplate.opsForValue();
		byte[] value = (byte[]) valueops.get(key);
		return value;
	}

	// 获取一段时间自增序列
	public Long getSequence(String key, long timeout, TimeUnit timeUnit) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
		if (!exists(key)) {
			valueOperations.set(key, null, timeout, timeUnit);
		}
		Long sequence = valueOperations.increment(key, 1l);
		return sequence;
	}

	// 单个删除
	public void delete(String key) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		redisTemplate.delete(key);
	}

	public void delete(Collection<String> keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return;
		}
		redisTemplate.delete(keys);
	}

	// 判断key是否存在
	public boolean exists(String key) {
		if (StringUtils.isBlank(key)) {
			return false;
		}
		Boolean b = redisTemplate.hasKey(key);
		return b == null ? false : b.booleanValue();
	}

	// 设置指定key的过期时间
	public boolean expire(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	// 设置指定key的过期时间
	public boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	// 获取key过期剩余时间，单位是秒
	public Long getExpire(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		if (exists(key)) {
			return redisTemplate.getExpire(key);
		} else {
			return null;
		}
	}

	// 获取key过期剩余时间
	public Long getExpire(String key, TimeUnit unit) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		if (exists(key)) {
			return redisTemplate.getExpire(key, unit);
		} else {
			return null;
		}
	}
	
	/** 
     * 设置 map 
     */  
    public void setMap(String key ,Map<String,Object> map){  
    	if (StringUtils.isBlank(key)) {
			return;
		}
		HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
		opsForHash.putAll(key,map);
    }  
	
	public void setMap(String key , Map<String,Object> map, long timeout, TimeUnit unit) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
		opsForHash.putAll(key,map);
		redisTemplate.expire(key, timeout, unit);
	}
    /** 
     * 获取map 
     */  
    public Map<String,Object> getMap(String key){  
    	if (StringUtils.isBlank(key)) {
			return null;
		}
    	Map<String,Object> map = new HashMap<String, Object>();
    	BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps(key);  
    	map = boundHashOperations.entries(); 
		return map;
    }  
}
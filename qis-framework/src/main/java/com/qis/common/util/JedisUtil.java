package com.qis.common.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import redis.clients.jedis.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 
 * 版权所有：2016-圆舟科技
 * 项目名称：pm-framework   
 *
 * 类描述：
 * 类名称：com.coracle.common.util.JedisUtil     
 * 创建人：闫志刚
 * 创建时间：2016年9月6日 上午10:01:46   
 * 修改人：
 * 修改时间：2016年9月6日 上午10:01:46   
 * 修改备注：   
 * @version   V1.0
 */
public class JedisUtil {

	private static Logger logger = Logger.getLogger(JedisUtil.class);

	/** redis普通连接 */
	public static JedisPool jedisPool;
	
	/** redis普通连接数据库索引 */
	public static int dbIndex=1;
	
	/** redis集群连接 */
	public static JedisClusterConnectionHandler connectionHandler;
	
	/** 缓存过期时间，单位秒，大于0有效 */
	public static int expireTime = 600;
	
	/** CUD不刷新缓存（redis.cud.not.flush.key配置关键字，英文逗号隔开，如果要清理的key包含这些关键字， 则不清理） */
	public static String[] cudNotFlushKeys;
	
	/** key 前缀，开发，测试，部署阶段使用不同前缀，避免缓存数据冲突 */
	public static String keyPrefix = "Dev:";
	
	/** 编码 */
	public static final String charsert = "utf-8";
	
	/** jedis 处理器*/
	public static JedisHandle jedisHandle = null;
	
	/** 集群模式redis地址前缀 */
	private static final String redisClusterAddrPrefix = "redis.cluster.addr";
	
	/** 集群模式redis地址正则表达式 */
	private static Pattern redisClusterHostPatt = Pattern.compile("^.+[:]\\d{1,5}\\s*$");
	
	/** 集群模式redis节点 */
	private static Set<HostAndPort> redisClusterNodes = new HashSet<HostAndPort>();
	
	/** 一个请求允许最多尝试的次数 */
	public static int maxRedirections = 10;

	/** redis 配置文件 */
	private static Properties properties;

	static {
		String propName = "redis.properties";
		properties = new Properties();
		InputStream is = null;
		BufferedReader bf = null;
		is = JedisUtil.class.getResourceAsStream("/" + propName);
		if(is==null) is = JedisUtil.class.getResourceAsStream(propName);
		try {
			bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			properties.load(bf);
		} catch (Exception e) {
			logger.error(propName + "属性文件读取失败");
		} finally {
			try {// 文件流关闭
				if (bf != null) bf.close();
				if (is != null) is.close();
			} catch (IOException e) {}
		}
		
		keyPrefix = properties.getProperty("redis.key.prefix");
		maxRedirections = Integer.valueOf(properties.getProperty("redis.maxRedirections"));
		expireTime = Integer.valueOf(properties.getProperty("redis.expire.time"));
		String cudNotFlushKey = properties.getProperty("redis.cud.not.flush");
		if (cudNotFlushKey.trim().length() > 1)
			cudNotFlushKeys = cudNotFlushKey.trim().split(",");
		
		//集群模式
		if(properties.getProperty("redis.cluster").equals("1")){
			// 读取redis节点配置
			for (Object key : properties.keySet()) {
				if (!((String) key).startsWith(redisClusterAddrPrefix)) continue;
				String val = (String) properties.get(key);
				boolean isHost = redisClusterHostPatt.matcher(val).matches();
				if (!isHost) throw new IllegalArgumentException("redis 集群配置 ip 或 port 不合法");
				String[] hostStr = val.split(":");
				HostAndPort host = new HostAndPort(hostStr[0], Integer.valueOf(hostStr[1]));
				redisClusterNodes.add(host);
			}
			//初始化连接池
			GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
			genericObjectPoolConfig.setMaxIdle(Integer.valueOf(properties.getProperty("redis.pool.maxIdle")));
			genericObjectPoolConfig.setMaxWaitMillis(Long.valueOf(properties.getProperty("redis.pool.maxWait")));
			// 创建连接
			connectionHandler = new JedisSlotBasedConnectionHandler(redisClusterNodes, genericObjectPoolConfig, 
					Integer.valueOf(properties.getProperty("redis.timeout")));
			jedisHandle = new JedisClusterHandle();
		} 
		else {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(Integer.valueOf(properties.getProperty("redis.pool.maxIdle")));
			config.setMaxWaitMillis(Long.valueOf(properties.getProperty("redis.pool.maxWait")));
			if (properties.getProperty("redis.password").trim().length() > 0) {
				jedisPool = new JedisPool(config, properties.getProperty("redis.host"),
						Integer.valueOf(properties.getProperty("redis.port")),
						Integer.valueOf(properties.getProperty("redis.timeout")),
						properties.getProperty("redis.password"));
			}
			else {
				jedisPool = new JedisPool(config, properties.getProperty("redis.host"),
						Integer.valueOf(properties.getProperty("redis.port")),
						Integer.valueOf(properties.getProperty("redis.timeout")));
				jedisHandle = new JedisSingleHandle();
			}
		}
	}

	public static String set(final String key, final Object value) throws Exception {
		return jedisHandle.set(JedisUtil.keyPrefix + key, value);
	}

	public static Object get(final String key) throws Exception {
		return jedisHandle.get(JedisUtil.keyPrefix + key);
	}

	public static String delete(final String key) throws Exception {
		// 根据规则匹配关键字不主动删除缓存
		if (cudNotFlushKeys != null && cudNotFlushKeys.length > 0) {
			for (String cudNotFlushKey : cudNotFlushKeys) {
				if (key.contains(cudNotFlushKey)) {
					logger.info(cudNotFlushKey + " don't need clear on CUD...");
					return "0";
				}
			}
		}
		return jedisHandle.delete(JedisUtil.keyPrefix + key);
	}

	public static Set<byte[]> getKeys(final String key) throws Exception {
		return jedisHandle.getKeys(JedisUtil.keyPrefix + key);
	}
	
}
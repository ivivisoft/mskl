package com.mskl.service.redis;

import com.alibaba.fastjson.TypeReference;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

public interface RedisClient {

	void append(String key, String value);
	
	void clear();
	
	void delete(String key);
	
	void expire(String key, int seconds);
	
	String get(String key);
	
	<T> List<T> getArray(String key, Class<T> clazz);
	
	<T> T getObject(String key, Class<T> clazz);
	
	<T> T getObject(String key, TypeReference<T> type);
	
	StringRedisTemplate getRedisTemplate();
	
	Long increment(String key);
	
	Properties info()throws Exception;
	
	boolean isExists(String key);
	
	List<String> multiGet(Collection<String> keyList);
	
	<T> List<T> multiGet(Collection<String> keyList, Class<T> clazz);
	
	void set(String key, Object value);
	
	void set(String key, Object value, int liveSeconds) ;

}

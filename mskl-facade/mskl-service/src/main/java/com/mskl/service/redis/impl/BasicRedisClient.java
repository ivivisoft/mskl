package com.mskl.service.redis.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mskl.service.redis.RedisClient;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasicRedisClient implements RedisClient {

	private StringRedisTemplate redisTemplate;
	
	public void append(String key, String value) {
		redisTemplate.opsForValue().append(key, value);
	}
	
	public void clear() {
		redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
				return "ok";
            }
		});
	}

	public void delete(String key) {
		redisTemplate.delete(key);
	}

	public void expire(String key, int seconds) {
		redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}
	
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public <T> List<T> getArray(String key, Class<T> clazz) {
		return JSON.parseArray(get(key), clazz);
	}
	
	public <T> T getObject(String key, Class<T> clazz) {
		return JSON.parseObject(get(key), clazz);
	}

	public <T> T getObject(String key, TypeReference<T> type) {
		return JSON.parseObject(get(key), type);
	}

	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public Long increment(String key) {
		return redisTemplate.opsForValue().increment(key, 1);
	}

	public Properties info(){
		return redisTemplate.execute(new RedisCallback<Properties>(){
			public Properties doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.info();
			}
		});
	}

	public boolean isExists(String key) {
		return redisTemplate.hasKey(key);
	}

	public List<String> multiGet(Collection<String> keyList) {
		return redisTemplate.opsForValue().multiGet(keyList);
	}

	public <T> List<T> multiGet(Collection<String> keyList, Class<T> clazz) {
		List<String> strList = multiGet(keyList);
		List<T> ojbList = new ArrayList<T>();
		if(strList != null){
			for(String s : strList){
				ojbList.add(JSON.parseObject(s, clazz));
			}
		}
		return ojbList;
	}

	public void set(String key, Object value) {
		if (value instanceof String) {
			redisTemplate.opsForValue().set(key, (String)value);
		}else{
			redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
		}
	}

	public void set(String key, Object value, int liveSeconds) {
		if (value instanceof String) {
			redisTemplate.opsForValue().set(key, (String)value, liveSeconds, TimeUnit.SECONDS);
		}else{
			redisTemplate.opsForValue().set(key, JSON.toJSONString(value), liveSeconds, TimeUnit.SECONDS);
		}
	}

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}

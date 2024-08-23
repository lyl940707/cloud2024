package com.lyl.common.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;

public class MyCacheManager extends RedisCacheManager {

    private static final String SPILIT_CHAR = "#";

    public MyCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    public MyCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
    }

    public MyCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, boolean allowInFlightCacheCreation, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
    }

    public MyCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
    }

    public MyCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        Duration ttl = getTtl(name);
        RedisSerializer<String> strSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
        cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(ttl)
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(strSerializer))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair
//                        .fromSerializer(jacksonSeial))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(strSerializer))
                .computePrefixWith(cacheName -> {
                    return getCachePrefix(cacheName);
                })
                .disableCachingNullValues();
        return super.createRedisCache(name,cacheConfig);
    }

    private String getCachePrefix(String name) {
        if(StrUtil.isBlank(name)){
            throw new RuntimeException("缓存名称不能为空");
        }
        String[] nameArr = name.split(SPILIT_CHAR);
        if(nameArr.length <= 1){
            return name;
        }else{
            return nameArr[0];
        }
    }

    private Duration getTtl(String name) {
        if(StrUtil.isBlank(name)){
            return Duration.ofMinutes(10);
        }
        String[] nameArr = name.split(SPILIT_CHAR);
        if(nameArr.length <= 1){
            return Duration.ofMinutes(10);
        }
        try {
            long ttl = Long.parseLong(nameArr[1]);
            return Duration.ofMinutes(ttl);
        }catch (Exception e){
            return Duration.ofMinutes(10);
        }
    }
}

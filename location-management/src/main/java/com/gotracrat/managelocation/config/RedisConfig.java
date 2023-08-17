

/*
 * package com.gotracrat.managelocation.config; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.cache.CacheManager; import
 * org.springframework.cache.annotation.EnableCaching; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.PropertySource; import
 * org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
 * import org.springframework.data.redis.cache.RedisCacheManager; import
 * org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
 * import org.springframework.data.redis.core.RedisTemplate;
 * 
 * @Configuration
 * 
 * @EnableCaching public class RedisConfig {
 * 
 * 
 * 
 * @Bean public static PropertySourcesPlaceholderConfigurer
 * propertySourcesPlaceholderConfigurer() { return new
 * PropertySourcesPlaceholderConfigurer(); }
 * 
 * @Bean JedisConnectionFactory jedisConnectionFactory() {
 * JedisConnectionFactory factory = new JedisConnectionFactory();
 * factory.setHostName("localhost"); factory.setPort(6379);
 * factory.setUsePool(true); return factory; }
 * 
 * @Bean RedisTemplate<Object, Object> redisTemplate() { RedisTemplate<Object,
 * Object> redisTemplate = new RedisTemplate<Object, Object>();
 * redisTemplate.setConnectionFactory(jedisConnectionFactory());
 * redisTemplate.setEnableTransactionSupport(true); return redisTemplate; }
 * 
 * @Bean CacheManager cacheManager() { return new
 * RedisCacheManager(redisTemplate()); } }
 */
 


// copied from google

/*
 * @Configuration public class RedisConfig {
 * 
 * @Value("${redisCentralCachingURL}") private String redisHost;
 * 
 * @Value("${redisCentralCachingPort}") private int redisPort;
 * 
 * @Bean public StringRedisSerializer stringRedisSerializer() {
 * StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
 * return stringRedisSerializer; }
 * 
 * @Bean JedisConnectionFactory jedisConnectionFactory() {
 * JedisConnectionFactory factory = new JedisConnectionFactory();
 * factory.setHostName(redisHost); factory.setPort(redisPort);
 * factory.setUsePool(true); return factory; }
 * 
 * @Bean public RedisTemplate<String, Object> redisTemplate() {
 * RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
 * redisTemplate.setConnectionFactory(jedisConnectionFactory());
 * redisTemplate.setExposeConnection(true); // No serializer required all
 * serialization done during impl
 * redisTemplate.setKeySerializer(stringRedisSerializer());
 * redisTemplate.setEnableTransactionSupport(true);
 * //`redisTemplate.setHashKeySerializer(stringRedisSerializer());
 * redisTemplate.setHashValueSerializer(new RedisConfig());
 * redisTemplate.afterPropertiesSet();
 * 
 * return redisTemplate; }
 * 
 * @Bean public RedisCacheManager cacheManager() { RedisCacheManager
 * redisCacheManager = new RedisCacheManager(redisTemplate());
 * redisCacheManager.setTransactionAware(true);
 * redisCacheManager.setLoadRemoteCachesOnStartup(true);
 * redisCacheManager.setUsePrefix(true); return redisCacheManager; }
 * 
 * }
 */
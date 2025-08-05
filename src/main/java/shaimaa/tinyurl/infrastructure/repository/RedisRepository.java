package shaimaa.tinyurl.infrastructure.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    void setKey(String key, String data) {
        redisTemplate.opsForValue().set(key, data);
    }

    String getKey(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}

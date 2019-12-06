package com.example.LearnHowToUserGit.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings("unchecked")
public class CacheAccessUtilsRedisImpl implements CacheAccessUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheAccessUtilsRedisImpl.class);

    @Autowired
    RedisTemplate redisTemplate;

// =============================common============================
    /**
     * Set expiration time by key.
     *
     * @param key
     * @param time
     * @return
     */
    public Boolean setExpireTime(String key, long time) {
        boolean bool;
        if (time > 0) {
            bool = redisTemplate.expire(key, time, TimeUnit.SECONDS);
        } else {
            LOGGER.error("The expire time must be positive.");
            bool = false;
        }
        return bool;
    }

    /**
     * Get the expiration time by given key.
     *
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * Contain the key or not.
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * Delete one key-value or more than one.
     *
     * @param keys
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    // ============================String=============================
    /**
     * Get a value by the given key in string.
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * Set a key(String)-value.
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Set a key(String)-value with expiration time.
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                return true;
            } else {
                LOGGER.error("Expiration time must be positive.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Increase.
     *
     * @param key
     * @param delta
     * @return
     */
    public Long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("The delta must be positive.");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * Decrease.
     *
     * @param key
     * @param delta
     * @return
     */
    public Long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("The delta must be negative.");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================Map=================================

    /**
     * @param key
     * @param hashKey
     * @return
     */
    public Object hashGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hashMapGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hashMapSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hashMapSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                setExpireTime(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean hashValueSet(String key, String hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hashValueSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                setExpireTime(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete the key-values according to hashKeys in the map corresponding with {@code key}.
     *
     * @param key      Variable name of the map.
     * @param hashKeys One or more than one key that included in the map named {@code key}.
     */
    public void hashDelete(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * Whether the map corresponding with {@code key} has {@code hashKey} or not.
     *
     * @param key     Variable name of the map.
     * @param hashKey Hash key that included in the map corresponding with "key".
     * @return
     */
    public boolean hasHashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * Progressive increase.If the map doesn't exist,a new one will be created and returned after being increased.
     *
     * @param key       Variable name of the map.
     * @param hashKey   Hash key that included in the map corresponding with "key".
     * @param increment The amount that will be increased automatically.
     * @return
     */
    public double hashIncr(String key, String hashKey, double increment) {
        return redisTemplate.opsForHash().increment(key, hashKey, increment);
    }

    /**
     * Progressive decrease.If the map doesn't exist,a new one will be created and returned after being decreased.
     *
     * @param key       Variable name of the map.
     * @param hashKey   Hash key that included in the map corresponding with "key".
     * @param decrement The amount that will be decreased automatically.
     * @return
     */
    public double hashDecr(String key, String hashKey, double decrement) {
        return redisTemplate.opsForHash().increment(key, hashKey, -decrement);
    }

    // ============================set=============================

    /**
     * Get the set at {@code key}.
     *
     * @param key Name of the set.
     * @return
     */
    public Set<Object> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * Check if set at {@code key} contains {@code value}.
     *
     * @param key   The key of the set.
     * @param value An element that may be contained in the set.
     * @return
     */
    public Boolean isSetMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * Put one or more values into the set at {@code key}.
     *
     * @param key    The key of the set.
     * @param values One or more elements that will be added into the set.
     * @return
     */
    public Long addToSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * Put one or more values into the set at {@code key} and set the expiration time.
     *
     * @param key    The key of the set.
     * @param time   Seconds
     * @param values values One or more elements that will be added into the set.
     * @return
     */
    public Long addToSetWithTime(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (time > 0) {
            boolean hasExpirationTime = setExpireTime(key, time);
            if (hasExpirationTime) {
                LOGGER.error("Failed to set expiration time.");
                throw new RuntimeException("Failed to set expiration time.");
            }
        } else {
            LOGGER.error("The expiration time should be positive.");
            throw new RuntimeException("The expiration time should be positive.");
        }
        return count;
    }

    /**
     * Get the size of the set.
     *
     * @param key The key of the set.
     * @return The size of the set.
     */
    public Long getSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * Remove one or more values from the set at {@code key}.
     *
     * @param key    The key of the set.
     * @param values one or more values in the set.
     * @return the amount of the removed elements.
     */
    public Long removeFromSet(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    // ===============================list=================================

    /**
     * Get some continuous nodes from list at {@code key}.
     *
     * @param key   The key of the list.
     * @param start
     * @param end   From 0 to -1 means all the nodes.
     * @return The partial list.
     */
    public List<Object> getList(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * Get size of list at {@code key}.
     *
     * @param key The key of the list.
     * @return
     */
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * Get node at {@code index} from list at {@code key} .
     *
     * @param key   The key of the list.
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object getListNode(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * Append {@code value} to the list.
     *
     * @param key   The key of the list.
     * @param value
     * @return
     */
    public Long appendToList(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * Append {@code value} to the list and set the expiration time.
     *
     * @param key   The key of the list.
     * @param value
     * @param time  The expiration time(second).
     * @return
     */
    public Long appendToListWithTime(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        if (time > 0) {
            boolean hasExpirationTime = setExpireTime(key, time);
            if (hasExpirationTime) {
                LOGGER.error("Failed to set expiration time.");
                throw new RuntimeException("Failed to set expiration time.");
            }
        } else {
            LOGGER.error("The expiration time should be positive.");
            throw new RuntimeException("The expiration time should be positive.");
        }
        return index;
    }

    /**
     * Append a {@code list}to the existing list at {@code key}.
     *
     * @param key
     * @param list
     * @return
     */
    public Long appendList(String key, List<Object> list) {
        return redisTemplate.opsForList().rightPushAll(key, list);
    }

    /**
     * Append a {@code list}to the existing list at {@code key} with expiration time.
     *
     * @param key
     * @param list
     * @param time
     * @return
     */
    public Long appendListWithTime(String key, List<Object> list, long time) {
        Long count = redisTemplate.opsForList().rightPushAll(key, list);
        if (time > 0) {
            boolean hasExpirationTime = setExpireTime(key, time);
            if (hasExpirationTime) {
                LOGGER.error("Failed to set expiration time.");
                throw new RuntimeException("Failed to set expiration time.");
            }
        } else {
            LOGGER.error("The expiration time should be positive.");
            throw new RuntimeException("The expiration time should be positive.");
        }
        return count;
    }

    /**
     * Update a node according to {@code index} in the list at {@code key}.
     *
     * @param key
     * @param index
     * @param value
     * @return
     */
    public void setListValue(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * Removes the first {@code count} occurrences of {@code value} from the list stored at {@code key}.
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }
}
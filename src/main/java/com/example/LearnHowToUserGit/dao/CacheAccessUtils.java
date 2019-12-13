package com.example.LearnHowToUserGit.dao;

public interface CacheAccessUtils {
    /**
     * Set expiration time by key.
     *
     * @param key
     * @param time
     * @return
     */
    Boolean setExpireTime(String key, long time);

    Long getExpire(String key);

    Boolean containKey(String key);

    void del(String... keys);

    Object get(String key);

    boolean set(String key, Object value);

    boolean set(String key, Object value,long timeToLive);
}

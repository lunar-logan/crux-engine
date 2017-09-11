package com.wiggler.crux.core.cache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class CacheManager {
    private static volatile CacheManager instance = null;

    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null) {
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

    private final Map<String, Object> caches = new ConcurrentHashMap<>();

    private CacheManager() {
    }

    public void setCache(Cache cache) {
        Objects.requireNonNull(cache);
        caches.put(cache.getClass().getName(), cache);
    }

    public <T> T getCache(Class<T> clazz) {
        Objects.requireNonNull(clazz);
        return (T) caches.get(clazz.getName());
    }
}

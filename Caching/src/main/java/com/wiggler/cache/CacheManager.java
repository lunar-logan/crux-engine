package com.wiggler.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CacheManager {
    private static volatile CacheManager instance = null;

    public static CacheManager getInstance(Cache... caches) {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null) {
                    instance = new CacheManager(caches);
                }
            }
        }
        return instance;
    }

    private CacheManager(Cache... caches) {
        for (Cache cache : caches) {
            try {
                cache.load();
                cacheMap.put(cache.getClass(), cache);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void invalidate() {
        synchronized (CacheManager.class) {
            instance = null;
        }
    }

    private final Map<Class, Object> cacheMap = new ConcurrentHashMap<>();

    public <T> T getInstance(Class<T> clazz) {
        return (T) cacheMap.get(clazz);
    }

    public static void main(String[] args) {
        CacheManager cacheManager = CacheManager.getInstance(new StopwordsCache());
        System.out.println(cacheManager.getInstance(StopwordsCache.class));
    }
}

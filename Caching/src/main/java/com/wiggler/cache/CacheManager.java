package com.wiggler.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class CacheManager {
    private static volatile CacheManager instance = null;
    private static final AtomicBoolean invalid = new AtomicBoolean(true);

    public static CacheManager getInstance(Cache... caches) {
        if (instance == null || invalid.get()) {
            synchronized (CacheManager.class) {
                if (instance == null || invalid.get()) {
                    instance = new CacheManager(caches);
                }
            }
        }
        return instance;
    }

    private CacheManager(Cache... caches) {
        if (invalid.compareAndSet(true, false)) {
            for (Cache cache : caches) {
                try {
                    cache.load();
                    cache.freeze();
                    cacheMap.put(cache.getClass(), cache);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void reload() {
        if (invalid.get()) {
            synchronized (CacheManager.class) {
                if (invalid.compareAndSet(true, false)) {
                    for (Map.Entry<Class, Object> entry : cacheMap.entrySet()) {
                        Cache cache = (Cache) entry.getValue();
                        try {
                            cache.load();
                            cache.freeze();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public boolean invalidate() {
        return invalid.compareAndSet(false, true);
    }

    private final Map<Class, Object> cacheMap = new ConcurrentHashMap<>();

    public <T> T getInstance(Class<T> clazz) {
        if (invalid.get()) {
            reload();
        }
        return (T) cacheMap.get(clazz);
    }

    public static void main(String[] args) {
        CacheManager cacheManager = CacheManager.getInstance(new StopwordsCache());
        System.out.println(cacheManager.getInstance(StopwordsCache.class));
    }
}

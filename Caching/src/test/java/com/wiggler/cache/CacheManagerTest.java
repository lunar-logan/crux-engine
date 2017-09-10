package com.wiggler.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CacheManagerTest {
    private String[] idNamePairs = {"48:Anurag", "48:Superman", "48:Batman"};
    private int curIndex = -1;
    private CacheManager cacheManager;

    static class NameCache implements Cache {

        private final Supplier<Map<String, String>> supplier;
        private Map<String, String> map = null;

        public NameCache(Supplier<Map<String, String>> supplier) {
            this.supplier = supplier;
        }

        @Override
        public void load() throws Exception {
            map = new HashMap<>();
            map.putAll(supplier.get());
        }

        @Override
        public void freeze() {
            map = Collections.unmodifiableMap(map);
        }

        public String getName(String id) {
            return map.get(id);
        }
    }

    @Before
    public void setup() {
        Cache names = new NameCache(() -> {
            String pair = idNamePairs[++curIndex];
            curIndex = curIndex % idNamePairs.length;
            String[] kv = pair.split(":");
            HashMap<String, String> map = new HashMap<>();
            map.put(kv[0], kv[1]);
            return map;
        });

        cacheManager = CacheManager.getInstance(new StopwordsCache(), names);
    }

    @Test
    public void testGetInstance() {
        Assert.assertNotNull(cacheManager);
        Assert.assertNotNull(cacheManager.getInstance(StopwordsCache.class));
    }

    @Test
    public void testGetInstanceUnderSingleThread() throws InterruptedException {
        Assert.assertNotNull(cacheManager);
        NameCache nameCache = cacheManager.getInstance(NameCache.class);
        Assert.assertNotNull(nameCache);
        Assert.assertEquals("Anurag", cacheManager.getInstance(NameCache.class).getName("48"));
        cacheManager.invalidate();
        Assert.assertEquals("Superman", cacheManager.getInstance(NameCache.class).getName("48"));
        cacheManager.invalidate();
        Assert.assertEquals("Batman", cacheManager.getInstance(NameCache.class).getName("48"));
    }
}
package com.wiggler.crux.core.cache;

public interface Cache {
    void load() throws Exception;

    void freeze();
}

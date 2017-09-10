package com.wiggler.cache;

public interface Cache {
    void load() throws Exception;

    /**
     * Freeze all your structures to ensure NO {@link java.util.ConcurrentModificationException}
     */
    void freeze();
}

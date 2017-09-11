package com.wiggler.crux.core.graph;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class NodeCache implements com.wiggler.crux.core.cache.Cache {
    private static final int SIZE = 10_000; //
    private AtomicReferenceArray<String> data;

    @Override
    public void load() throws Exception {
        data = new AtomicReferenceArray<>(SIZE);
    }

    @Override
    public void freeze() {
    }

    public boolean put(int i, String label) {
        if (i < 0 || i >= data.length())
            throw new IndexOutOfBoundsException("Invalid index: " + i + " ");

        return data.compareAndSet(i, null, label);
    }

    public String get(int i) {
        if (i < 0 || i >= data.length())
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        return data.get(i);
    }
}

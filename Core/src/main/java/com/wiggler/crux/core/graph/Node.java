package com.wiggler.crux.core.graph;

import com.wiggler.crux.core.cache.CacheManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {
    private static final AtomicInteger ID_GEN = new AtomicInteger(0);
    private final int id;
    private final String label;
    private Map<String, Edge> connections = null;
    private Map<String, Object> attributes = null;

    public Node(String label) {
        Objects.requireNonNull(label);
        this.id = ID_GEN.incrementAndGet();
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Map<String, Edge> getConnections() {
        if (connections == null) {
            connections = new HashMap<>();
        }
        return connections;
    }

    public Map<String, Object> getAttributes() {
        if (attributes == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(attributes);
    }

    public Object setAttribute(String key, Object value) {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        return attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        if (attributes != null) {
            return attributes.get(key);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", label=" + label +
                '}';
    }
}

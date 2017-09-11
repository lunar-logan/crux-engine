package com.wiggler.crux.core.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEdge implements Edge {
    protected final Node u;
    protected final Node v;
    protected final Map<String, Object> attributes = new HashMap<>();

    public AbstractEdge(Node u, Node v) {
        this.u = u;
        this.v = v;
    }

    @Override
    public Node u() {
        return u;
    }

    @Override
    public Node v() {
        return v;
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public Object setAttribute(String key, Object value) {
        return attributes.put(key, value);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
}

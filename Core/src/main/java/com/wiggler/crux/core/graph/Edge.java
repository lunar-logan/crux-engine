package com.wiggler.crux.core.graph;

import java.util.Map;

public interface Edge {
    Node u();

    Node v();

    boolean isDirected();

    Map<String, Object> getAttributes();

    Object getAttribute(String name);

    Object setAttribute(String key, Object value);
}

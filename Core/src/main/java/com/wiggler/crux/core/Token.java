package com.wiggler.crux.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Token {
    private String value;
    private int index;
    private final Map<String, Object> attributes = new HashMap<>();
    private final List<Token> children = new ArrayList<>();

    public Token(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public List<Token> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", index=" + index +
                ", attributes=" + attributes +
                ", children=" + children +
                '}';
    }
}

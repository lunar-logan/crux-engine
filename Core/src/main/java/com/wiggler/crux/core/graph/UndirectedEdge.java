package com.wiggler.crux.core.graph;

public class UndirectedEdge extends AbstractEdge {
    public UndirectedEdge(Node u, Node v) {
        super(u, v);
    }

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public String toString() {
        return "UndirectedEdge{" +
                "u=" + u +
                ", v=" + v +
                ", getAttributes=" + attributes +
                '}';
    }
}

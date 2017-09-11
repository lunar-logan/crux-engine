package com.wiggler.crux.core.graph;

public class DirectedEdge extends AbstractEdge {
    public DirectedEdge(Node u, Node v) {
        super(u, v);
    }

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "u=" + u +
                ", v=" + v +
                ", getAttributes=" + attributes +
                '}';
    }
}

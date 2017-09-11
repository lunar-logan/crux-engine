package com.wiggler.crux.core.graph;

import java.util.*;

public class Graph {
    private final Map<String, Node> nodes = new HashMap<>();

    public Graph() {
    }

    public Iterable<Node> getNodes() {
        return nodes.values();
    }

    public void addNode(Node node) {
        Objects.requireNonNull(node);
        nodes.put(node.getLabel(), node);
    }

    public Node getNode(String label) {
        return nodes.get(label);
    }

    public void addConnection(String labelA, String labelB, boolean directed) {
        Node aNode = nodes.get(labelA);
        Node bNode = nodes.get(labelB);
        if (aNode != null && bNode != null) {
            if (!aNode.getConnections().containsKey(labelB)) {
                if (directed) {
                    aNode.getConnections().put(labelB, new DirectedEdge(aNode, bNode));
                } else {
                    aNode.getConnections().put(labelB, new UndirectedEdge(aNode, bNode));
                    bNode.getConnections().put(labelA, new UndirectedEdge(bNode, aNode));
                }
            }
        }
    }

    public Edge getConnection(String labelA, String labelB) {
        Node aNode = nodes.get(labelA);
        Node bNode = nodes.get(labelB);
        Edge e = null;
        if (aNode != null && bNode != null) {
            if (aNode.getConnections().containsKey(labelB)) {
                e = aNode.getConnections().get(labelB);
            } else if (bNode.getConnections().containsKey(labelA)) {
                e = bNode.getConnections().get(labelA);
            }
        }
        return e;
    }

    public List<Node> bfs(String src, String target) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(target);

        Node srcNode;
        Node targetNode;
        List<Node> path = new ArrayList<>();
        if ((srcNode = getNode(src)) != null && (targetNode = nodes.get(target)) != null) {
            Deque<Node> Q = new LinkedList<>();
            Set<Node> seen = new HashSet<>();
            Q.offer(srcNode);
            while (!Q.isEmpty()) {
                Node u = Q.pop();
                path.add(u);
                seen.add(u);
                if (u == targetNode) {
                    break;
                }
                u.getConnections().values()
                        .stream()
                        .map(Edge::v)
                        .filter(v -> !seen.contains(v))
                        .forEach(Q::offer);
            }
        }
        return path;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode(new Node("price"));
        g.addNode(new Node("value"));
        g.addNode(new Node("cost"));
        g.addConnection("price", "cost", false);
        g.addConnection("price", "value", false);

        System.out.println(g.bfs("cost", "value"));
    }
}

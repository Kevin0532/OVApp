package com.example.openov.Utilz;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private String nodeName;
    private Map<Node, Integer> neighbors; // Map to store neighbors along with distances

    public Node(String nodeName) {
        this.nodeName = nodeName;
        this.neighbors = new HashMap<>();
    }

    public String getNodeName() {
        return nodeName;
    }

    public Map<Node, Integer> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor, int distance) {
        neighbors.put(neighbor, distance);
        neighbor.neighbors.put(this, distance); // Assuming an undirected graph, add reverse relationship
    }

    public void removeNeighbor(Node neighbor) {
        neighbors.remove(neighbor);
        neighbor.neighbors.remove(this); // Remove reverse relationship
    }
}

package algorithms.greedy;

import java.util.*;

class PrimMST {
    public List<Edge> findMST(Graph graph) {
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Set<Node> visited = new HashSet<>();
        Node startNode = graph.nodes.getFirst(); // Changed getFirst() to get(0)

        visit(startNode, pq, visited);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            Node node = edge.to;
            if (visited.contains(node)) continue;
            mst.add(edge);
            visit(node, pq, visited);
        }

        return mst;
    }

    private void visit(Node node, PriorityQueue<Edge> pq, Set<Node> visited) {
        visited.add(node);
        for (Edge edge : node.edges) {
            if (!visited.contains(edge.to)) {
                pq.add(edge);
            }
        }
    }
}

class Node {
    int id;
    List<Edge> edges = new ArrayList<>();

    public Node(int id) {
        this.id = id;
    }
}

class Edge {
    Node from;
    Node to;
    int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Graph {
    List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node from, Node to, int weight) {
        Edge edge = new Edge(from, to, weight);
        from.edges.add(edge);
        to.edges.add(edge);
    }
}

class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.addEdge(node1, node2, 1);
        graph.addEdge(node1, node3, 3);
        graph.addEdge(node2, node3, 1);
        graph.addEdge(node2, node4, 6);
        graph.addEdge(node3, node4, 2);

        PrimMST prim = new PrimMST();
        List<Edge> mst = prim.findMST(graph);

        System.out.println("Edges in MST:");
        for (Edge edge : mst) {
            System.out.println(edge.from.id + " - " + edge.to.id + ": " + edge.weight);
        }
    }
}

package algorithms.greedy;

import java.util.*;

public class KruskalMST {

    public List<int[]> findMST(int numNodes, List<int[]> edges) {
        List<int[]> mst = new ArrayList<>();
        edges.sort(Comparator.comparingInt(e -> e[2]));

        DisjointSet disjointSet = new DisjointSet(numNodes);

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int root1 = disjointSet.find(from);
            int root2 = disjointSet.find(to);

            if (root1 != root2) {
                mst.add(edge);
                disjointSet.union(root1, root2);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        int numNodes = 4;
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[]{0, 1, 1});
        edges.add(new int[]{0, 2, 3});
        edges.add(new int[]{1, 2, 1});
        edges.add(new int[]{1, 3, 6});
        edges.add(new int[]{2, 3, 2});

        KruskalMST kruskal = new KruskalMST();
        List<int[]> mst = kruskal.findMST(numNodes, edges);

        System.out.println("Edges in MST:");
        for (int[] edge : mst) {
            System.out.println(edge[0] + " - " + edge[1] + ": " + edge[2]);
        }
    }

    public int[] minimumSpanningTree(int[] edges, int[] weights) {
        return edges;
    }
}

class DisjointSet {
    int[] parent, rank;

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}

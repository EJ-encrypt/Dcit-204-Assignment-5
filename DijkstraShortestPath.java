package algorithms.greedy;

import java.util.*;

public class DijkstraShortestPath {

    public int[] dijkstra(int numNodes, List<int[]>[] adjList, int src) {
        int[] dist = new int[numNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int distance = current[1];

            if (distance > dist[node]) continue;

            for (int[] neighbor : adjList[node]) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                int newDist = dist[node] + weight;

                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.add(new int[]{nextNode, newDist});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int numNodes = 5;
        List<int[]>[] adjList = new List[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adjList[i] = new ArrayList<>();
        }

        adjList[0].add(new int[]{1, 10});
        adjList[0].add(new int[]{2, 3});
        adjList[1].add(new int[]{2, 1});
        adjList[1].add(new int[]{3, 2});
        adjList[2].add(new int[]{1, 4});
        adjList[2].add(new int[]{3, 8});
        adjList[2].add(new int[]{4, 2});
        adjList[3].add(new int[]{4, 7});
        adjList[4].add(new int[]{3, 9});

        DijkstraShortestPath dsp = new DijkstraShortestPath();
        int[] distances = dsp.dijkstra(numNodes, adjList, 0);

        System.out.println("Shortest distances from node 0:");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("To node " + i + ": " + distances[i]);
        }
    }
}

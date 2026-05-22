import java.util.*;


class Edge {
    int destination;
    int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}


class Graph {
    private int vertices;
    private List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }


    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
        adjacencyList.get(destination).add(new Edge(source, weight)); // undirected graph
    }


    public void dijkstra(int start) {
        int[] distance = new int[vertices];
        boolean[] visited = new boolean[vertices];
        int[] parent = new int[vertices]; // to reconstruct paths


        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);


        distance[start] = 0;


        for (int i = 0; i < vertices; i++) {

            int u = getMinDistanceVertex(distance, visited);

            if (u == -1) break;


            visited[u] = true;


            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;


                if (!visited[v] && distance[u] != Integer.MAX_VALUE
                        && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    parent[v] = u;
                }
            }
        }


        printResults(start, distance, parent);
    }


    private int getMinDistanceVertex(int[] distance, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && distance[v] < minDistance) {
                minDistance = distance[v];
                minVertex = v;
            }
        }
        return minVertex;
    }


    private void printResults(int start, int[] distance, int[] parent) {
        System.out.println("=== Dijkstra's Shortest Path from Vertex " + start + " ===\n");
        System.out.printf("%-10s %-15s %-20s%n", "Vertex", "Distance", "Path");
        System.out.println("--------------------------------------------------");

        for (int v = 0; v < vertices; v++) {
            if (v == start) {
                System.out.printf("%-10d %-15d %-20s%n", v, 0, String.valueOf(start));
            } else if (distance[v] == Integer.MAX_VALUE) {
                System.out.printf("%-10d %-15s %-20s%n", v, "Unreachable", "N/A");
            } else {
                System.out.printf("%-10d %-15d %-20s%n", v, distance[v], getPath(parent, start, v));
            }
        }
    }


    private String getPath(int[] parent, int start, int target) {
        List<Integer> path = new ArrayList<>();
        int current = target;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        Collections.reverse(path);


        if (path.get(0) != start) return "N/A";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i < path.size() - 1) sb.append(" -> ");
        }
        return sb.toString();
    }
}


public class Dijkstra {
    public static void main(String[] args) {

        Graph graph = new Graph(6);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 5, 2);
        graph.addEdge(3, 5, 8);

        // Run Dijkstra from vertex 0
        graph.dijkstra(0);
    }
}

# Dijkstra's Algorithm – Shortest Path

## Student Information
- **Name:** [Your Name]
- **Course:** [Your Course Name]
- **Assignment:** Bonus Task – Dijkstra's Algorithm (Shortest Path)

---

## Description

This project implements **Dijkstra's Algorithm** in Java to find the shortest path from a starting vertex to all other vertices in a weighted graph. The implementation uses an adjacency list to store weighted edges and simple arrays with loops — no priority queue is used.

---

## Project Structure

```
Dijkstra.java       → Main file containing all classes and the algorithm
README.md           → This file
```

---

## Classes Overview

### `Edge`
Represents a weighted edge in the graph.
- `int destination` – the target vertex
- `int weight` – the cost/weight of the edge

### `Graph`
Represents the graph using an adjacency list of weighted edges.
- `addEdge(int source, int destination, int weight)` – adds a bidirectional weighted edge
- `dijkstra(int start)` – runs Dijkstra's algorithm from the given start vertex
- `getMinDistanceVertex(...)` – helper: finds the unvisited vertex with the smallest distance (uses a simple loop, no priority queue)
- `getPath(...)` – helper: reconstructs the shortest path from start to any vertex

### `Dijkstra` (Main)
Entry point of the program. Creates a sample graph, adds weighted edges, and runs the algorithm from vertex `0`.

---

## How to Compile and Run

### Requirements
- Java JDK 8 or higher

### Compile
```bash
javac Dijkstra.java
```

### Run
```bash
java Dijkstra
```

---

## Sample Output

```
=== Dijkstra's Shortest Path from Vertex 0 ===

Vertex     Distance        Path
--------------------------------------------------
0          0               0
1          3               0 -> 2 -> 1
2          1               0 -> 2
3          4               0 -> 2 -> 1 -> 3
4          7               0 -> 2 -> 1 -> 3 -> 4
5          9               0 -> 2 -> 1 -> 3 -> 4 -> 5
```

---

## Graph Used for Testing

The graph has **6 vertices (0–5)** with the following weighted edges:

| From | To | Weight |
|------|----|--------|
| 0    | 1  | 4      |
| 0    | 2  | 1      |
| 2    | 1  | 2      |
| 1    | 3  | 1      |
| 2    | 3  | 5      |
| 3    | 4  | 3      |
| 4    | 5  | 2      |
| 3    | 5  | 8      |

---

## Algorithm Explanation

1. Set the distance of the start vertex to `0` and all others to infinity (`Integer.MAX_VALUE`)
2. Repeat for all vertices:
   - Pick the unvisited vertex with the smallest known distance
   - Mark it as visited
   - For each of its neighbors, check if going through this vertex gives a shorter path (**edge relaxation**)
   - If yes, update the distance and record the parent vertex
3. After all vertices are processed, print the shortest distance and full path to every vertex

---

<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/3c79da38-d90e-4f3e-9d1f-31a8c7059697" />

---

## Notes

- The graph is **undirected** — each edge is added in both directions.
- If a vertex is unreachable from the start, the output shows `Unreachable` and `N/A` for the path.
- The path reconstruction is done using a `parent[]` array that tracks how each vertex was reached.

import java.util.*;

class FordFulkerson {
    static final int INF = Integer.MAX_VALUE;
    private int V;
    private int[][] capacity;
    private int[] parent;

    FordFulkerson(int V) {
        this.V = V;
        capacity = new int[V][V];
    }

    void addEdge(int u, int v, int cap) {
        capacity[u][v] = cap;
    }

    boolean bfs(int source, int sink) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent = new int[V];

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                if (!visited[v] && capacity[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                    if (v == sink) return true;
                }
            }
        }
        return false;
    }

    int maxFlow(int source, int sink) {
        int flow = 0;

        while (bfs(source, sink)) {
            int pathFlow = INF;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;
            }

            flow += pathFlow;
        }
        return flow;
    }

    public static void main(String[] args) {
        FordFulkerson g = new FordFulkerson(6);
        g.addEdge(0, 1, 16);
        g.addEdge(0, 2, 13);
        g.addEdge(1, 2, 10);
        g.addEdge(1, 3, 12);
        g.addEdge(2, 1, 4);
        g.addEdge(2, 4, 14);
        g.addEdge(3, 2, 9);
        g.addEdge(3, 5, 20);
        g.addEdge(4, 3, 7);
        g.addEdge(4, 5, 4);

        System.out.println("Maximum Flow: " + g.maxFlow(0, 5));
    }
}

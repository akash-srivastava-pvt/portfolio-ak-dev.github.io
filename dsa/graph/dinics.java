import java.util.*;

class Dinic {
    static final int INF = Integer.MAX_VALUE;
    private int V;
    private int[][] capacity;
    private int[] level, ptr;
    private List<List<Integer>> adj;

    Dinic(int V) {
        this.V = V;
        capacity = new int[V][V];
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v, int cap) {
        adj.get(u).add(v);
        adj.get(v).add(u);
        capacity[u][v] = cap;
    }

    boolean bfs(int source, int sink) {
        level = new int[V];
        Arrays.fill(level, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        level[source] = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (level[v] == -1 && capacity[u][v] > 0) {
                    level[v] = level[u] + 1;
                    queue.add(v);
                }
            }
        }
        return level[sink] != -1;
    }

    int dfs(int u, int sink, int flow) {
        if (u == sink) return flow;
        for (; ptr[u] < adj.get(u).size(); ptr[u]++) {
            int v = adj.get(u).get(ptr[u]);
            if (level[v] == level[u] + 1 && capacity[u][v] > 0) {
                int minFlow = dfs(v, sink, Math.min(flow, capacity[u][v]));
                if (minFlow > 0) {
                    capacity[u][v] -= minFlow;
                    capacity[v][u] += minFlow;
                    return minFlow;
                }
            }
        }
        return 0;
    }

    int maxFlow(int source, int sink) {
        int flow = 0;
        while (bfs(source, sink)) {
            ptr = new int[V];
            while (true) {
                int pushed = dfs(source, sink, INF);
                if (pushed == 0) break;
                flow += pushed;
            }
        }
        return flow;
    }

    public static void main(String[] args) {
        Dinic g = new Dinic(6);
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

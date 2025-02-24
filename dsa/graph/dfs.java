import java.util.*;

class DFS {
    private int V;
    private List<List<Integer>> adj;

    DFS(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    void addEdge(int v, int w) {
        adj.get(v).add(w);
    }

    void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int n : adj.get(v)) {
            if (!visited[n]) dfsUtil(n, visited);
        }
    }

    void DFS(int v) {
        boolean[] visited = new boolean[V];
        dfsUtil(v, visited);
    }

    public static void main(String[] args) {
        DFS g = new DFS(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Depth First Traversal starting from vertex 2:");
        g.DFS(2);
    }
}

import java.util.*;

class KosarajuSCC {
    private int V;
    private List<List<Integer>> adj, revAdj;
    private Stack<Integer> stack;
    private boolean[] visited;

    KosarajuSCC(int V) {
        this.V = V;
        adj = new ArrayList<>();
        revAdj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }
        stack = new Stack<>();
        visited = new boolean[V];
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
        revAdj.get(v).add(u);
    }

    void dfs1(int v) {
        visited[v] = true;
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) dfs1(neighbor);
        }
        stack.push(v);
    }

    void dfs2(int v) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int neighbor : revAdj.get(v)) {
            if (!visited[neighbor]) dfs2(neighbor);
        }
    }

    void findSCCs() {
        Arrays.fill(visited, false);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfs1(i);
        }

        Arrays.fill(visited, false);
        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                dfs2(v);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        KosarajuSCC g = new KosarajuSCC(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        g.findSCCs();
    }
}

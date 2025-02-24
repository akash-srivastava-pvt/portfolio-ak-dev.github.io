import java.util.*;

class TarjanSCC {
    private int V, time;
    private List<List<Integer>> adj;
    private int[] disc, low;
    private boolean[] inStack;
    private Stack<Integer> stack;

    TarjanSCC(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        disc = new int[V];
        low = new int[V];
        inStack = new boolean[V];
        stack = new Stack<>();
        time = 0;
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    void dfs(int v) {
        disc[v] = low[v] = ++time;
        stack.push(v);
        inStack[v] = true;

        for (int neighbor : adj.get(v)) {
            if (disc[neighbor] == 0) {
                dfs(neighbor);
                low[v] = Math.min(low[v], low[neighbor]);
            } else if (inStack[neighbor]) {
                low[v] = Math.min(low[v], disc[neighbor]);
            }
        }

        if (low[v] == disc[v]) {
            System.out.print("SCC: ");
            while (true) {
                int node = stack.pop();
                inStack[node] = false;
                System.out.print(node + " ");
                if (node == v) break;
            }
            System.out.println();
        }
    }

    void findSCCs() {
        Arrays.fill(disc, 0);
        for (int i = 0; i < V; i++) {
            if (disc[i] == 0) dfs(i);
        }
    }

    public static void main(String[] args) {
        TarjanSCC g = new TarjanSCC(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        g.findSCCs();
    }
}

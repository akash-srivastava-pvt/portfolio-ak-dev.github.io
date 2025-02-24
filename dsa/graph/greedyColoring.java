import java.util.*;

class GraphColoring {
    private int V;
    private List<List<Integer>> adj;

    public GraphColoring(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    void greedyColoring() {
        int[] result = new int[V];
        Arrays.fill(result, -1);

        result[0] = 0;
        boolean[] available = new boolean[V];

        for (int u = 1; u < V; u++) {
            Arrays.fill(available, true);

            for (int v : adj.get(u)) {
                if (result[v] != -1) available[result[v]] = false;
            }

            int color;
            for (color = 0; color < V; color++) {
                if (available[color]) break;
            }

            result[u] = color;
        }

        System.out.println("Vertex   Color");
        for (int i = 0; i < V; i++) System.out.println(i + "        " + result[i]);
    }

    public static void main(String[] args) {
        GraphColoring g = new GraphColoring(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        System.out.println("Graph Coloring using Greedy Algorithm:");
        g.greedyColoring();
    }
}

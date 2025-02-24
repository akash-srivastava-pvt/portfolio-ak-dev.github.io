import java.util.*;

class DFSTopologicalSort {
    private int V; // Number of vertices
    private List<List<Integer>> adj; // Adjacency list

    DFSTopologicalSort(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v); // Directed Graph
    }

    void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true; // Mark node as visited

        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor])
                topologicalSortUtil(neighbor, visited, stack);
        }

        stack.push(v); // Push to stack after visiting all dependencies
    }

    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        System.out.println("Topological Sorting Order:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        DFSTopologicalSort g = new DFSTopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Performing Topological Sort:");
        g.topologicalSort();
    }
}

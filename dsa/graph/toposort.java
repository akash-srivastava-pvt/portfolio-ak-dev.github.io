import java.util.*;

class TopologicalSort {
    static void topologicalSort(int V, List<List<Integer>> adj) {
        int[] inDegree = new int[V];
        for (List<Integer> neighbors : adj)
            for (int v : neighbors) inDegree[v]++;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++)
            if (inDegree[i] == 0) queue.add(i);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");

            for (int v : adj.get(u))
                if (--inDegree[v] == 0) queue.add(v);
        }
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = Arrays.asList(
            Arrays.asList(2, 3),
            Arrays.asList(3, 4),
            Arrays.asList(4),
            Arrays.asList(5),
            Arrays.asList(),
            Arrays.asList()
        );

        System.out.println("Topological Order:");
        topologicalSort(V, adj);
    }
}

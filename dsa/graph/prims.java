import java.util.*;

class Prims {
    static class Node implements Comparable<Node> {
        int vertex, weight;
        Node(int v, int w) {
            vertex = v;
            weight = w;
        }

        public int compareTo(Node other) {
            return this.weight - other.weight;
        }
    }

    static void primsMST(int V, List<List<Node>> adj) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        int[] key = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;
            visited[u] = true;

            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex, weight = neighbor.weight;
                if (!visited[v] && weight < key[v]) {
                    parent[v] = u;
                    key[v] = weight;
                    pq.add(new Node(v, key[v]));
                }
            }
        }

        System.out.println("Edge  Weight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "   " + key[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Node(1, 2));
        adj.get(0).add(new Node(3, 6));
        adj.get(1).add(new Node(2, 3));
        adj.get(1).add(new Node(3, 8));
        adj.get(1).add(new Node(4, 5));
        adj.get(2).add(new Node(4, 7));
        adj.get(3).add(new Node(4, 9));

        primsMST(V, adj);
    }
}

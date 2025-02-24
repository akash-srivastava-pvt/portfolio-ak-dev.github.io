import java.util.*;

class Dijkstra {
    static final int INF = Integer.MAX_VALUE;
    private int V;
    private List<List<Node>> adj;

    static class Node implements Comparable<Node> {
        int vertex, weight;
        Node(int v, int w) {
            vertex = v;
            weight = w;
        }
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }

    Dijkstra(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Node(v, w));
        adj.get(v).add(new Node(u, w));
    }

    void dijkstra(int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;
            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex, weight = neighbor.weight;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        System.out.println("Vertex Distance from Source:");
        for (int i = 0; i < V; i++) System.out.println(i + " -> " + dist[i]);
    }

    public static void main(String[] args) {
        Dijkstra g = new Dijkstra(5);
        g.addEdge(0, 1, 9);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(0, 4, 3);
        g.addEdge(2, 1, 2);
        g.addEdge(2, 3, 4);

        System.out.println("Dijkstra’s Shortest Path from Node 0:");
        g.dijkstra(0);
    }
}

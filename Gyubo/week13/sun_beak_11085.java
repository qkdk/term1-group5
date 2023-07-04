import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class sun_beak_11085 {

    static class Vertex implements Comparable<Vertex> {
        
        int node1;
        int node2;
        int cost;

        public Vertex(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return o.cost - this.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        PriorityQueue<Vertex> vertices = new PriorityQueue<>();
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            vertices.add(new Vertex(node1, node2, cost));
        }

        int[] parents = new int[p];
        for (int i = 0; i < p; i++) {
            parents[i] = i;
        }

        int count = 0;

        while (!vertices.isEmpty()) {
            if (count == p - 1) {
                break;
            }

            Vertex vertex = vertices.poll();
            if (union(parents, vertex.node1, vertex.node2)) {
                count++;
                if (findParent(parents, c) == findParent(parents, v)) {
                    System.out.println(vertex.cost);
                    return;
                }
            }
        }
    }

    private static boolean union(int[] parents, int node1, int node2) {
        int p1 = findParent(parents, node1);
        int p2 = findParent(parents, node2);

        if (p1 == p2) {
            return false;
        } else {
            if (p2 < p1) {
                parents[p1] = p2;
            } else {
                parents[p2] = p1;
            }
            return true;
        }
    }

    private static int findParent(int[] parents, int node) {
        if (parents[node] == node) {
            return node;
        } else {
            return parents[node] = findParent(parents, parents[node]);
        }
    }
}

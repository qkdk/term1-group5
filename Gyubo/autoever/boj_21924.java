import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_21924 {

    static class Vertex implements Comparable<Vertex> {

        int e;
        int cost;

        public Vertex(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "[e = " + this.e + ", cost = " + this.cost + "]";
        }

        @Override
        public int compareTo(Vertex o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<Vertex>> graph = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        long sum = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            sum += cost;
            graph.get(node1).add(new Vertex(node2, cost));
            graph.get(node2).add(new Vertex(node1, cost));
        }

        PriorityQueue<Vertex> q = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        q.addAll(graph.get(1));

        int count = 0;
        while (count < n - 1) {
            if (q.isEmpty()) {
                System.out.println(-1);
                return;
            }

            Vertex now = q.poll();

            if (visited[now.e]) {
                continue;
            }

            count++;
            sum -= now.cost;
            visited[now.e] = true;
            q.addAll(graph.get(now.e));
        }
        System.out.println(sum);
    }
}
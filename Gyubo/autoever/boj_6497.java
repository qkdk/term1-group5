import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_6497 {

    static class Vertex implements Comparable<Vertex> {

        int e;
        int cost;

        public Vertex(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Vertex [e = " + this.e + ", cost = " + this.cost + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }

            Map<Integer, List<Vertex>> graph = new HashMap<>();

            for (int i = 0; i < n; i++) {
                graph.put(i, new ArrayList<>());
            }

            int sum = 0;

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
            q.addAll(graph.get(0));

            int count = 0;
            boolean[] visited = new boolean[n];
            visited[0] = true;
            int answer = 0;

            while (!q.isEmpty()) {
                Vertex now = q.poll();

                if (visited[now.e]) {
                    continue;
                }

                visited[now.e] = true;

                answer += now.cost;
                count++;
                q.addAll(graph.get(now.e));
            }
            System.out.println(sum - answer);
        }
    }
}

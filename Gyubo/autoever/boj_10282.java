import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_10282 {

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
            return "Vertex{" +
                    "e=" + e +
                    ", cost=" + cost +
                    '}';
        }
    }

    private static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Map<Integer, List<Vertex>> graph = new HashMap<>();

            for (int i = 1; i < n + 1; i++) {
                graph.put(i, new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.get(node2).add(new Vertex(node1, cost));
            }

            PriorityQueue<Vertex> q = new PriorityQueue<>();
            q.add(new Vertex(c, 0));
            int[] dist = new int[n + 1];
            Arrays.fill(dist, INF);

            dist[c] = 0;

            while (!q.isEmpty()) {
                Vertex now = q.poll();

                List<Vertex> nexts = graph.get(now.e);
                for (Vertex next : nexts) {
                    if (dist[next.e] > dist[now.e] + next.cost) {
                        dist[next.e] = dist[now.e] + next.cost;
                        q.add(new Vertex(next.e, dist[next.e]));
                    }
                }
            }

            int count = 0;
            int maxTime = 0;
            for (int d : dist) {
                if (d != INF) {
                    count++;
                    maxTime = Math.max(maxTime, d);
                }
            }

            System.out.println(count + " " + maxTime);
        }

    }
}

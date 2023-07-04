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

public class boj_1939 {

    static class Vertex implements Comparable<Vertex> {

        int e;
        int p;
        int cost;

        @Override
        public int compareTo(Vertex o) {
            return o.cost - this.cost;
        }

        public Vertex(int e, int p, int cost) {
            this.e = e;
            this.p = p;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "e=" + e +
                    ", cost=" + cost +
                    '}';
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

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(node1).add(new Vertex(node2, node1, cost));
            graph.get(node2).add(new Vertex(node1, node2, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] parents = new int[n + 1];
        int[] dist = new int[n + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        PriorityQueue<Vertex> q = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        q.addAll(graph.get(start));
        visited[start] = true;

        int count = 0;
        while (!q.isEmpty() && count < n - 1) {
            Vertex cur = q.poll();

            if (visited[cur.e]) {
                continue;
            }

            dist[cur.e] = cur.cost;
            parents[cur.e] = cur.p;
            visited[cur.e] = true;
            count++;
            List<Vertex> nexts = graph.get(cur.e);
            q.addAll(nexts);
        }

        findParent(parents, dist, end, start, Integer.MAX_VALUE);
    }

    private static void findParent(int[] parents, int[] dist, int node, int target, int minCost){
        if (node == target) {
            System.out.println(minCost);
            return;
        }
        minCost = Math.min(minCost, dist[node]);
        findParent(parents, dist, parents[node], target, minCost);
    }

}

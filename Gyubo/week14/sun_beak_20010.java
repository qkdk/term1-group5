import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class sun_beak_20010 {

    static class Node implements Comparable<Node> {

        int node1;
        int node2;
        long cost;

        public Node(int node1, int node2, long cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static class Vertex {

        int e;
        long cost;

        public Vertex(int e, long cost) {
            this.e = e;
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

    private static int endNode;
    private static long endNodeCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> q = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            q.add(new Node(node1, node2, cost));

        }

        Map<Integer, List<Vertex>> mst = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mst.put(i, new ArrayList<>());
        }

        int sum = 0;
        int cnt = 0;
        int[] parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        while (!q.isEmpty() && cnt < n - 1) {
            Node poll = q.poll();
            // node1과 node2가 같은 부모인지 검사
            if (union(parents, poll.node1, poll.node2)) {
                sum += poll.cost;
                cnt++;
                mst.get(poll.node1).add(new Vertex(poll.node2, poll.cost));
                mst.get(poll.node2).add(new Vertex(poll.node1, poll.cost));
            }
        }

        endNode = Integer.MIN_VALUE;
        endNodeCost = Long.MIN_VALUE;
        boolean[] vistied = new boolean[n];
        vistied[0] = true;
        dfs(mst, vistied, 0, 0);

        endNodeCost = Long.MIN_VALUE;
        vistied = new boolean[n];
        vistied[endNode] = true;
        dfs(mst, vistied, 0, endNode);

        System.out.println(sum);
        System.out.println(endNodeCost);

    }

    private static void dfs(Map<Integer, List<Vertex>> mst, boolean[] visited, long cost, int node) {
        if (endNodeCost < cost) {
            endNodeCost = cost;
            endNode = node;
        }

        List<Vertex> nexts = mst.get(node);
        for (Vertex next : nexts) {
            if (visited[next.e]) {
                continue;
            }
            visited[next.e] = true;
            dfs(mst, visited, cost + next.cost, next.e);
        }
    }

    private static boolean union(int[] parents, int node1, int node2) {
        int p1 = findParents(parents, node1);
        int p2 = findParents(parents, node2);

        if (p1 == p2) {
            return false;
        }
        parents[p2] = p1;
        return true;
    }

    private static int findParents(int[] parents, int node) {
        if (parents[node] == node) {
            return node;
        } else {
            return parents[node] = findParents(parents, parents[node]);
        }
    }
}

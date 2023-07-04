import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_1260 {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        for (int i = 1; i < n + 1; i++) {
            Collections.sort(graph.get(i));
        }

        boolean[] visited = new boolean[n + 1];
        visited[v] = true;

        dfs(graph, v, visited);
        sb.append("\n");
        bfs(graph, v);

        System.out.println(sb.toString());
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int node, boolean[] visited) {
        // 종료조건
        sb.append(node).append(" ");

        List<Integer> nexts = graph.get(node);
        for (Integer next : nexts) {
            if (visited[next]) {
                continue;
            }

            visited[next] = true;
            dfs(graph, next, visited);
        }
    }

    private static void bfs(Map<Integer, List<Integer>> graph, int node) {
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.size() + 1];
        visited[node] = true;
        q.add(node);

        while (!q.isEmpty()) {
            Integer p = q.poll();
            sb.append(p).append(" ");

            List<Integer> nexts = graph.get(p);
            for (Integer next : nexts) {
                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                q.add(next);
            }
        }
    }

}

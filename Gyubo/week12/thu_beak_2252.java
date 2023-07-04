import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class thu_beak_2252 {

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] links = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            graph.get(node1).add(node2);
            links[node2]++;
        }

        topologySort(n, graph, links);
        System.out.println(sb.toString());
    }

    private static void topologySort(int n, Map<Integer, List<Integer>> graph, int[] links) {
        Deque<Integer> q = new ArrayDeque<>();
        // 차수가 0 인곳 찾기
        for (int i = 1; i < n + 1; i++) {
            if (links[i] == 0) {
                q.add(i);
            }
        }
        // q에서 뽑고 링크 제거
        for (int i = 1; i < n + 1; i++) {
            if (q.isEmpty()) {
                return;
            }

            Integer poll = q.poll();
            sb.append(poll);
            sb.append(" ");

            List<Integer> nexts = graph.get(poll);
            for (Integer next : nexts) {
                if (--links[next] == 0) {
                    q.add(next);
                }
            }
        }
    }
}

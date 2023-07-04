import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class thu_beak_3665 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < testCase + 1; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n + 1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] indegrees = new int[n + 1];
            Map<Integer, List<Integer>> graph = new HashMap<>();
            //초기값 세팅
            for (int i = 1; i < n + 1; i++) {
                graph.put(i, new ArrayList<>());
            }

            for (int i = 1; i < n + 1; i++) {
                int curNode = arr[i];
                for (int j = i + 1; j < n + 1; j++) {
                    graph.get(curNode).add(arr[j]);
                    indegrees[arr[j]]++;
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());

                int high = node2;
                int low = node1;
                // 노드 1이 더 높은 순위인 경우
                if (graph.get(node1).contains(Integer.valueOf(node2))) {
                    high = node1;
                    low = node2;
                }

                graph.get(low).add(high);
                indegrees[low]--;
                graph.get(high).remove(Integer.valueOf(low));
                indegrees[high]++;
            }

            topologySort(n, indegrees, graph);
        }
    }

    private static void topologySort(int n, int[] indegrees, Map<Integer, List<Integer>> graph) {
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            if (q.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }
            if (q.size() > 1) {
                System.out.println("?");
                return;
            }

            int p = q.poll();
            sb.append(p).append(" ");

            List<Integer> nexts = graph.get(p);
            for (Integer next : nexts) {
                if (--indegrees[next] == 0) {
                    q.add(next);
                }
            }
        }
        System.out.println(sb.toString());
    }
}

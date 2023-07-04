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

public class thu_beak_1516 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        int[] costs = new int[n + 1];
        // inputLinks
        int[] links = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());

            while (true) {
                int nextInt = Integer.parseInt(st.nextToken());
                if (nextInt == -1) {
                    break;
                }
                graph.get(nextInt).add(i);
                links[i]++;
            }
            costs[i] = cost;
        }

        topologySort(n, graph, costs, links);
    }

    private static void topologySort(int n, Map<Integer, List<Integer>> graph, int[] costs, int[] links) {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> q = new ArrayDeque<>();

        int[] c = new int[n + 1];

        for (int i = 1; i < links.length; i++) {
            if (links[i] == 0) {
                q.add(i);
                c[i] = costs[i];
            }
        }

        for (int i = 1; i <= n; i++) {
            if (q.isEmpty()) {
                return;
            }

            Integer p = q.poll();

            List<Integer> nexts = graph.get(p);

            for (Integer next : nexts) {
                c[next] = Math.max(c[next], costs[next] + c[p]);
                if (--links[next] == 0) {
                    q.add(next);
                }
            }
        }

        for (int i = 1; i < c.length; i++) {
            sb.append(c[i]).append("\n");
        }
        System.out.print(sb.toString());
    }
}

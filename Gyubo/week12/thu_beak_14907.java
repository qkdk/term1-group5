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

public class thu_beak_14907 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            graph.put(i, new ArrayList<>());
        }
        int[] links = new int[26];
        Arrays.fill(links, -1);

        int[] costs = new int[26];

        while (true) {
            String input = br.readLine();
            if (input == null) {
                break;
            }
            st = new StringTokenizer(input);
            int source = st.nextToken().charAt(0) - 'A';
            int cost = Integer.parseInt(st.nextToken());

            costs[source] = cost;
            links[source] = 0;

            if (st.hasMoreTokens()) {
                String node = st.nextToken();
                for (int i = 0; i < node.length(); i++) {
                    links[source]++;
                    graph.get(node.charAt(i) - 'A').add(source);
                }
            }
        }

        Deque<Integer> q = new ArrayDeque<>();
        int[] dp = new int[26];

        for (int i = 0; i < links.length; i++) {
            if (links[i] == 0) {
                q.add(i);
                dp[i] = costs[i];
            }
        }

        while (!q.isEmpty()) {
            Integer p = q.poll();

            List<Integer> nexts = graph.get(p);
            for (Integer next : nexts) {
                dp[next] = Math.max(dp[next], dp[p] + costs[next]);
                if (--links[next] == 0) {
                    q.add(next);
                }
            }
        }

        int asInt = Arrays.stream(dp).max().getAsInt();
        System.out.println(asInt);
    }
}

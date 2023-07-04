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

public class Commute {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph.get(node1).add(node2);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        // BFS
        boolean[] visitedS = new boolean[n + 1];
        boolean[] visitedD = new boolean[n + 1];
        Deque<Integer> q = new ArrayDeque<>();

        q.add(start);
        visitedS[start] = true;
        bfs(graph, dest, visitedS, q);

        q.clear();

        q.add(dest);
        visitedD[dest] = true;
        bfs(graph, start, visitedD, q);

//        System.out.println(Arrays.toString(visitedS));
//        System.out.println(Arrays.toString(visitedD));
        int count = 0;
        for (int i = 0; i < visitedS.length; i++) {
            if (visitedS[i] == true && visitedD[i] == true) {
                count++;
            }
        }
        System.out.println(count - 2);
    }

    private static void bfs(Map<Integer, List<Integer>> graph, int end, boolean[] visited, Deque<Integer> q) {
        while (!q.isEmpty()) {
            Integer now = q.poll();

            if (now == end) {
                continue;
            }

            List<Integer> nexts = graph.get(now);
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

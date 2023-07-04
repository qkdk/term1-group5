import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class thu_beak_1446 {

    static class Vertex{

        int start;
        int cost;

        public Vertex(int start, int cost) {
            this.start = start;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "start=" + start +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dist = new int[10001];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = i;
        }

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Map<Integer, List<Vertex>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.putIfAbsent(dest, new ArrayList<>());
            graph.get(dest).add(new Vertex(start, cost));
        }

        for (int i = 1; i < dist.length; i++) {
            // 지름길이 있는 경우
            if (graph.containsKey(i)) {
                for (Vertex vertex : graph.get(i)) {
                    dist[i] = Math.min(dist[vertex.start] + vertex.cost, dist[i]);
                }
            }
            dist[i] = Math.min(dist[i - 1] + 1, dist[i]);
        }
        System.out.println(dist[d]);
    }
}

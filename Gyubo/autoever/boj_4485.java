import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_4485 {

    static class Vertex implements Comparable<Vertex> {

        int y;
        int x;
        int cost;

        @Override
        public int compareTo(Vertex o) {
            return this.cost - o.cost;
        }

        public Vertex(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }

    private static final int[][] vector = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        while (true) {
            cnt++;
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }

            int[][] table = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = INF;
                }
            }

            dist[0][0] = table[0][0];

            PriorityQueue<Vertex> q = new PriorityQueue<>();
            q.add(new Vertex(0, 0, 0));

            while (!q.isEmpty()) {
                Vertex now = q.poll();

                for (int d = 0; d < vector.length; d++) {
                    int ny = now.y + vector[d][0];
                    int nx = now.x + vector[d][1];

                    if (ny < 0 || nx < 0 || ny >= table.length || nx >= table.length) {
                        continue;
                    }

                    if (dist[ny][nx] > dist[now.y][now.x] + table[ny][nx]) {
                        dist[ny][nx] = dist[now.y][now.x] + table[ny][nx];
                        q.add(new Vertex(ny, nx, dist[ny][nx]));
                    }
                }
            }
            sb.append("Problem ").append(cnt).append(": ").append(dist[n - 1][n - 1]).append("\n");

        }
        System.out.println(sb.toString());
    }
}

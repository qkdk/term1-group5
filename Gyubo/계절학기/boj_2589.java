import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_2589 {

    static class Point {

        int y;
        int x;
        int depth;

        public Point(int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "y=" + y +
                    ", x=" + x +
                    ", depth=" + depth +
                    '}';
        }
    }

    private static final int[][] vector = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] table = new char[n][m];
        for (int i = 0; i < n; i++) {
            String readLine = br.readLine();
            for (int j = 0; j < m; j++) {
                table[i][j] = readLine.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (table[i][j] == 'L') {
                    // L일때마다 bfs
                    answer = Math.max(bfs(n, m, table, i, j), answer);
                }
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int n, int m, char[][] table, int i, int j) {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(i, j, 0));
        boolean[][] visited = new boolean[n][m];
        visited[i][j] = true;

        int maxDepth = 0;
        while (!q.isEmpty()) {
            Point p = q.poll();

            maxDepth = Math.max(maxDepth, p.depth);

            for (int[] d : vector) {
                int ny = d[0] + p.y;
                int nx = d[1] + p.x;

                if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                    continue;
                }

                if (table[ny][nx] == 'W'){
                    continue;
                }

                if (visited[ny][nx]){
                    continue;
                }

                visited[ny][nx] = true;
                q.add(new Point(ny, nx, p.depth + 1));
            }
        }

        return maxDepth;
    }

    private static void print(char[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }
}

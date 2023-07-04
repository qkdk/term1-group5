import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class sat_codetree_bread {

    static class Point2 {

        int id;
        int y;
        int x;

        public Point2(int id, int y, int x) {
            this.id = id;
            this.y = y;
            this.x = x;
        }
    }

    static class Point implements Comparable<Point> {

        int y;
        int x;
        int depth;

        public Point(int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }

        @Override
        public int compareTo(Point o) {
            if (this.y == o.y) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
    }

    private static final int[][] vector = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private static int[][] table;
    private static boolean[][] visited;
    private static int[][] targets;
    private static boolean[] visitResult;
    private static boolean[][][] bfsVisited;
    private static int time = 1;
    private static int rs = 0;

    private static Deque<Point2> curQ = new ArrayDeque<>();
    private static Deque<Point2> nextQ = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        table = new int[n][n];
        visited = new boolean[n][n];
        targets = new int[m][2];
        visitResult = new boolean[m];
        bfsVisited = new boolean[m][n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            targets[i][0] = y;
            targets[i][1] = x;
            // y, x와 가장 가까운 지점 찾기
            Point baseCamp = findBaseCamp(n, y, x);

            // bfs를 돌리고(1회) 1초
            // 다음입력 받기
            curQ.add(new Point2(i, baseCamp.y, baseCamp.x));
            bfsVisited[i][baseCamp.y][baseCamp.x] = true;
            bfs();
        }

        // 완료될때까지 bfs()
        while (rs != m) {
            bfs();
        }

        System.out.println(time);
    }

    private static void bfs() {
        time++;
        while (!curQ.isEmpty()) {
            Point2 p = curQ.poll();
            if (visitResult[p.id]) {
                continue;
            }

            for (int d = 0; d < vector.length; d++) {
                int ny = p.y + vector[d][0];
                int nx = p.x + vector[d][1];

                if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                    continue;
                }
                if (visited[ny][nx]) {
                    continue;
                }
                if (bfsVisited[p.id][ny][nx]) {
                    continue;
                }

                bfsVisited[p.id][ny][nx] = true;
                if (ny == targets[p.id][0] && nx == targets[p.id][1]) {
                    visitResult[p.id] = true;
                    visited[ny][nx] = true;
                    rs++;
                    continue;
                }
                nextQ.add(new Point2(p.id, ny, nx));
            }
        }
        curQ = nextQ;
        nextQ = new ArrayDeque<>();
    }

    private static Point findBaseCamp(int n, int y, int x) {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(y, x, 0));
        boolean[][] visitedBaseCamp = new boolean[n][n];
        visitedBaseCamp[y][x] = true;
        List<Point> bfsAnswers = new ArrayList<>();
        int maxDepth = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (maxDepth <= p.depth) {
                continue;
            }

            for (int d = 0; d < vector.length; d++) {
                int ny = p.y + vector[d][0];
                int nx = p.x + vector[d][1];

                if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                    continue;
                }
                if (visitedBaseCamp[ny][nx]) {
                    continue;
                }
                if (visited[ny][nx]) {
                    continue;
                }

                visitedBaseCamp[ny][nx] = true;
                // 베이스 캠프를 찾았다.
                if (table[ny][nx] == 1) {
                    bfsAnswers.add(new Point(ny, nx, p.depth + 1));
                    maxDepth = p.depth + 1;
                    continue;
                }

                q.add(new Point(ny, nx, p.depth + 1));
            }
        }
        Point selBaseCamp = Collections.min(bfsAnswers);
        visited[selBaseCamp.y][selBaseCamp.x] = true;
        return selBaseCamp;
    }
}
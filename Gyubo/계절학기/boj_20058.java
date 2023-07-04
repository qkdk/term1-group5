import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_20058 {

    static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }

    private static final int[][] vector = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = (int) Math.pow(2, n);
        int q = Integer.parseInt(st.nextToken());

        int[][] table = new int[m][m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());

        for (int k = 0; k < q; k++) {
            rotateTable(st, table);
            table = deleteIce(table);
        }

        int iceCount = getIceCount(table);
        int maxIceSize = getMaxIceSize(table);

        System.out.println(iceCount);
        System.out.println(maxIceSize);
    }

    private static void print(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int getMaxIceSize(int[][] table) {
        boolean[][] visited = new boolean[table.length][table[0].length];
        int maxIceSize = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] != 0 && !visited[i][j]) {
                    maxIceSize = Math.max(maxIceSize, bfs(table, visited, i, j));
                }
            }
        }
        return maxIceSize;
    }

    private static int bfs(int[][] table, boolean[][] visited, int i, int j) {
        int sizeCount = 0;
        Deque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(i, j));
        visited[i][j] = true;
        while (!deque.isEmpty()) {
            Point p = deque.poll();
            sizeCount++;

            for (int[] d : vector) {
                int ny = p.y + d[0];
                int nx = p.x + d[1];

                if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                    continue;
                }

                if (table[ny][nx] == 0) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                deque.add(new Point(ny, nx));
            }
        }
        return sizeCount;
    }

    private static int getIceCount(int[][] table) {
        int iceCount = 0;
        for (int[] arr : table) {
            iceCount += Arrays.stream(arr).sum();
        }
        return iceCount;
    }

    private static int[][] deleteIce(int[][] table) {
        // 숫자 까는 로직
        int[][] tmpTable = new int[table.length][table[0].length];
        for (int i = 0; i < table.length; i++) {
            tmpTable[i] = Arrays.copyOf(table[i], table[i].length);
        }

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] == 0) {
                    continue;
                }

                int iceCount = 0;
                for (int[] d : vector) {
                    int ny = i + d[0];
                    int nx = j + d[1];

                    if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                        continue;
                    }

                    if (table[ny][nx] > 0) {
                        iceCount++;
                    }
                }
                if (iceCount < 3) {
                    tmpTable[i][j] = table[i][j] - 1;
                }
            }
        }

        return tmpTable;
    }

    private static void rotateTable(StringTokenizer st, int[][] table) {
        int level = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, level);
        for (int i = 0; i < table.length; i += size) {
            for (int j = 0; j < table[0].length; j += size) {
                rotate(table, i, j, size);
            }
        }
    }

    private static void rotate(int[][] table, int startY, int startX, int size) {
        int[][] tmpMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
//                size -1 - 하는 부분의 차이로 시계방향 반시계방향 결정
//                tmpMatrix[i][j] = table[startY + j][startX + size - 1 - i];
                tmpMatrix[i][j] = table[startY + size - 1 - j][startX + i];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i + startY][j + startX] = tmpMatrix[i][j];
            }
        }
    }
}

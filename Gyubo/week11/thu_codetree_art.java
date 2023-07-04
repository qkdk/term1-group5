import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class thu_codetree_art {

    static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static final int[][] vector = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] table = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int score = 0;
        int[][] labelMap = new int[n][n];
        int label = labeling(n, table, labelMap);
        score += calculateScore(table, labelMap, label);

        for (int i = 0; i < 3; i++) {
            int div = n / 2;
            rotateSquare(table, div, 0, 0);
            rotateSquare(table, div, div + 1, 0);
            rotateSquare(table, div, 0, div + 1);
            rotateSquare(table, div, div + 1, div + 1);
            rotateCross(n, table, div);

            labelMap = new int[n][n];
            label = labeling(n, table, labelMap);
            score += calculateScore(table, labelMap, label);
        }

        System.out.println(score);
    }

    private static void rotateCross(int n, int[][] table, int div) {
        int[][] rotate = new int[n][n];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                rotate[n - 1 - j][i] = table[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            table[i][div] = rotate[i][div];
            table[div][i] = rotate[div][i];
        }
    }

    private static void rotateSquare(int[][] table, int div, int y, int x) {
        int[][] subSquare = makeSubSquare(table, y, x, div);
        subSquare = rotate(subSquare);
        for (int i = 0; i < div; i++) {
            for (int j = 0; j < div; j++) {
                table[y + i][x + j] = subSquare[i][j];
            }
        }
    }

    private static int[][] rotate(int[][] table) {
        int n = table.length;

        int[][] rotate = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotate[i][j] = table[n - 1 - j][i];
            }
        }
        return rotate;
    }

    private static int[][] makeSubSquare(int[][] table, int y, int x, int length) {
        int[][] subSquare = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                subSquare[i][j] = table[i + y][j + x];
            }
        }

        return subSquare;
    }

    private static int calculateScore(int[][] table, int[][] labelMap, int label) {
        int sumScore = 0;

        int[][] closeTable = new int[label][label];
        int[] valueTable = new int[label];
        int[] countTable = new int[label];

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table.length; x++) {
                countTable[labelMap[y][x]]++;
                valueTable[labelMap[y][x]] = table[y][x];
                for (int d = 0; d < vector.length; d++) {
                    int ny = y + vector[d][0];
                    int nx = x + vector[d][1];

                    if (ny < 0 || nx < 0 || ny >= table.length || nx >= table.length) {
                        continue;
                    }

                    if (labelMap[y][x] != labelMap[ny][nx]) {
                        closeTable[labelMap[y][x]][labelMap[ny][nx]]++;
                    }
                }
            }
        }

        for (int i = 1; i < label; i++) {
            for (int j = i + 1; j < label; j++) {
                sumScore += (countTable[i] + countTable[j]) * valueTable[i] * valueTable[j] * closeTable[i][j];
            }
        }

        return sumScore;
    }

    private static int labeling(int n, int[][] table, int[][] labelMap) {
        int label = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (labelMap[i][j] == 0) {
                    lableBfs(labelMap, table, label, i, j);
                    label++;
                }
            }
        }
        return label;
    }

    private static void lableBfs(int[][] labelMap, int[][] table, int label, int i, int j) {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(i, j));
        labelMap[i][j] = label;
        int targetValue = table[i][j];

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int d = 0; d < vector.length; d++) {
                int ny = p.y + vector[d][0];
                int nx = p.x + vector[d][1];

                if (ny < 0 || nx < 0 || ny >= labelMap.length || nx >= labelMap[0].length) {
                    continue;
                }
                if (labelMap[ny][nx] != 0) {
                    continue;
                }
                if (table[ny][nx] != targetValue) {
                    continue;
                }

                labelMap[ny][nx] = label;
                q.add(new Point(ny, nx));
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1799 {

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(matrix, 0, -1, 0);
        System.out.println(answer);
    }

    private static void dfs(int[][] matrix, int y, int x, int count) {
        while (y < matrix.length) {
            x++;
            if (x == matrix[0].length) {
                y++;
                x = 0;
                if (y == matrix.length) {
                    answer = Math.max(answer, count);
                    return;
                }
            }

            if (matrix[y][x] == 0) {
                continue;
            }

            if (!check(matrix, y, x)) {
                continue;
            }

            matrix[y][x] += 1;
            dfs(matrix, y, x, count + 1);
            matrix[y][x] -= 1;
        }
    }

    private static boolean check(int[][] matrix, int y, int x) {
        // 왼쪽 위
        int len = Math.min(y, x) + 1;
        for (int i = 1; i < len; i++) {
            if (matrix[y - i][x - i] > 1) {
                return false;
            }
        }
        // 왼쪽 아레
        len = Math.min(matrix.length - 1 - y, x) + 1;
        for (int i = 1; i < len; i++) {
            if (matrix[y + i][x - i] > 1) {
                return false;
            }
        }
        // 오른쪽 위
        len = Math.min(y, matrix[0].length - 1 - x) + 1;
        for (int i = 1; i < len; i++) {
            if (matrix[y - i][x + i] > 1) {
                return false;
            }
        }
        // 오른쪽 아레
        len = Math.min(matrix.length - 1 - y, matrix.length - 1 - x);
        for (int i = 1; i < len; i++) {
            if (matrix[y + i][x + i] > 1) {
                return false;
            }
        }

        return true;
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}

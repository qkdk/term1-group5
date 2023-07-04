import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class tue_beak_1719 {

    private static final int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][n + 1];
        int[][] answer = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = INF;
                if (i == j) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dp[node1][node2] = cost;
            dp[node2][node1] = cost;

            answer[node1][node2] = node2;
            answer[node2][node1] = node1;
        }

        for (int k = 1; k < n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                        answer[i][j] = answer[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < answer.length; i++) {
            for (int j = 1; j < answer.length; j++) {
                if (i == j) {
                    sb.append("-");
                } else {
                    sb.append(answer[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

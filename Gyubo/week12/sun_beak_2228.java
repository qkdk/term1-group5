import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sun_beak_2228 {

    private static final int MINF = Integer.MIN_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i < n + 1; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }

        int[][] dp = new int[n + 1][m];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], MINF);
        }

        System.out.println(dfs(1, m - 1, dp, arr));
    }


    private static int dfs(int startIndex, int depth, int[][] dp, int[] arr) {
        if (depth == -1) {
            return 0;
        }
        if (startIndex >= arr.length) {
            return MINF;
        }

        if (dp[startIndex][depth] != MINF) {
            return dp[startIndex][depth];
        }

        dp[startIndex][depth] = Math.max(dfs(startIndex + 1, depth, dp, arr), dp[startIndex][depth]);
        // dfs를 돌린다.
        for (int i = startIndex; i < arr.length; i++) {
            int curVal = arr[i] - arr[startIndex - 1];
            dp[startIndex][depth] = Math.max(dp[startIndex][depth], curVal + dfs(i + 2, depth - 1, dp, arr));
        }

        return dp[startIndex][depth];
    }
}

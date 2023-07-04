import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sat_beak_10942 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());

        // 0 팰른드롬 블가능
        // 1 팰린드롬
        // 2 방문안함
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], 2);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(checkPalindrome(dp, arr, start, end)).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int checkPalindrome(int[][] dp, int[] arr, int start, int end) {
        if (end - start == 1){
            return dp[start][end] = arr[start] == arr[end] ? 1 : 0;
        }
        if (end == start) {
            return dp[start][end] = 1;
        }

        if (dp[start][end] == 1) {
            return 1;
        } else if (dp[start][end] == 0) {
            return 0;
        } else {
            int tmp = 0;
            if (arr[start] == arr[end]) {
                tmp = 1;
            }
            return dp[start][end] = tmp * checkPalindrome(dp, arr, start + 1, end - 1);
        }
    }
}

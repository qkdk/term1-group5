import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sat_beak_1695 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[n][n];
        System.out.println(palindrome(arr, dp, 0, n - 1));
    }

    private static int palindrome(int[] arr, int[][] dp, int start, int end) {
        // 종료조건
        if (dp[start][end] > 0) {
            return dp[start][end];
        }
        if (start == end) {
            return dp[start][end] = 0;
        }
//        if (end - start == 1) {
//            if (arr[start][end])
//            else {
//                return dp[start][end] = 1;
//            }
//        }

        // 좌 끝과 우 끝이 같은 경우는 그냥 depth를 올리지 않고 진행
        if (arr[start] == arr[end]) {
            dp[start][end] = palindrome(arr, dp, start + 1, end - 1);
        }
        // 아닌경우 오른쪽 왼쪽 나누어서 진행
        else {
            dp[start][end] = Math.min(palindrome(arr, dp, start + 1, end), palindrome(arr, dp, start, end - 1)) + 1;
        }
        return dp[start][end];
    }
}

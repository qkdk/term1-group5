import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 다리놓기
public class thu_beak_1010 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            int[][] dp = new int[right + 1][right + 1];
            for (int i = 0; i <= right; i++) {
                for (int j = 0; j <= Math.min(i, left); j++) {
                    if (j == 0 || i == j) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    }
                }
            }
            System.out.println(dp[right][left]);
//			int[][] dp = new int[left + 1][right + 1];
//			solve(left, right, 0, 1, dp);
//			System.out.println(dp[1][0]);
        }
    }

//	private static int solve(int left, int right, int parentIdx, int idx, int[][] dp) {
//		if (idx == left + 1) {
//			return 1;
//		}
//
//		if (dp[idx][parentIdx] != 0) {
//			return dp[idx][parentIdx];
//		}
//
//		for (int i = parentIdx + 1; i < right + 1; i++) {
//			dp[idx][parentIdx] += solve(left, right, i, idx + 1, dp);
//		}
//		return dp[idx][parentIdx];
//	}
}

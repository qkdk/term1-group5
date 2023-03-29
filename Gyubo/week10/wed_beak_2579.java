import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 계단오르기
public class wed_beak_2579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] stair = new int[n + 1];
		int[] dp = new int[n + 1];
		dp[n] = stair[n];
		for (int i = 1; i <= n; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}

		solve(dp, stair, n);
		System.out.println(dp[n]);
	}

	private static int solve(int[] dp, int[] stair, int depth) {
		// TODO Auto-generated method stub
		if (depth < 1) {
			return 0;
		}
		if (dp[depth] != 0) {
			return dp[depth];
		}

		int r1 = stair[depth - 1] + solve(dp, stair, depth - 3);
		int r2 = solve(dp, stair, depth - 2);
		return dp[depth] = Math.max(r1, r2) + stair[depth];
	}
}

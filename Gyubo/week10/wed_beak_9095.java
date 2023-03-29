import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1,2,3 더하기
public class wed_beak_9095 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());

		for (int t = 0; t < testCase; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] dp = new int[12];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;

			for (int i = 4; i < n + 1; i++) {
				dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
			}
			System.out.println(dp[n]);
		}

	}
}

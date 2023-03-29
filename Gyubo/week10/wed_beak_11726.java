import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2 x n 타일링
public class wed_beak_11726 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		if (n == 1) {
			System.out.println(1);
			return;
		}

		int[] dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i < n + 1; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}
		System.out.println(dp[n] % 10007);
	}
}

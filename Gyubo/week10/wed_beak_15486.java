import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 퇴사
public class wed_beak_15486 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] income = new int[n + 1];
		int[] cost = new int[n + 1];
		int[] dp = new int[n + 2];
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			income[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < n + 1; i++) {
			dp[i] = Math.max(dp[i], dp[i - 1]);
			int curCost = cost[i];
			int curIncome = income[i] + dp[i];
			int nextDay = i + curCost;

			if (nextDay > n + 1) {
				continue;
			}
			dp[nextDay] = Math.max(curIncome, dp[nextDay]);
		}

		int answer = Arrays.stream(dp).max().getAsInt();
		System.out.println(answer);
	}
}

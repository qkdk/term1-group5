import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 내리막길
public class thu_beak_1520 {

	private static final int[][] vector = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] table = new int[n][m];
		int[][] dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(table, dp, 0, 0);
		if (dp[0][0] < 0) {
			System.out.println(0);
		} else {
			System.out.println(dp[0][0]);
		}
	}

	private static int dfs(int[][] table, int[][] dp, int y, int x) {
		if (y == table.length - 1 && x == table[0].length - 1) {
			return 1;
		}

		int count = 0;
		for (int d = 0; d < vector.length; d++) {
			int ny = y + vector[d][0];
			int nx = x + vector[d][1];

			if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
				continue;
			}

			if (table[ny][nx] >= table[y][x]) {
				continue;
			}

			if (dp[ny][nx] == -1) {
				continue;
			}

			if (dp[ny][nx] > 0) {
				count += dp[ny][nx];
				continue;
			}

			count += dfs(table, dp, ny, nx);
		}
		if (count == 0) {
			dp[y][x] = -1;
		} else {
			dp[y][x] = count;
		}
		return count;
	}
}

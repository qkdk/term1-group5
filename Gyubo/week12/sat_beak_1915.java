import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sat_beak_1915 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[n][m];
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            String readLine = br.readLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Character.getNumericValue(readLine.charAt(j));
                dp[i][j] = Character.getNumericValue(readLine.charAt(j));
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++){
            answer = Math.max(Arrays.stream(dp[i]).max().getAsInt(), answer);
        }
        System.out.println(answer * answer);
    }
}

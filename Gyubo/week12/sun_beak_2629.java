import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class sun_beak_2629 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] marbles = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }

        HashSet<Integer>[] dp = new HashSet[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashSet();
        }

        dfs(dp, weights, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++){
            if (dp[n - 1].contains(marbles[i])){
                sb.append("Y");
            }
            else{
                sb.append("N");
            }
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(HashSet<Integer>[] dp, int[] weights, int depth, int value) {
        if (depth == weights.length) {
            return;
        }

        if (!dp[depth].contains(value)) {
            dp[depth].add(value);
            dfs(dp, weights, depth + 1, value);
        }

        if (!dp[depth].contains(value + weights[depth])) {
            dp[depth].add(value + weights[depth]);
            dfs(dp, weights, depth + 1, value + weights[depth]);
        }

        if (!dp[depth].contains(value - weights[depth])) {
            dp[depth].add(value - weights[depth]);
            dfs(dp, weights, depth + 1, value - weights[depth]);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sat_beak_21758 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(st.nextToken());
            arr[i] = input;
            max = Math.max(max, input);
        }

        int[] dp1 = new int[n];
        dp1[0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp1[i] = dp1[i - 1] + arr[i];
        }

        int[] dp2 = new int[n];
        dp2[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp2[i] = dp2[i + 1] + arr[i];
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            answer = Math.max(answer, dp2[i] - arr[i] * 2 + dp2[0] - arr[0]);
        }
        for (int i = n - 2; i >= 0; i--) {
            answer = Math.max(answer, dp1[i] - arr[i] * 2 + dp1[n - 1] - arr[n - 1]);
        }

        answer = Math.max(answer, dp1[n - 1] - arr[0] - arr[n - 1] + max);

        System.out.println(answer);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class wed_beak_14002 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.println(Arrays.toString(dp));
        int m = Arrays.stream(dp).max().getAsInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = m; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (dp[j] == i) {
                    stack.add(arr[j]);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(m);
        sb.append("\n");
        while (!stack.isEmpty()){
            sb.append(stack.pop());
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}

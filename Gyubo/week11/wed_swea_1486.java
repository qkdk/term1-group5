import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 장훈이의 높은 선반
public class wed_swea_1486 {

    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 1; t < testCase + 1; t++) {
            answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            powerSet(arr, new boolean[n], 0, b);
            System.out.println("#" + t + " " + answer);
        }
    }

    private static void powerSet(int[] arr, boolean[] visited, int depth, int b) {
        if (depth == arr.length) {
            int sum = 0;
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    sum += arr[i];
                }
            }
            if (sum >= b) {
                answer = Math.min(answer, sum - b);
            }
            return;
        }

        visited[depth] = false;
        powerSet(arr, visited, depth + 1, b);

        visited[depth] = true;
        powerSet(arr, visited, depth + 1, b);
    }
}

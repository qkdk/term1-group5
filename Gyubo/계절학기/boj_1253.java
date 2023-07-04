import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1253 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 같은수가 올 수 있다.
        Arrays.sort(arr);
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int target = arr[i];
            int left = 0;
            int right = n - 1;

            while (left < right) {
                if (left == i){
                    left++;
                    continue;
                }
                if (right == i){
                    right--;
                    continue;
                }

                int sum = arr[left] + arr[right];

                if (sum == target) {
                    answer++;
                    break;
                }
                else if (sum < target) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}

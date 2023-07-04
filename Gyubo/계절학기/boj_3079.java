import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_3079 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long left = 0;
        long right = (long) Arrays.stream(arr).min().getAsInt() * m + 1;

        while (left < right) {
            long mid = (right + left) / 2;

            long count = 0;
            for (int time : arr) {
                count += mid / time;
            }

            // 오른쪽으로 탐색범위
            if (count < m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }
}

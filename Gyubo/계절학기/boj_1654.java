import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1654 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long left = 0;
        long right = (long) Arrays.stream(arr).max().getAsInt() + 1;

        while (left < right) {
            long mid = (right + left) / 2;

            int count = 0;
            for (int len : arr) {
                count += len / mid;
            }

            // 더 길게 자를 필요가 있는 경우
            if (count >= n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left - 1);
    }
}

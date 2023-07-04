import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1561 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long left = 0;
        long right = (long) Arrays.stream(arr).min().getAsInt() * n + 1;
        long count = 0;

        // 완료된 시점에서 n-1인경우 찾기
        while (left < right) {
            long mid = (left + right) / 2;
            count = arr.length;
            for (int el : arr) {
                count += mid / el;
            }

            if (count < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 모든 사람이 놀이기구에 타려면 최소한 left 의 시간 만큼이 필요하다.
        // left -1 값부터 시간을 줄여가면서 n-1이 되는 경우 계산
        count = 0;
        for (int el : arr) {
            count += Math.ceil(((double) left / el));
        }

        for (long i = left - 1; i >= 0; i--) {
            for (int j = arr.length - 1; j >= 0; j--) {
                if (i % arr[j] == 0) {
                    count--;
                }
                if (count == n - 1) {
                    System.out.println(j + 1);
                    return;
                }
            }
        }
    }
}

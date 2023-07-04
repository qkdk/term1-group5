import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_21921 {

    // 누적합으로 합 계산하기
    // max값이 갱싱될때 max값 count도 초기화
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];
        }

        int max = 0;
        int count = 0;
        for (int i = x; i < n + 1; i++) {
            int sum = arr[i] - arr[i - x];
            if (max < sum) {
                max = sum;
                count = 1;
            }
            else if (max == sum) {
                count++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(count);
        }
    }
}

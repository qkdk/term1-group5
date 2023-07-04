import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2559 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arrSum = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        arrSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            arrSum[i] = arrSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int maxValue = Integer.MIN_VALUE;
        for (int i = m; i < n + 1; i++) {
            maxValue = Math.max(arrSum[i] - arrSum[i - m], maxValue);
        }
        System.out.println(maxValue);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2470 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;

        int min = Integer.MAX_VALUE;
        int liquid1 = 0;
        int liquid2 = 0;
        while (left < right) {
            int sum = arr[left] + arr[right];

            int flag = Math.abs(sum);
            if (min > flag) {
                min = flag;
                liquid1 = arr[left];
                liquid2 = arr[right];
            }

            if (sum <= 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(liquid1 + " " + liquid2);
    }
}

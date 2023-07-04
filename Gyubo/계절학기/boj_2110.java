import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2110 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int start = 1;
        int end = arr[arr.length - 1] - arr[0];

        while (start < end) {
            int mid = (start + end) / 2;

            int installCount = 1;
            // 거리에 걸맞는 위치를 찾는 로직
            int dist = mid;
            int curValue = arr[0];

            for (int i = 0; i < n; i++) {
                if (arr[i] - dist > curValue){
                    installCount++;
                    curValue = arr[i];
                }
            }

            // 거리를 줄여야 하는경우
            if (installCount < c){
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }
}

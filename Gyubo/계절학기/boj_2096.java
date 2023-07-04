import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] arrMin = new int[n][3];
        for (int i = 0; i < n; i++) {
            arrMin[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        for (int i = 1; i < n; i++) {
            int max = Math.max(arr[i - 1][0], arr[i - 1][1]);
            arr[i][0] += max;

            max = Math.max(max, arr[i - 1][2]);
            arr[i][1] += max;

            max = Math.max(arr[i - 1][1], arr[i - 1][2]);
            arr[i][2] += max;
        }

        for (int i = 1; i < n; i++) {
            int min = Math.min(arrMin[i - 1][0], arrMin[i - 1][1]);
            arrMin[i][0] += min;

            min = Math.min(min, arrMin[i - 1][2]);
            arrMin[i][1] += min;

            min = Math.min(arrMin[i - 1][1], arrMin[i - 1][2]);
            arrMin[i][2] += min;
        }

        int max = Arrays.stream(arr[n - 1]).max().getAsInt();
        int min = Arrays.stream(arrMin[n - 1]).min().getAsInt();

        System.out.println(max + " " + min);
    }
}

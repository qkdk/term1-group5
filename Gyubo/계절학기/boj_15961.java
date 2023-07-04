import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15961 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + k];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = n; i < arr.length; i++) {
            arr[i] = arr[i - n];
        }

        int[] visited = new int[d + 1];
        int max;
        int count = 0;
        for (int i = 0; i < k; i++) {
            if (visited[arr[i]] == 0) {
                count++;
            }
            visited[arr[i]]++;
        }
        max = count;

        for (int i = 0; i < n; i++) {
            visited[arr[i]]--;
            if (visited[arr[i]] == 0) {
                count--;
            }

            visited[arr[i + k]]++;
            if (visited[arr[i + k]] == 1) {
                count++;
            }

            int aCount = count;
            if (visited[c] == 0){
                aCount++;
            }

            max = Math.max(aCount, max);
        }
        System.out.println(max);
    }
}

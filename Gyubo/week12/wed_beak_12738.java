import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class wed_beak_12738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        List<Long> dp = new ArrayList<>();
        dp.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (dp.get(dp.size() - 1) < arr[i]) {
                dp.add(arr[i]);
            } else {
                int index = Collections.binarySearch(dp, arr[i]);
                if (index < 0){
                    index = -index - 1;
                }
                dp.set(index, arr[i]);
            }
        }
        System.out.println(dp.size());
    }
}

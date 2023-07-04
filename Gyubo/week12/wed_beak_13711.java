import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// LCS4
public class wed_beak_13711 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            map.put(Integer.parseInt(st.nextToken()), i);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = map.get(Integer.parseInt(st.nextToken()));
        }

        List<Integer> dp = new ArrayList<>();
        dp.add(arr[0]);

        for (int i = 1; i < n; i++) {
            if (dp.get(dp.size() - 1) < arr[i]) {
                dp.add(arr[i]);
            } else {
                int index = Collections.binarySearch(dp, arr[i]);
                if (index < 0) {
                    index = -index - 1;
                }
                dp.set(index, arr[i]);
            }
        }
        System.out.println(dp.size());
    }
}

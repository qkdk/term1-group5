import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_9375 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String item = st.nextToken();
                String type = st.nextToken();

                map.putIfAbsent(type, 1);
                map.compute(type, (key, value) -> value + 1);
            }

            int result = 1;
            for (Integer value : map.values()) {
                result *= value;
            }
            System.out.println(result - 1);
        }
    }
}

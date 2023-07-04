import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class tue_beak_10815 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int c = Integer.parseInt(st.nextToken());
            int r = Arrays.binarySearch(cards, c);
            if (r < 0) {
                sb.append(0);
            }
            else {
                sb.append(1);
            }
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}

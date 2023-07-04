import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 파일 합치기 3
public class mon_beak_13975 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> q = new PriorityQueue<>();
            for (int i = 0; i < n; i++){
                q.add(Long.parseLong(st.nextToken()));
            }
            long sum = 0;
            while (q.size() > 2) {
                Long p1 = q.poll();
                Long p2 = q.poll();
                long next = p1 + p2;
                sum += next;
                q.add(next);
            }

            System.out.println(sum + q.poll() + q.poll());
        }
    }
}

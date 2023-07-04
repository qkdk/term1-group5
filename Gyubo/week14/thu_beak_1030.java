import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class thu_beak_1030 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R1 = Integer.parseInt(st.nextToken());
        int R2 = Integer.parseInt(st.nextToken());
        int C1 = Integer.parseInt(st.nextToken());
        int C2 = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = R1; i <= R2; i++) {
            for (int j = C1; j <= C2; j++) {
                sb.append(recursive(s, i, j, N, K, 0, 0));
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int recursive(int s, int i, int j, int n, int k, int startY, int startX) {
        if (s == 0) {
            return 0;
        }

        int length = (int) Math.pow(n, s);
        int offset = (int) Math.pow(n, s - 1);
        int whiteArea = (n - k) / 2;

        if (i >= whiteArea * offset + startY && i < length - whiteArea * offset + startY) {
            if (j >= whiteArea * offset + startX && j < length - whiteArea * offset + startX) {
                return 1;
            }
        }

        int result = 0;
        L:for (int a = 0; a < n; a++) {
            int sy = a * offset + startY;
            int ey = sy + offset;

            if (i >= sy && i < ey) {
                for (int b = 0; b < n; b++) {
                    int sx = b * offset + startX;
                    int ex = sx + offset;

                    if (j >= sx && j < ex) {
                        result = recursive(s - 1, i, j, n, k, sy, sx);
                        break L;
                    }
                }
            }
        }
        return result;
    }
}

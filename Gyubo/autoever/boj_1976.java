import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1976 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] parents = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parents[i] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) {
                int flag = Integer.parseInt(st.nextToken());
                if (flag == 1) {
                    // i와 j는 같은 그룹이다.
                    union(i, j, parents);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int prevValue = findParent(Integer.parseInt(st.nextToken()), parents);
        for (int i = 0; i < m - 1; i++) {
            int curValue = findParent(Integer.parseInt(st.nextToken()), parents);
            if (prevValue != curValue){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static void union(int cityA, int cityB, int[] parents) {
        int parentA = findParent(cityA, parents);
        int parentB = findParent(cityB, parents);

        parents[parentB] = parentA;
    }

    private static int findParent(int city, int[] parents) {
        if (parents[city] == city) {
            return city;
        }
        return parents[city] = findParent(parents[city], parents);
    }
}

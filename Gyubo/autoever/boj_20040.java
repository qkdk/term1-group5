import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20040 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            if (union(node1, node2, parents)) {
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println(0);

    }

    private static boolean union(int node1, int node2, int[] parents) {
        int p1 = findParent(node1, parents);
        int p2 = findParent(node2, parents);

        // 두개의 부모가 같다면 사이클이 생긴것
        if (p1 == p2) {
            return true;
        } else {
            parents[p2] = p1;
            return false;
        }
    }

    private static int findParent(int node, int[] parents) {
        if (parents[node] == node) {
            return node;
        }
        return parents[node] = findParent(parents[node], parents);
    }
}

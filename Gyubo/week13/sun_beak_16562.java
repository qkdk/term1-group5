import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 친구비
public class sun_beak_16562 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] cost = new int[n + 1];
        int[] parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            union(node1, node2, parents, cost);
        }

        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (parents[i] == i){
                answer += cost[i];
            }
        }

        if (answer <= k) {
            System.out.println(answer);
        }
        else{
            System.out.println("Oh no");
        }

    }

    private static void union(int node1, int node2, int[] parents, int[] cost) {
        int p1 = findParent(node1, parents);
        int p2 = findParent(node2, parents);

        // 값이 작은게 부모로
        if (cost[p1] > cost[p2]) {
            parents[p1] = p2;
        } else {
            parents[p2] = p1;
        }
    }

    private static int findParent(int node, int[] parents) {
        if (parents[node] == node) {
            return node;
        } else {
            return parents[node] = findParent(parents[node], parents);
        }
    }
}

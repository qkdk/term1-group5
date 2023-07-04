import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_2143 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr1 = new int[n + 1];
        for (int i = 1; i < arr1.length; i++) {
            arr1[i] = Integer.parseInt(st.nextToken()) + arr1[i - 1];
        }

        int m = Integer.parseInt(br.readLine());
        int[] arr2 = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < arr2.length; i++) {
            arr2[i] = Integer.parseInt(st.nextToken()) + arr2[i - 1];
        }

        List<Integer> arr1Set = new ArrayList<>();
        List<Integer> arr2Set = new ArrayList<>();
        makeSet(arr1, arr1Set);
        makeSet(arr2, arr2Set);

        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : arr2Set) {
            map.putIfAbsent(integer, 0);
            map.compute(integer, (key, value) -> value + 1);
        }
        long count = 0;

        for (Integer integer : arr1Set) {
            int target = T - integer;
            count += map.getOrDefault(target, 0);
        }

        System.out.println(count);
    }

    private static void makeSet(int[] arr, List<Integer> arrSet) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                arrSet.add(arr[j] - arr[i]);
            }
        }
    }
}

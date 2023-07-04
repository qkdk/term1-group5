import java.util.Arrays;

public class combis {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

//        combinations(arr, new int[3], 0, 0);
//        combinationsre(arr, new int[3], 0, 0);
//        permutations(arr, new int[3], 0, new boolean[arr.length]);
        permutationsRe(arr, new int[3], 0);

    }

    private static void combinations(int[] arr, int[] result, int index, int depth) {
        if (result.length == depth) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            result[depth] = arr[i];
            combinations(arr, result, i + 1, depth + 1);
        }
    }

    private static void combinationsre(int[] arr, int[] result, int depth, int index) {
        if (result.length == depth) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            result[depth] = arr[i];
            combinationsre(arr, result, depth + 1, i);
        }
    }

    private static void permutations(int[] arr, int[] result, int depth, boolean[] visited) {
        if (result.length == depth) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = arr[i];
                permutations(arr, result, depth + 1, visited);
                visited[i] = false;
            }
        }
    }

    private static void permutationsRe(int[] arr, int[] result, int depth){
        if (result.length == depth) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for (int i = 0; i < arr.length; i++){
            result[depth] = arr[i];
            permutationsRe(arr, result, depth + 1);
        }
    }
}

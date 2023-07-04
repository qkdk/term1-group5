public class BinarySearch {
    // lower bound를 찾는 이분 탐색 함수
    public static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // upper bound를 찾는 이분 탐색 함수
    public static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 같아도 left를 증가시키면 upperbound
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // 예시를 위한 코드 실행
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 7, 7, 9};

        int target = 7;
        int lowerIndex = lowerBound(arr, target);
        int upperIndex = upperBound(arr, target);

        System.out.println("Lower Bound: " + lowerIndex);
        System.out.println("Upper Bound: " + upperIndex);
    }
}

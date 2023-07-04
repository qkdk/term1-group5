import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class boj_10799 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String readLine = br.readLine();

        Deque<Character> q = new ArrayDeque<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < readLine.length(); i++) {
            q.add(readLine.charAt(i));
        }

        // 레이저 발사일 경우 stack의 사이즈 만큼
        // 아닐경우 +1
        // prevValue를 만들어서 이전 poll의 값을 가지고 있는다.
        // prevValue 기 (
        // curValue 가 ) 인경우 레이저

        Character prevValue = q.poll();
        stack.add(prevValue);

        int answer = 0;
        while (!q.isEmpty()) {
            Character curValue = q.poll();

            if (curValue == '(') {
                stack.add(curValue);
            } else {
                stack.pop();
                if (prevValue == '(') {
                    // 레이저인 경우
                    answer += stack.size();
                } else {
                    // 그냥 삭제인 경우
                    answer++;
                }
            }
            prevValue = curValue;
        }
        System.out.println(answer);
    }
}

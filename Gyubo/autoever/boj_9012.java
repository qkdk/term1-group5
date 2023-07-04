import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class boj_9012 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String readLine = br.readLine();
            Deque<Character> q = new ArrayDeque<>();
            Stack<Character> stack = new Stack<>();

            for (int j = 0; j < readLine.length(); j++) {
                q.add(readLine.charAt(j));
            }

            while (!q.isEmpty()) {
                Character p = q.poll();
                if (stack.isEmpty()) {
                    stack.push(p);
                } else {
                    if (stack.peek() == '(' && p == ')') {
                        stack.pop();
                    } else {
                        stack.push(p);
                    }
                }
            }

            if (stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

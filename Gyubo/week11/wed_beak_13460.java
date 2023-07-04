import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class wed_beak_13460 {

    static class Marble {

        int y;
        int x;
        char type;

        public Marble(int y, int x, char type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }

    static class Game {

        Marble marble1;
        Marble marble2;

        int depth;

        public Game(Marble marble1, Marble marble2, int depth) {
            this.marble1 = marble1;
            this.marble2 = marble2;
            this.depth = depth;
        }
    }

    private static final int[][] vector = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] table = new char[n][m];

        Marble redMarble = null;
        Marble blueMarble = null;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char input = s.charAt(j);
                if (input == 'B') {
                    blueMarble = new Marble(i, j, input);
                    table[i][j] = '.';
                } else if (input == 'R') {
                    redMarble = new Marble(i, j, input);
                    table[i][j] = '.';
                } else {
                    table[i][j] = input;
                }
            }
        }

        Deque<Game> q = new ArrayDeque<>();
        q.add(new Game(redMarble, blueMarble, 0));

        while (!q.isEmpty()) {
            Game game = q.poll();
            if (game.depth == 10) {
                System.out.println(-1);
                System.exit(0);
            }

            for (int d = 0; d < vector.length; d++) {
                // 가장먼저오는 구술선택
                Marble first;
                Marble last;

                if (d == 0) {
                    if (game.marble1.y > game.marble2.y) {
                        first = game.marble2;
                        last = game.marble1;
                    } else {
                        first = game.marble1;
                        last = game.marble2;
                    }
                } else if (d == 1) {
                    if (game.marble1.x < game.marble2.x) {
                        first = game.marble2;
                        last = game.marble1;
                    } else {
                        first = game.marble1;
                        last = game.marble2;
                    }
                } else if (d == 2) {
                    if (game.marble1.y < game.marble2.y) {
                        first = game.marble2;
                        last = game.marble1;
                    } else {
                        first = game.marble1;
                        last = game.marble2;
                    }
                } else {
                    if (game.marble1.x > game.marble2.x) {
                        first = game.marble2;
                        last = game.marble1;
                    } else {
                        first = game.marble1;
                        last = game.marble2;
                    }
                }

                boolean firstFlag = true;
                boolean lastFlag = true;

                int fcy = first.y;
                int fcx = first.x;
                int lcy = last.y;
                int lcx = last.x;

                boolean redFlag = false;
                boolean blueFlag = false;

                // 퍼스트 먼저 이동
                while (firstFlag || lastFlag) {
                    if (firstFlag) {
                        int ny = fcy + vector[d][0];
                        int nx = fcx + vector[d][1];
                        if (table[ny][nx] == '.') {
                            fcy = ny;
                            fcx = nx;
                        } else if (table[ny][nx] == 'O' && first.type == 'R') {
                            redFlag = true;
                            fcy = -1;
                            fcx = -1;
                            firstFlag = false;
                        } else if (table[ny][nx] == 'O' && first.type == 'B') {
                            blueFlag = true;
                            fcy = -1;
                            fcx = -1;
                            firstFlag = false;
                        } else {
                            firstFlag = false;
                        }
                    }

                    if (lastFlag) {
                        int ny = lcy + vector[d][0];
                        int nx = lcx + vector[d][1];
                        if (fcy == ny && fcx == nx) {
                            lastFlag = false;
                        } else if (table[ny][nx] == '.') {
                            lcy = ny;
                            lcx = nx;
                        } else if (table[ny][nx] == 'O' && last.type == 'R') {
                            redFlag = true;
                            lcy = -1;
                            lcx = -1;
                            lastFlag = false;
                        } else if (table[ny][nx] == 'O' && last.type == 'B') {
                            blueFlag = true;
                            lcy = -1;
                            lcx = -1;
                            lastFlag = false;
                        } else {
                            lastFlag = false;
                        }
                    }
                }
                if (redFlag && !blueFlag) {
                    System.out.println(game.depth + 1);
                    System.exit(0);
                } else if (redFlag || blueFlag) {
                    continue;
                }
                q.add(new Game(new Marble(fcy, fcx, first.type), new Marble(lcy, lcx, last.type), game.depth + 1));
            }
        }
    }
}

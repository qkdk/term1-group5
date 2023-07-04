import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class sat_codetree_packman {

    private static void dfs(int index, int value, int y, int x, boolean[][] visited, int[] route) {
        if (index == 3) {
            if (maxValue < value) {
                maxValue = value;
                packmanRoute = route;
            }
            return;
        }

        for (int d = 0; d < packmanVector.length; d++) {
            int ny = y + packmanVector[d][0];
            int nx = x + packmanVector[d][1];

            if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                continue;
            }
            if (visited[ny][nx]) {
                continue;
            }

            route[index] = d;
            visited[ny][nx] = true;
            dfs(index + 1, value + table[ny][nx], ny, nx, visited, route);
            visited[ny][nx] = false;
        }
    }

    static class Packman {

        int y;
        int x;

        public Packman(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public void move() {
            table[this.y][this.x] = 0;
            maxValue = -1;
            boolean[][] visited = new boolean[4][4];
            visited[this.y][this.x] = true;
            dfs(0, 0, this.y, this.x, visited, new int[3]);

            // 루트따라 이동
            for (int d : packmanRoute) {
                this.y += packmanVector[d][0];
                this.x += packmanVector[d][1];

                if (table[this.y][this.x] > 0) {
                    // 시체 만들기
                    dummyTable[this.y][this.x] = 2;

                    // 몬스터 삭제
                    for (Monster monster : monsterList) {
                        if (monster.y == this.y && monster.x == this.x) {
                            monsterList.remove(monster);
                        }
                    }

                }
            }
            table[this.y][this.x] = -1;
        }
    }

    static class Monster {


        int y;
        int x;
        int dir;

        public Monster(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }

        public void makeEgg() {
            eggList.add(new Egg(this.y, this.x, this.dir));
        }

        public void move() {
            int d = this.dir - 1;
            for (int i = 0; i < monsterVector.length; i++) {
                d = ++d % monsterVector.length;
                int ny = this.y + monsterVector[d][0];
                int nx = this.x + monsterVector[d][1];

                // 범위를 벗어난 경우
                if (ny < 0 || nx < 0 || ny > table.length || nx >= table[0].length) {
                    continue;
                }
                // 팩맨이 있는 경우
                if (table[ny][nx] == -1) {
                    continue;
                }
                // 시체가 있는 경우
                if (dummyTable[ny][nx] > 0) {
                    continue;
                }

                this.y = ny;
                this.x = nx;
                this.dir = d;
                break;
            }
        }


        @Override
        public String toString() {
            return "Monster{" +
                    "y=" + y +
                    ", x=" + x +
                    ", dir=" + dir +
                    '}';
        }
    }

    static class Egg {

        int y;
        int x;
        int dir;

        public Egg(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }

        public void birth() {
            monsterList.add(new Monster(y, x, dir));
        }
    }

    private static int[][] table = new int[4][4];
    // 팩맨은 -1 로 표시
    // 몬스터는 양수 로 표시 (몬스터의 숫자)
    // 시체는 표시 안함
    // 알은 표시 안함
    private static int[][] dummyTable = new int[4][4];
    private static List<Monster> monsterList = new LinkedList<>();
    private static List<Egg> eggList = new LinkedList<>();
    private static int maxValue;
    private static int[] packmanRoute;

    private static final int[][] monsterVector = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    private static final int[][] packmanVector = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Packman packman = new Packman(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        table[packman.y][packman.x] = -1;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            monsterList.add(new Monster(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1));
        }

        for (int i = 0; i < t; i++){
            simulation(packman);
        }

        print();

    }

    private static void simulation(Packman packman) {
        for (Monster monster : monsterList) {
            monster.makeEgg();
        }
        for (Monster monster : monsterList) {
            monster.move();
        }
        table = new int[4][4];
        for (Monster monster : monsterList) {
            table[monster.y][monster.x]++;
        }
        packman.move();
        // 몬스터 시체 처리
        for (int i = 0; i < dummyTable.length; i++){
            for (int j = 0; j < dummyTable.length; j++){
                if (dummyTable[i][j] > 0){
                    dummyTable[i][j]--;
                }
            }
        }
        for (Egg egg : eggList) {
            egg.birth();
        }
    }

    private static void print() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

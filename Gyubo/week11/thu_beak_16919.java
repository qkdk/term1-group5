import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class thu_beak_16919 {

    private static final int[][] vector = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static char[][] table;
    static int[][] timeTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        table = new char[r][c];
        timeTable = new int[r][c];
        for (int i = 0; i < r; i++) {
            String readLine = br.readLine();
            for (int j = 0; j < c; j++) {
                char input = readLine.charAt(j);
                if (input == 'O') {
                    timeTable[i][j] = 3;
                }
                table[i][j] = readLine.charAt(j);
            }
        }

        if (n > 4){
            n = n % 4;
            n += 4;
        }

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                applyTime();
            } else {
                fillBomb();
                applyTime();
            }
        }

        printChar(table);
    }

    private static void fillBomb() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] == '.') {
                    table[i][j] = 'O';
                    timeTable[i][j] = 4;
                }
            }
        }
    }

    private static void applyTime() {
        char[][] resultTable = new char[table.length][table[0].length];
        int[][] resultTimeTable = new int[table.length][table[0].length];

        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[0].length; x++) {
                if (--timeTable[y][x] == 0) {
                    resultTable[y][x] = '.';
                    resultTimeTable[y][x] = 0;
                    for (int d = 0; d < vector.length; d++) {
                        int ny = y + vector[d][0];
                        int nx = x + vector[d][1];

                        if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                            continue;
                        }

                        resultTable[ny][nx] = '.';
                        resultTimeTable[ny][nx] = 0;
                    }
                }
                if (resultTable[y][x] == '\u0000') {
                    resultTable[y][x] = table[y][x];
                    resultTimeTable[y][x] = timeTable[y][x];
                }
            }
        }

        table = resultTable;
        timeTable = resultTimeTable;
    }


    private static void printChar(char[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }

    private static void printInt(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
    }
}

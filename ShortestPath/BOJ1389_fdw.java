import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 백준 유저 중에서 케빈 베이컨 수가 가장 작은 사람을 찾으려 함.
    // 모든 사람과 케빈 베이컨 게임을 했을 때, 나오는 단계의 합

    // 모든 정점에서 모든 정점의 최단거리를 구하고, 그 합을 비교하면 됨.
    public static int[][] map;
    public static int inf = 5000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], inf);
        }

        int a, b;
        for (int m = 0; m < M; m++) {
            line = br.readLine().split(" ");
            a = Integer.parseInt(line[0]) - 1;
            b = Integer.parseInt(line[1]) - 1;
            map[a][b] = 1;
            map[b][a] = 1;
        }

        for (int n = 0; n < N; n++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > map[i][n] + map[n][j]) {
                        map[i][j] = map[i][n] + map[n][j];
                    }
                }
            }
        }

        // result
        int min = inf;
        int num = -1;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                sum += map[i][j];
            }
            if (min > sum) {
                min = sum;
                num = i;
            }
        }
        System.out.println(num + 1);
    }
}
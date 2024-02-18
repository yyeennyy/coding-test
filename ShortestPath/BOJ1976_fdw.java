import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i == j) {
                    map[i][j] = 1; continue;
                }
                if (map[i][j] == 0) {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int n = 1; n <= N; n++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][n] == Integer.MAX_VALUE || map[n][j] == Integer.MAX_VALUE) continue;
                    if (map[i][j] > map[i][n] + map[n][j])
                        map[i][j] = map[i][n] + map[n][j];
                }
            }
        }

        String[] path = br.readLine().split(" ");
        for(int i = 0; i < M - 1; i++) {
            int startCity = Integer.parseInt(path[i]);
            int endCity = Integer.parseInt(path[i + 1]);
            if (map[startCity][endCity] == Integer.MAX_VALUE) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
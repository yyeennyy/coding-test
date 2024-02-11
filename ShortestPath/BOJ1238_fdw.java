import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NMX = br.readLine().split(" ");
        int N = Integer.parseInt(NMX[0]);
        int M = Integer.parseInt(NMX[1]);
        int X = Integer.parseInt(NMX[2]);

        int[][] D1 = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    D1[i][j] = 0; continue;
                }
                D1[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int time = Integer.parseInt(line[2]);
            D1[start][end] = time;
        }

        for (int n = 1; n <= N; n++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (D1[i][n] != Integer.MAX_VALUE && D1[n][j] != Integer.MAX_VALUE) {
                        if (D1[i][j] > D1[i][n] + D1[n][j]) {
                            D1[i][j] = D1[i][n] + D1[n][j];
                        }
                    }
                }
            }
        }
        for (int n = 1; n <= N; n++) {
            for (int i = 1; i <= N; i++) {
                if (D1[X][n] != Integer.MAX_VALUE && D1[n][i] != Integer.MAX_VALUE){
                    if (D1[X][i] > D1[X][n] + D1[n][i]) {
                        D1[X][i] = D1[X][n] + D1[n][i];
                    }
                }
            }
        }

        int longest = -1;
        for (int i = 1; i <= N; i++) {
            if (D1[i][X] == Integer.MAX_VALUE || D1[X][i] == Integer.MAX_VALUE) continue;
            if (longest < D1[i][X] + D1[X][i])
                longest = D1[i][X] + D1[X][i];
        }
        System.out.println(longest);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    // floyd: 400 * 400 * 400 = 64000000
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int[][] map = new int[N][N];
        int K = Integer.parseInt(line[1]);
        for (int k = 0; k < K; k++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]) -1;
            int b = Integer.parseInt(line[1]) -1;
            map[a][b] = -1;
            map[b][a] = 1;
        }

        for (int n = 0; n < N; n++) {
            for (int i = 0; i < N; i ++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == -1) continue;
                    if (map[i][n] == -1 && map[n][j] == -1) {
                        map[i][j] = -1;
                        map[j][i] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int S = Integer.parseInt(br.readLine());
        for (int s = 0; s < S; s++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]) -1;
            int b = Integer.parseInt(line[1]) -1;

            sb.append(map[a][b]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
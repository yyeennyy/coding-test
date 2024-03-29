import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    // 물건쌍 N개끼리의 비교걸과가 일부 주어질 때 (i, j)
    // 비교 결과를 알 수 없는 물건쌍의 개수
    // floyd: 100 * 100 * 100  = 1000000 (100만)
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]) -1;
            int b = Integer.parseInt(line[1]) -1;
            // a > b
            map[a][b] = 1;
            map[b][a] = -1;
        }

        for (int n = 0; n < N; n++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) continue;
                    if (map[i][n] == 1 && map[n][j] == 1) {
                        map[i][j] = 1;
                        map[j][i] = -1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0)
                    sum++;
            }
            sb.append(sum -1 + "\n");
        }
        System.out.println(sb);
    }
}
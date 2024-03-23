import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 모든 정점 (i, j)에 대해서 i -> j로 가는 길이가 양수인 경로가 있는지 구하기
public class Main {

    public static int N;
    public static int[][] map;
    public static int[][] paths;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        paths = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }

        for (int n = 0; n < N; n++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][n] == 0 && map[n][j] == 0) continue;
                    if ((map[i][n] != 0 && map[n][j] != 0)
                            || (i == n && map[n][j] != 0)
                            || (map[i][n] != 0 && n == j)) {
                        paths[i][j] = 1;
                        map[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(paths[i][j] + " ");
            }
            System.out.println();
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 모든 정점 (i, j)에 대해서 i -> j로 가는 길이가 양수인 경로가 있는지 구하기
public class Main {

    public static int N;
    public static int[][] map;
    public static int inf = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] nums = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                // 아예 처음부터 map 에다가 반영해도 무관
                // 가는 길이 없으면 inf 라는 것을 처음부터 map 에 반영
                map[i][j] = Integer.parseInt(nums[j]) == 0 ? inf : 1;  // 아예 처음부터 map 에다가 반영해도 무관
            }
        }

        for (int n = 0; n < N; n++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > map[i][n] + map[n][j])
                        map[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d ", map[i][j] == inf ? 0 : 1);
            }
            System.out.println();
        }
    }
}
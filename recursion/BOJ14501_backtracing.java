import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int max;
    public static int N;
    public static int[][] data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[N + 1][2];
        for (int i=1; i<=N; i++){
            String[] tmp = br.readLine().split(" ");
            data[i][0] = Integer.parseInt(tmp[0]);
            data[i][1] = Integer.parseInt(tmp[1]);
        }

        // deadline 갱신: 일이 종료되는 날임
        // 반복문 인덱스: deadline 의 다음날임 (인덱스부터 시작하여 재귀호출 할 수 있도록)

        // 필요 정보: deadline, price,
        // 1일, 2일, ... 에서부터 가능한 조합 탐색
        for (int i=1; i<=N; i++) {
            backtracking(0, 0, false);
        }
        System.out.println(max);
    }

    public static void backtracking(int deadline, int price, boolean finishFlag) {
        if (finishFlag) {
            if (price > max) {
                max = price;
            }
            return;
        }
        // 현재 deadline 부터 가능한 조합을 모두 돌게 된다.
        for(int i = deadline + 1; i<=N; i++) {
            int newDeadline = i + data[i][0] - 1;
            if (newDeadline < N) {
                backtracking(newDeadline, price + data[i][1], false);
            } else if (newDeadline == N) {
                backtracking(newDeadline, price + data[i][1], true);
            } else {
                backtracking(deadline, price, true);
            }
        }
    }
}
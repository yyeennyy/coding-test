import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N == 1) {
            System.out.println(1);
            return;
        }
        // 수를 찾아가보면 규칙을 발견 가능 ..
        // level별로 고정된 평행사변형(고정된 거리) 안에서 찾아낼 수 있었음..
        int init = 2;
        for(int plus=6; init <= N; plus = plus + 6){
            if (init <= N && N <= init + plus - 1) {
                System.out.println(plus/6 + 1);
                break;
            }
            init += plus;
        }
    }
    // 부족했던점: 경계값, 기준, 초기값 설정
}
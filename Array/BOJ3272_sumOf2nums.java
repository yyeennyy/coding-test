https://www.acmicpc.net/problem/3273
배운 것
- 인덱스가 "X-A < K"를 만족해야 할 때, 조건문을 넣는 것도 방법이겠지만, 배열길이를 거기까지 확장하여 초기값이면 안됨을 통해 판단할 수 있다.




import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String[] seq_ = sc.nextLine().split(" ");
        int[] seq = new int[1000001];
        boolean[] visit = new boolean[1000001];
        for(int i=0; i<N; i++)
            seq[i] = Integer.parseInt(seq_[i]);
        int X = Integer.parseInt(sc.nextLine());

        int res = 0;
        for(int i=0; i<N; i++){
            if(X-seq[i] > 0){
                if(X-seq[i]<=1000000){
                    if(visit[X-seq[i]]) res++;
                    visit[seq[i]] = true;
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}

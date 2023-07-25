import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] line = br.readLine().split(" ");
    int N = Integer.parseInt(line[0]);
    int K = Integer.parseInt(line[1]);

    int[] coins = new int[N];
    for(int i=0; i<N; i++){
      coins[i] = Integer.parseInt(br.readLine());
    }

    // 큰 거 위주로 고르기
    // A1 = 1이므로 반드시 가능함
    int cnt = 0;
    int remain = K;
    for(int a=N-1; a>-1; a--){
      if(K%coins[a]==0 && K/coins[a]==0){
        continue;
      }
      cnt += remain/coins[a];
      remain = K%coins[a];
    }
    System.out.println(cnt);
  }
}


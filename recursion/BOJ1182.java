풀이과정에서 느낀 것:
- 재귀에서 직전재귀값을 체크할 때 인덱스 경계라고 return해버리면 안된다는 것을 유의
이 느낌은 아래 풀이에 관련된 것은 아니지만, 하나의 시행착오였다.
  

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static int N, S;
    public static int cnt = 0;
    public static int[] input;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        S = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        input = new int[N];
        for(int i=0; i<N; i++){
            input[i] = Integer.parseInt(line[i]);
        }
        findSequence(0, 0);
        System.out.println(cnt);
    }

    public static void findSequence(int i, int sum){
        if(i>=N) return;
        if(sum+input[i] == S)
            cnt++;
        findSequence(i+1, sum); // 현재 원소를 선택하지 않은 경우
        findSequence(i+1, sum+input[i]);  // 현재 원소를 선택한 경우
    }
}

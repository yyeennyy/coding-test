// "1이 될 때까지"

/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
1. 만약 N < K일 경우 N % K 가 무의미하다.
   ∴ N < K 에서 무조건 N-1만 하게 한다면 비교연산 횟수를 줄일 수 있다.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/


/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int K = sc.nextInt();

    int count = 0;

    while(N != 1){
      if (N % K == 0){
        N = (int) N/K;
      }
      else{
        N = N-1;
      }
      count++;
    }

    System.out.println(count);
  }
}



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 수정한 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

while(N >= K){
  if (N % K == 0){
    N = (int) N/K;
  }
  else{
    N = N-1;
  }
  count++;
}

while (N > 1){
  N = N - 1;
  count++;
}

System.out.println(count);


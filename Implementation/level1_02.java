// "시각"
// 완전 탐색 유형 (Brute Forcing) - 가능한 경우의 수를 모두 검사해보는 탐색 방법

/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
1. N시 XX분 XX초에 대하여 N=3인 경우는 60*60경우가 있는데
   바보같게도.. +1경우만 추가했다가 제일 나중에 수정했다.
   
   경우의 수를 처음부터 명확히 세도록 하자. 
   

2. 십의자리 숫자에 3이 포함:
   N / 10 == 3 || N % 10 == 3 
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
후기 : 무난하게는 풀었으나...
       처음부터 경우의수를 올바르게 생각하자.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
      
    int count = 0;

    for(int n=0; n<=N; n++){
      if (n != 0 && n % 3 == 0){
        count += 60*60;
        continue;
      } 

      //0~59에는 3이 포함되는 것이.. 3, 13, 23, 30대수, 43, 53 총 5개+10개 
      count += 15 * 60;

      //m에 3이 없는 경우는 60-15 = 45경우. 
      //이 45경우에 대해서는 45 * 15경우를 추가 가능하다.
      count += 45 * 15;
    }

    System.out.println(count);    
  }
}



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 다른 방법 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ "문자열에 3이 포함되어 있는가"에 집중하여 구현한 코드다.

- 내가 작성했던 코드: 경우의수가 규칙이 있고 간단하므로, 
                    count에 직접 각 케이스별 경우의수를 더했다.
                    
- 이 코드: 문자열에 3이 포함되어있는지를 체크하여 count += 1          
           → check 함수를 3중for문 안에 사용하였다.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
public static boolean check(int h, int m, int s){
  if (h % 10 == 3 || m / 10 == 3 || m % 10 == 3 || s / 10 == 3 || s % 10 == 3)
    return true;
  return false;
}

public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
  int h = sc.nextInt();
  int count = 0;

  for (int i = 0; i <= h; i++){
    for (int j = 0; j < 60; j++){
      for (int k = 0; k < 60; k++){
        if (check(i, j, k)) count++;
      }
    }
  }

  System.out.println(count);
}


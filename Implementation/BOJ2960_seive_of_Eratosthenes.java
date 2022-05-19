/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 새로 알게된 것 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
k번째로 조건을 만족하는 경우를 찾기 위해서,

▶ 나의 경우          : 변수 count와 k를 매번 비교
                        2중 for문을 빠져나오기 위해 break loop1; 사용 (named loop!)
                        
▶ 마음에 드는 방식: 해당 조건에서 k--처리 & 
                    for문에서 k!=0 조건식 이용 
                    (i.e. 굳이 변수 count를 만들지 x)
                        ▷ 이렇게 할 경우, 
                           2중 for문을 한번에 빠져나올 수 있다.
                           by 모든 for문에 k!=0 조건 달기!
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
이건 가볍게 아래처럼 구현 완료!
몇번째로 지우는 중인가 : bool배열의 인덱스를 활용!
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

import java.util.*;

public class Main{

  public static boolean isPrime(int num){
    for(int i = 2; i < num; i++){
      if (num % i == 0)
        return false;
    }
    return true;
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    boolean[] erased = new boolean[n+1];

    int j = 0, count = 0;
    int answer = 0;
    loop1 : for(int i = 2; i <= n; i++){
      if(isPrime(i)){
        for(int multi = i; multi <= n; multi+=i){
          if(!erased[multi]){          // if(erased[multi]) continue;
            erased[multi] = true;      // erased[multi] = true;
            if(++count == k){
              answer = multi;
              break loop1;
            }
          }
        }
      }
    }
    System.out.println(answer);
    sc.close();
  }
}



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 다른 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
로직은 유사하다. 
그러나 차이점을 한번 인식해보도록 하자.

◆for문 조건식이 인상깊다!
▷ num = prime_number * maximum_multiplier 를 사용 : for(int j=1; j<=n/i && k!=0; j++)
ㄴ변수 : 수의 multiplier로서 지정
▷ 내 방식은 이거였다 : for(int multi = i; multi <= n; multi+=i)
ㄴ변수 : multiply의 결과 값으로 지정

◆또한 k번째 값을 구하기 위하여 k-- 와 k!=0 조건을 활용!
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int now = 0;
    
    boolean[] p = new boolean[1010];

    for(int i=2; i<=n && k!=0; i++){
      for(int j=1; j<=n/i && k!=0; j++){
        if(p[i*j]) continue;
        p[i*j] = true;
        k--;
        now = i*j;
      }
    }
    
    System.out.println(now);
    sc.close();

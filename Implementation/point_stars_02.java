/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
어제 공부한 String클래스의 .repeat()메서드를 이용해보았다.
별찍기를 구현하기 훨씬 편해졌다.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/


/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */

import java.util.*;

public class Main{

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.nextLine();

    for(int i = 1; i <= 2*n-1; i++){
      if(i > n){
        System.out.println("*".repeat(2*n-i)
                          + " ".repeat(2*(i-n))
                          + "*".repeat(2*n-i));
        continue;
      }
      System.out.println("*".repeat(i)
                        + " ".repeat(2*(n-i))
                        + "*".repeat(i));
    }
    sc.close();
  }
}

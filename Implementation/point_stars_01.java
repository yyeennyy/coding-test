/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 발견한 다른 방법 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ 문자의 반복 출력: " ".repeat(n)
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/


/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
import java.util.*;

public class Main{

  public static void space(int k){
    for (int i = 0; i < k; i++){
      System.out.print(" "); 
    }
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.nextLine();  
    
    for (int i = 1; i <= n; i++){ 
      space(n-i);
      for (int j = 0; j < 2*i-1; j++){
        System.out.print("*");
      }
      System.out.println();
    }
    sc.close();
  }
}


/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 다른 풀이 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ 문자의 반복 출력: " ".repeat(n)
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
for (int i=0; i<n; i++)
  System.out.println(" ".repeat(n-i-1) + "*".repeat(2*i + 1);
                     

// BOJ10994 - 별찍기 - 19
// 부가 설명 : https://splendidlolli.tistory.com/461

// 교훈 : 처음부터 알쏭달쏭하게 시작하지 말고, 확실히 해결법을 구상한 뒤에 코드로 옮기자.
//        알쏭달쏭한 채로 시작하니까 코드를 고치고 고치고 고치면서 난잡해지기 쉬웠다.
//        코드도 난잡해지고 머릿속도 혼란스러워진다.
//        그러니까 구상을 처음부터 확실히 하고, 확신을 가지고 들어가자.

import java.util.*;

public class Main{

  public static boolean flag = false;
  public static int max_stars, N;

  public static void print_else_line(int k){
    // box k개 -> 양옆에 k개의 별 찍기 (k != 3)
    // 그럼 공백은 -> max_stars - k*2*2
    if (k != N) {
      System.out.print("* ".repeat(k)
                      + " ".repeat(max_stars - k*2*2)
                      + " *".repeat(k) + "\n"); 
    }
  }
  
  public static void draw_step_of(int k, boolean flag){
    if (flag)
      print_else_line(k);
    
    // box k개 단계
    // - count of "*" or " " except line : ((k-1)*2)*2
    // - count of stars at line : max_stars - ((k-1)*2)*2
    System.out.print("* ".repeat((k-1))
                    + "*".repeat((max_stars - ((k-1)*2)*2))
                    + " *".repeat((k-1)) + "\n");
    
    if (!flag)
      print_else_line(k);
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    max_stars = (2*N-1)*2 - 1;
    sc.nextLine();

    for(int i = 1; i <= N; i++)
      draw_step_of(i, flag);

    flag = true;
    for (int i = N-1; i >= 1; i--)
      draw_step_of(i, flag);
      
    sc.close();
  }
}

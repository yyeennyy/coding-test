/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
아래 참고!
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
- 2차원 배열 of 파스칼 삼각형
규칙에 따라 정직하게 구현했다.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
import java.util.*;

// 풀이 : 파스칼 삼각형 배열 자체를 만들기

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();

    int[][] pas = new int[n][];
    for(int i = 0; i < n; i++){
      pas[i] = new int[i+1];
      pas[i][0] = 1;
      pas[i][i] = 1;
    }
    for(int i = 1; i < n-1; i++){
      for(int j = 0; j < i; j++){
        pas[i+1][j+1] = pas[i][j] + pas[i][j+1];
      }
    }
    int result = pas[n-1][k-1];
    System.out.println(result);
    sc.close();
  }
}


/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 더 간결한 구현 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
이렇게도 작성 가능하구나!!
1. for(int i = 0; i < m; a[i++][0]=1); 증감식 부분에서 할당도 가능!
2. a[sc.nextInt()-1][sc.nextInt()-1]; 배열 인덱스를 직접 스캐너로 입력받기!
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int m = 31;   // by 조건 : 1 <= k <= n <= 30
    int[][] a = new int[m][m];
    
    // pascal's triangle의 0열에만 1을 넣고, 끝열에는 1을 직접 넣지 않았다.
    // [m][m] 사이즈 배열 -> 연산 중에 끝열에 1이 들어가게 된다. (1 + 0 = 1)
    for(int i = 0; i < m; a[i++][0]=1);  //우와..!
    for(int i = 1; i < m; i++){
      for(int j = 1; j <= i; j++)
        a[i][j] = a[i-1][j-1] + a[i-1][j];
    }
    
    System.out.println(a[sc.nextInt()-1][sc.nextInt()-1]); // 헉..
    sc.close();
  }
}


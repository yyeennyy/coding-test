// "상하좌우"
// simulation 유형 - 제시한 알고리즘을 한 단계씩 직접 수행

/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/




/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ 문자 U, D, R, L 케이스에 따라 동작이 달라지므로 switch문 사용
◆ 이동횟수 N번 : O(N)

주석으로 세세한 개선점을 달아보겠다.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    String tmp_ = sc.nextLine();       //<-- 그저 버퍼비우는 용도이므로 굳이 할당 X

    // 아래 두줄 : sc.nextLine().split(" "); 로 합치기
    String str = sc.nextLine();
    String[] direction_array = str.split(" ");  

    int r = 1;
    int c = 1;

    // 구현의 핵심 부분
    for(int i=0; i<direction_array.length; i++){
      String dir = direction_array[i];   
      switch(dir){
        case "U":
          if (r==1) continue;
          r--; break;

        case "D":
          if (r==N) continue;
          r++; break;

        case "L":
          if (c==1) continue;
          c--; break;

        case "R":
          if (c==N) continue;
          c++; break;
      }
    }
    System.out.printf("%d %d", r, c);
  }
}




/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 다른 방법 예시 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ 다음처럼 딕셔너리를 만들고 동일 인덱스로 다룬다.
int[] dr = {0, 0, -1, 1};
int[] dc = {-1, 1, 0, 0};    
char[] move_types = {'L', 'R', 'U', 'D'};

◆ 일단 이동좌표를 계산 후, 이동이 유효한지 체크하여 갱신한다.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

Scanner sc = new Scanner(System.in);
int N = sc.nextInt();
sc.nextLine();
String[] direction_plan = sc.nextLine().split(" ");

int r = 1; int c = 1;

// 이동방향 설정 - 동일 인덱스로 다룰 것임
int[] dr = {0, 0, -1, 1};
int[] dc = {-1, 1, 0, 0};    
char[] move_types = {'L', 'R', 'U', 'D'};

for (int i=0; i<direction_plan.length; i++){
  char dir = direction_plan[i].charAt(0);    //<-- charAt(0) 기억하자.

  // nc, nr : 이동 후 좌표
  int nc = -1, nr = -1;
  for(int j = 0; j < 4; j++) {
    if(dir == move_types[j]) {
      nc = c + dc[j];
      nr = r + dr[j];
    }
  }
  // nc, nr가 공간을 벗어나는지 확인
  if(nc < 1 || nr < 1 || nc > N || nr > N) continue;

  // 좌표갱신
  c = nc;
  r = nr;
}
System.out.printf(r + " " + c);

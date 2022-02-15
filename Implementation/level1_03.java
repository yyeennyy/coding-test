/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ char값 정수는 -'0'해주어야 int 정수 수치를 가진다.
◆ 알파벳char을 정수화하려면 - 'a' + 1 해준다.

◆ x, y 경로가 정해진 문제면 인덱스로 다루는 방법을 떠올리자.

◆ 경우를 나누는 조건을 일일이 따져 +n 했었는데,
   for문 돌리고 조건만족시 +1하는 로직도 생각하자.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
후기 : 시간초과했다.
◆ if문 적극이용 - 경우를 나누는 조건에 집중
◆ 공백칸수를 조건으로 삼았다
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

import java.util.*;

public class Main{
  
  public static int space(int loc){
    int x = -1;
    if (loc <= 4) x = loc - 1;
    if (loc >= 5) x = 8 - loc;
    return x;
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String loc = sc.nextLine();

    String[] location = loc.split("");
    // 여기서도 미숙함이 드러난다. ㅡㅡㅡㅡㅡㅡㅡㅡ
    int row = Integer.parseInt(location[1]);
    char col_ = location[0].charAt(0);

    char[] col_list = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    int result = 0;
    int col = 0;
    for(int i=0; i<8; i++){
      if (col_ == col_list[i]) col = i+1;
    }
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    while(true){
      // 8인 경우 - row, col 2띄워짐
      if (space(row) > 1){
        if(space(col) > 1) { result = 8; break; };
      }
      
      // 6인 경우 - row가 1만 띄워지고 col은 2이상 띄워짐
      if (space(row) == 1 || space(col) == 1){
        if (space(col) > 1) { result = 6; break; }
        if (space(row) > 1) { result = 6; break; }
      }
  
      // 4인 경우 - row가 0띄워진 경우: col이 2띄워짐
      //           row가 1띄워진 경우: col이 1띄워짐
      if (space(row) == 0 || space(col) == 0 ){
        if (space(col) == 2) { result = 4; break; }
        if (space(row) == 2) { result = 4; break; }
      }
      if (space(row) == 1 || space(col) == 1){
        if (space(col) == 1) { result = 4; break; }
        if (space(row) == 1) { result = 4; break; }
      }
  
      // 3인 경우 - row가 0띄워지고 col은 1 띄워짐
      if (space(row) == 0 || space(col) == 0){
        if (space(col) == 1) { result = 3; break; }
        if (space(row) == 1) { result = 3; break; }
      }
      
      // 2인 경우 - 모서리 only
      if (space(row)==0 && space(col)==0) { result = 2; break; }
    }
    
    System.out.println(result);
  }
}


/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 정리된 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ 이동가능한 방향을 정의
ㄴ인덱스로 다룬다
◆ 이동가능한지 체크하고 count++
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

// 현재 위치
Scanner sc = new Scanner(System.in);
String loc = sc.nextLine();
int row = loc.charAt(1) - '0';
int col = loc.charAt(0) - 'a' + 1;

// 이동 가능 방향 8가지
int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

// 8가지 이동경우중 가능한 경우 체크
int result = 0;
for (int i = 0; i < 8; i++) {
  int next_row = row + dy[i];
  int next_col = col + dx[i];

  if (next_row > 0 && next_row < 9 && next_col > 0 && next_col < 9){
    result += 1;
  }
}


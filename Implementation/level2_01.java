/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ 클래스 변수로 선언할 줄 알아야 한다.
   변수 용도를 생각하고, "알맞은 위치에 선언하자"

◆ turn_time처럼 회전횟수를 체크해야 할 경우
   - 횟수의 최대값이 있고 (4회)
   - 그 이하값이면 다시 초기화해야하는 경우 (4회가 아니면 리셋)
   다음과 같은 조작을 바로 떠올리자.
   (ex)
   turn_time = 0;
   turn_time++;
   if (turn_time == 4): { //process }
   else: turn time = 0;
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/



/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 첫 구현 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
후기 : 중간에 사고가 꼬여서 너무 오래 걸렸다.
       그저 d값으로만 인덱스를 다루면 되었는데,
       실수로 0, 1, 2, ... 순서의 인덱스도 동시에 적용해버렸다.
       두 생각이 꼬여버렸고, 수정하느라 시간을 허비했다.
◆ 클래스 변수로 선언할 줄 알아야 한다.
   변수 용도를 생각하고 선언하자.
◆ 구현시 깔끔하게 생각하지 못했다. 
   조건을 '깔끔하게 먼저' 정리 후 구현에 들어가자. 
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

import java.util.*;

public class Main{

  public static int rotate(int d){
    if (d==0) return 3;
    return --d;
  }

  public static int back_d(int d){ 
    if (d > 1) return d-2;
    return d+2;
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    // 초기정보 입력받기
    int N = sc.nextInt();
    int M = sc.nextInt();
    int row = sc.nextInt();
    int col = sc.nextInt();
    int d = sc.nextInt();
    
    // 미숙한 부분
    // 왜 이렇게 짰니..? map[i][j]에 그냥 넣어도 되는데..
    int[][] map = new int[N][M];
    for (int i=0; i<N; i++){
      int[] element_ = new int[M];
      for (int j=0; j<M; j++){
        element_[j] = sc.nextInt();
      }
      map[i] = element_;
    }
    

    // 북0 동1 남2 서3
    // d로 방향을 다룰 예정
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};

    // 회전순서는 0 -> 3 -> 2 -> 1임에 유의하자.
    // 위에서 '회전 d 함수'를 만들었다.

    // 길 걷기 시작
    boolean flag = false;
    int result = 1;
    while(true){
      map[row][col] = 9;
      if (flag){
        int back_step = map[row+dy[back_d(d)]][col+dx[back_d(d)]];
        flag = false;
        if (back_step == 1) break;
        row += dy[back_d(d)];
        col += dx[back_d(d)];
        continue;
      }
      for (int i=0; i<4; i++){
        int next_step = map[row+dy[rotate(d)]][col+dx[rotate(d)]];
        flag = true;
        if (next_step == 0){
          d = rotate(d);
          row += dy[d];
          col += dx[d];
          result++; flag = false; break;
        }
        d = rotate(d); 
        continue;
      }
    }
    System.out.println(result);
  }
}





/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 정리된 코드 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ 적절한 클래스 변수 선언
◆ 깔끔한 사고 → 깔끔한 로직
◆ 뒤로 가는 것: -dx, -dy
   나는 direction을 정반대로 돌렸었는데,
   -dx, -dy까지도 바로 떠올릴 줄 알자.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

import java.util.*;

public class Main{
  // 클래스 변수
  public static int N, M, x, y, direction;
  
  // 기록용 맵
  public static int[][] note = new int[50][50];
  // 전체 맵 정보
  public static int[][] map = new int[50][50];
  
  // 방향정의 (북, 동, 남, 서)
  public static int dx[] = {0, 1, 0, -1};
  public static int dy[] = {-1, 0, 1, 0};

  public static void turn_left() {
    direction -= 1;
    if (direction == -1) direction = 3;
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();

    y = sc.nextInt();
    x = sc.nextInt();
    direction = sc.nextInt();
    note[y][x] = 1;

    // 맵 정보 입력
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        map[i][j] = sc.nextInt();
      }
    }
    
    // 시뮬레이션 시작
    int cnt = 1;
    int turn_time = 0;
    while(true) {
      // turn left 먼저 슉~
      turn_left();
      int nx = x + dx[direction];
      int ny = y + dy[direction];
      // 회전 이후 : 정면칸이 안간 곳인가?
      if (note[ny][nx] == 0 && map[ny][nx] == 0) {
        note[ny][nx] = 1;
        x = nx;
        y = ny;
        cnt += 1;
        turn_time = 0;
        continue;
      }
      // 회전 이후 : 정면칸이 갔던곳 or 바다
      else turn_time += 1;
      
      // 네 방향 모두 갈 수 없는 경우
      if (turn_time == 4) {
        // 가능한 다음 스텝은 back뿐이다.
        nx = x - dx[direction];
        ny = y - dy[direction];
        // 뒤로 갈 수 있으면 이동
        if(map[ny][nx] == 0) {
          x = nx;
          y = ny;
        }
        // 뒤로 갈 수 없으면 (바다)
        else break;
        turn_time = 0;
      }
    }
    System.out.println(cnt);
  }
}

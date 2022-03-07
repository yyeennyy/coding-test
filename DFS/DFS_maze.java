/* 미로찾기 최단거리 스텝 */
/* 블로그에 설명 : https://splendidlolli.tistory.com/454 */

import java.util.*;

public class Main{
  public static int N, M;
  public static int result = 0;
  public static boolean finishFlag = false;
  public static int[][] map = new int[201][201];

  // 이동 방향 정의 (하우상좌)
  // 우측하단을 먼저 고려하고자 한다
  public static int[] dx = {0, 1, 0, -1};
  public static int[] dy = {1, 0, -1, 0};

  
  public static int DFS(int y, int x){
    // map[y][x]에 서 있어도 되는지 판단
    if (map[y][x] == 0) return 0;
    map[y][x] = 2;

    // 종료조건
    if (y == N && x == M) {
      finishFlag = true;
      map[y][x] = 2;
      return result++;
    }

    // 갈 곳이 있는 경우 재귀
    // 미로를 찾았는데도 다른 i에서 이어서 길을 찾는 경우를 유의 
    // => finishFlag 설정
    for (int i=0; i<4; i++) {
      int nx = x + dx[i]; 
      int ny = y + dy[i];
      if (map[ny][nx] == 1) DFS(ny, nx);
      if (finishFlag == true) return result++;
    }
    
    // 돌아가야 하는 경우 (재귀로 갱신된 맵 반영)
    // 위 for문을 지나 왔다면 자동으로 map[ny][nx] != 1
    map[y][x] = 3;
    return 0;
  }

  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    // 맵 정보 로드
    N = sc.nextInt();
    M = sc.nextInt();
    sc.nextLine();
    for(int i=1; i<=N; i++){
      String line = sc.nextLine();
      for(int j=1; j<=M; j++){
        map[i][j] = line.charAt(j-1) - '0';
      }
    }

    // DFS
    DFS(1, 1);
    
    System.out.println(result);
  }
}

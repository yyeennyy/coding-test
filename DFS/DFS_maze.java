/* 미로찾기 최단거리 스텝 */
/* 블로그에 설명 : https://splendidlolli.tistory.com/454 */

import java.util.*;

public class Main{
  public static int N, M;
  public static int result = 0;
  public static boolean finishFlag = false;
  public static int[][] map = new int[200][200];

  public static int DFS(int y, int x){
    // map[y][x]에 서 있어도 되는지 판단
    if (map[y][x] == 0) return 0;
    map[y][x] = 2;

    int bellow = map[y+1][x];
    int right  = map[y][x+1];
    
    // 종료조건
    if (y == N && x == M) {
      finishFlag = true;   
      return result++;
    }

    // 갈 곳이 있는 경우 재귀
    // bellow와 right의 중복 처리 유의 => finishFlag 설정
    if (bellow == 1) DFS(y+1, x);
    if (finishFlag == true) return result++;
    if (right == 1) DFS(y, x+1);
    if (finishFlag == true) return result++;
    
    // 돌아가야 하는 경우 (재귀로 갱신된 맵 반영)
    bellow = map[y+1][x];
    right  = map[y][x+1];
    if (bellow != 1 && right != 1){
      map[y][x] = 3;
      return 0;
    }

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

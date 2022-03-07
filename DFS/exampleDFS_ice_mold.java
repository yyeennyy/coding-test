/*
▶ 얼음 몰드에 물을 부어 채우는 DFS
종료 조건 : 몰드 범위를 벗어나면 종료
알고리즘
* return은 true / false로 한다.
1. 현재 노드는 방문한 노드인지 판단
2. 방문하지 않았다면, "상하좌우 위치"에서 재귀호출,
   넷 모두 호출 뒤 true
   (즉, 어떤 곳의 상하좌우를 모두 탐색하라고 돌린 셈임)

▶ main에서 DFS의 사용
모든 노드에 대해 음료수를 채워야 하므로
for i, j에서 DFS(i, j)를 그냥 싹 돌린다.
- 연결된 노드라면 방문한 노드니까 상관 없고
- 방문 안한 노드(연결안된 노드)만 골라서 true를 반환하겠죠~

▶ 공백없는 문자열로 정수가 들어오는 경우 
ex) 001011010010
Scanner에 nextLine()으로 한 line을 받고, 
그 길이만큼 for문 j++를 돌려서 line.charAt(j) - '0'를 한 뒤 int배열에 담는다.
정수 char를 뽑아 int로 담으려면 '0'을 빼야 함을 잊지 말자.
*/



/* [ 핵심 요소 ]
 * - 2차원 좌표를 그래프 형태로 모델링
 * - 2차원 좌표에서 인접한 값의 묶음 찾기
 *
 * [ 알고리즘 ]
 * 1. 특정 지점의 상하좌우 지점 중에서,
 *    값이 0이고 방문하지 않은 지점 방문하기
 * 2. 방문한 지점에서 위 과정을 다시 진행 
**/

import java.util.*;

public class Main {

  public static int N, M;
  public static int[][] graph = new int[1000][1000];

  // DFS로 특정 노드 방문, 인접노드 방문
  public static boolean DFS(int x, int y) {
    // 범위 벗어나면 즉시 종료
    if (x <= -1 || x >= N || y <= -1 || y >= M) {
      return false;
    }
    // 현재 노드를 방문했는지 판단
    if (graph[y][x] == 0) {
      graph[y][x] = 1;
      //상하좌우 위치에 대해 재귀호출
      DFS(x, y + 1);
      DFS(x, y - 1);
      DFS(x - 1, y);
      DFS(x + 1, y);
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    M = sc.nextInt();
    sc.nextLine(); // 버퍼 지우기

    // 2차원 리스트 - mold 정보
    for (int i = 0; i < N; i++) {
      String str = sc.nextLine();
      for (int j = 0; j < M; j++) {
        graph[i][j] = str.charAt(j) - '0';
      }
    }

    // 모든 노드에 대해 음료수 채우기
    int result = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        // 현재 위치에서 DFS 수행        
        if (DFS(i, j)) {
          result += 1; 
        }
      }
    }
    System.out.println(result);
  }
}

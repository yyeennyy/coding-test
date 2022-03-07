/* 
boj 2777 : 단지번호붙이기
- ice mold 문제와 거의 유사함
*/

import java.util.*;

public class Main{
  public static int N;
  public static int result = 0;
  public static int count = 0;
  public static int[][] graph = new int[25][25];
  public static ArrayList<Integer> check_count = new ArrayList<>();

  public static boolean DFS(int y, int x){
    // 틀을 벗어나는 경우 - 그냥 종료 (스택에 넣지 x)
    if (y<0 || x<0 || y>=N || x>=N){
      return false;
    }
    
    // 탐색 대상 노드인가? yes
    // 방문 아직 안한 노드  ▷ count++ and 방문처리
    //                      ▷ 상하좌우(이웃) DFS 샥 돌리고 return true로 마무리
    //                       
    // 처음으로 값이 1인 노드가 루트노드임 ( true로 끝나게 되어있음 )
    // 값이 1인 노드를 밟을 때, 결국 true로 끝남 => DFS(i, j)는 if문에 true로 들어간다
    //                                          => if문은 DFS 트리 개수 만큼 돌아간다 (그때 count를 세면, 트리의 노드개수!)
    
    
    // 탐색 대상 노드인 경우
    if (graph[y][x] == 1) {
      count++;
      graph[y][x] = 2;
      DFS(y-1, x);
      DFS(y+1, x);
      DFS(y, x-1);
      DFS(y, x+1);
      return true;
    }
    // 탐색 대상 노드가 아닌 경우
    return false;
  }
  

  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    N = sc.nextInt();
    sc.nextLine();

    for (int i=0; i<N; i++){
      String str = sc.nextLine();
      for (int j=0; j<N; j++){
        graph[i][j] = str.charAt(j) - '0';
      }
    }

    // 모든 노드 방문하기
    for (int i = 0; i < N; i++){
      for (int j = 0; j < N; j++){
        if (DFS(i, j)){
          check_count.add(count);          
          count = 0;
        }
      }
    }
    
    int k = check_count.size();
    System.out.println(k);
    
    Collections.sort(check_count);
    for (int i=0; i<k; i++){
      System.out.println(check_count.get(i));
    }
  }
}

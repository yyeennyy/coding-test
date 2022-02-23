// BFS
// 인접리스트
// O(N)
// 자료구조 : 큐

import java.util.*;

public class Main {

  public static boolean[] visited = new boolean[9];
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

  // BFS 함수 정의
  public static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    // 현재 노드 방문처리
    visited[start] = true;

    while(!q.isEmpty()) {
      // 큐에서 하나 뽑기
      int x = q.poll();
      System.out.println(x + " ");
      // 방문하지 않은 인접 노드를 큐에 삽입
      for (int i = 0; i < graph.get(x).size(); i++){
        // 인접 노드는 i로 다루자
        int y = graph.get(x).get(i);
        if (!visited[y]) {
          q.offer(y);
          visited[y] = true;
        }
      }
    }
  }
  public static void main(String[] args) {
    // 그래프 초기화
    for (int i = 0; i < 9; i++) {
        graph.add(new ArrayList<Integer>());
    }

    // 각 노드의 인접노드 정보 입력
    graph.get(1).add(2);
    graph.get(1).add(3);
    graph.get(1).add(8);
    
    graph.get(2).add(1);
    graph.get(2).add(7);

    graph.get(3).add(1);
    graph.get(3).add(4);
    graph.get(3).add(5);

    graph.get(4).add(3);
    graph.get(4).add(5);

    graph.get(5).add(3);
    graph.get(5).add(4);

    graph.get(6).add(7);

    graph.get(7).add(2);
    graph.get(7).add(6);
    graph.get(7).add(8);

    graph.get(8).add(1);
    graph.get(8).add(7);

    bfs(1);
   }
}

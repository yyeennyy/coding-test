// DFS 기본예제
// 인접리스트 방식

import java.util.*;

public class Main {
  public static boolean[] visited = new boolean[9];

  // 2차원 ArrayList
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

  // DFS 함수 정의
  public static void dfs(int x) {
    // 현재 노드를 방문 처리
    visited[x] = true;
    System.out.println(x + " ");
    // 인접 노드(i로 다룸)를 재귀적으로 방문
    for (int i = 0; i < graph.get(x).size(); i++) {
      int y = graph.get(x).get(i);
      if (!visited[y]) dfs(y);
    }
  }

  public static void main(String[] args) {
    // 그래프 초기화
    for (int i = 0; i < 9; i++) {
      graph.add(new ArrayList<Integer>());
    }

    // 각 노드 1~9의 인접노드 정보 저장
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

    dfs(1);
  }
}

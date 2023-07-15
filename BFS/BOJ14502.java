Java!
- 동일객체 판정 기준 : 문자열로 지정 - equals(), hashCode() 재정의
  hashCode()를 재정의할 때는 Objects.hash()를 사용하면 편했음 - 가변인자 받음

Algorithm
- 조합 구현에 백트래킹 사용함 : 공부 필요

  ----------------------------------
  
import java.util.*;
import java.io.*;

// 바이러스가 퍼진 뒤 0의 개수(안전 영역)의 최댓값을 구하자.

public class Main {
  public static int[][] map;
  public static Queue<Node> q = new LinkedList<>();
  public static int[] dx = {0, 0, -1, 1};
  public static int[] dy = {-1, 1, 0, 0};
  public static int N, M;
  public static List<Node> comb;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] line = br.readLine().split(" ");
    N = Integer.parseInt(line[0]);
    M = Integer.parseInt(line[1]);

    // 맵 구성
    map = new int[N][M];
    List<Node> combiTargets = new ArrayList<>();
    for(int i=0; i<N; i++){
      line = br.readLine().split(" ");
      for(int j=0; j<M; j++){
        map[i][j] = Integer.parseInt(line[j]);
        if(map[i][j] == 0)
            combiTargets.add(new Node(i, j));
        }
      }

    // 조합 3개 리스트가 담긴 comb 얻기
    boolean[] visited = new boolean[combiTargets.size()];
    comb = new ArrayList<>();
    combination(combiTargets, visited, 0, combiTargets.size(), 3);
    
    int res = 0;
    for(int i=0; i<comb.size(); i+=3){
      List<Node> wall3 = comb.subList(i, i+3);
      // 맵 복사
      int[][] map_ = new int[N][M];
      for(int j=0; j<N; j++){
        for(int k=0; k<M; k++)
          map_[j][k] = map[j][k];
      }
      // 벽 3개 세우기
      for(Node node : wall3){
        map_[node.r][node.c] = 1;
      }
        
      // 1. 바이러스를 퍼뜨린다.
      map_ = BFS_Virus(map_);
      // 2. 0인 곳을 센다.
      int cnt = BFS_Count(map_);
      if(cnt != 0){
        if(cnt > res)
          res = cnt;
      }
    }
    System.out.println(res);      
  }
  
  // 조합 by 백트래킹
  public static void combination(List<Node> arr, boolean[] visited, int start, int n, int r) {
      if (r == 0) {
          print(arr, visited, n);
          return;
      }
      for (int i = start; i < n; i++) {
          visited[i] = true;
          combination(arr, visited, i + 1, n, r - 1);
          visited[i] = false;
      }
  }
  static void print(List<Node> arr, boolean[] visited, int n) {
      for (int i = 0; i < n; i++) {
          if (visited[i]){
              comb.add(arr.get(i));
          }
      }
  }

  public static int[][] BFS_Virus(int[][] m){
    Map<Node, Boolean> visited = new HashMap<>();
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        visited.put(new Node(i, j), false);
      }
    }
    q = new LinkedList<>();
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if(m[i][j] == 2){
          q.add(new Node(i, j));
          while(!q.isEmpty()){
            Node now = q.poll();
            for(int k=0; k<4; k++){
              int x = now.c + dx[k]; 
              int y = now.r + dy[k];
              if(x<0||y<0||y>=N||x>=M)
                continue;
              if(m[y][x]==0){
                Node n = new Node(y, x);
                if(!visited.get(n)){
                  visited.put(n, true);
                  q.add(n);
                  m[y][x] = 2;
                }
              }
            }
          }
        }
      }
    }
    return m;
  }
  
  public static int BFS_Count(int[][] m){
    int cnt = 0;
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if(m[i][j] == 0)
          cnt++;
      }
    }
    return cnt;
  }


  public static class Node{
    int r;
    int c;

    Node(int r, int c){
      this.r = r;
      this.c = c;
    }

    @Override
    public boolean equals(Object obj){
      if(obj instanceof Node){
        Node node = (Node) obj;
        return (r == node.r) && (c == node.c);
      } else {
        return false;
      }
    }
    
    @Override
    public int hashCode(){
      return Objects.hash(r, c);
    }

    @Override
    public String toString(){
      return String.format("(%d, %d)", r, c);
    }
  }
}


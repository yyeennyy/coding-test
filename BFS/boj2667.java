// 저번에 DFS로 푼 문제인데, 다시 BFS로 풀었다.


import java.util.*;

public class Main{
  public static Queue<Node> q = new LinkedList<>();
  public static int[][] check; // 방문:1 & 방문안함:0 
  public static int[][] map;
  public static int n;
  public static int[] dr = {-1, 1, 0, 0};
  public static int[] dc = {0, 0, -1, 1};

  public static boolean isTarget(int i, int j){
    if(map[i][j] != 0 && check[i][j] == 0)
      return true;
    return false;
  }
  
  public static int BFS(int i, int j){
    int count = 0;
    while(true){
      if(isTarget(i, j)){
        count++;
        check[i][j] = 1; // visited
        // input neighbor nodes in q (4-directions)
        for(int d=0; d<4; d++){
          int row = i + dr[d]; int col = j + dc[d];
          if(row >= 0 && col >= 0 && row < n && col < n){
            if(isTarget(row, col)){
              q.offer(new Node(row, col));
            }
          }
        }
      }
      Node nb;
      if ((nb = q.poll()) != null){
        i = nb.r;
        j = nb.c;
      } else {
        break;
      }
    }
    return count;
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt(); sc.nextLine();

    // map 구성
    map = new int[n][n];
    check = new int[n][n];
    for(int i=0; i<n; i++){
      String line = sc.nextLine();
      for(int j=0; j<n; j++){
        map[i][j] = line.charAt(j) - '0';
      }
    }

    // BFS
    List<Integer> cnts = new ArrayList<>();
    int count = 0;
    for(int i=0; i<n; i++){
      for(int j=0; j<n; j++){
        if((count = BFS(i, j)) != 0){
          cnts.add(count);
          q = new LinkedList<>();
        }
      }
    }
    Collections.sort(cnts);
    System.out.println(cnts.size());
    cnts.stream().forEach(x -> System.out.println(x));
  }
}

class Node {
  int r;
  int c;
  Node(int r, int c){
    this.r = r;
    this.c = c;
  }
}

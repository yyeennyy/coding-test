import java.util.*;

import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int CASE = Integer.parseInt(br.readLine());
    
    while(CASE > 0){
      String[] nums = br.readLine().split(" ");
      int M = Integer.parseInt(nums[0]);
      int N = Integer.parseInt(nums[1]);
      int K = Integer.parseInt(nums[2]);
  
      // 맵 구성
      int[][] map = new int[N][M];
      for(int i=0; i<K; i++){
        String[] locs = br.readLine().split(" ");
        int C = Integer.parseInt(locs[0]);
        int R = Integer.parseInt(locs[1]);
        map[R][C] = 1;
      }

      // 맵의 각 위치에서 BFS 진행
      // Queue<Node> q = new LinkedList<>();
      int count = 0;
      int[] dx = {0, 0, -1, 1};
      int[] dy = {-1, 1, 0, 0};
      int[][] cared = new int[N][M];
      Queue<Node> q = new LinkedList<>();
      for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
          // 현위치를 따져야 한다면
          if(map[i][j] == 1 && cared[i][j] != 1){
            // 현위치를 q에 넣고 BFS 돌리자
            q.add(new Node(i, j));
            while(!q.isEmpty()){
              Node now = q.poll();
              int x = now.x;
              int y = now.y;
              if(cared[y][x] == 1){
                continue;
              }
              cared[y][x] = 1;
              for(int k=0; k<4; k++){
                // 상하좌우 배추를 q에 넣는다.
                if(y+dy[k] >= 0 && x+dx[k] >= 0 && y+dy[k] < N && x+dx[k] < M)
                  if(map[y+dy[k]][x+dx[k]] == 1)
                    q.add(new Node(y+dy[k], x+dx[k]));
              }
            }
            // 현 위치랑 연결된 것은 전부 방문처리된 상태임
            count++;
          }
        }
      }
      System.out.println(count);
      CASE--;
    }
  }

  public static class Node{
    int x;
    int y;

    Node(int y, int x){
      this.y = y;
      this.x = x;
    }
  }
}

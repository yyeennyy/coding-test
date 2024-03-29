import java.util.*;
import java.io.*;

public class Main {
  public static int[][] box;
  public static int[] dx = {0, 0, -1, 1};
  public static int[] dy = {-1, 1, 0, 0};
  public static int N, M;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] line = br.readLine().split(" ");
    N = Integer.parseInt(line[1]);
    M = Integer.parseInt(line[0]);
    box = new int[N][M];
    for(int i=0; i<N; i++){
      line = br.readLine().split(" ");
      for(int j=0; j<M; j++){
        box[i][j] = Integer.parseInt(line[j]);
      }
    }

    // 하루가 지나면 익는다!
    // 1:익은토마토
    // 0:익지않은
    // -1: empty!

    // 유의: 저쪽에서 익어오는 거, 이쪽에서 익어오는 거.. 동시에 고려해야 함
    int[][] visit = new int[N][M];
    Queue<int[]> q = new LinkedList<>();
    // 익은 토마토 공평하게 먼저 q에 넣어두기 (거기서부터 공평히 익어가야 하니까)
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if(box[i][j] == 1){
          q.add(new int[]{i, j});
        }
      }
    }
    // 위에서 익토를 q에 넣어줬으니, poll만 해서 진행하면 된다. (익토 최소 1개 존재)
    while(!q.isEmpty()){
      int[] now = q.poll();
      for(int k=0; k<4; k++){
        int x = now[1] + dx[k];
        int y = now[0] + dy[k];
        if(x<0||y<0||y>=N||x>=M)
          continue;
        if(box[y][x]==0 && visit[y][x]==0){
          visit[y][x] = visit[now[0]][now[1]] + 1;  // 하루하루..
          box[y][x] = 1; // 익었다..
          q.add(new int[]{y, x});
        }
      }
    }
    // visit에서 가장 큰 값이, 모두 익을 때 까지의 최소 날짜다.
    int res = 0;
    int tmp = 0;
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if((tmp = visit[i][j]) > res){
          res = tmp;
        }
      }
    }
    if(!isPossible(box)){
      res = -1;
    }
    System.out.println(res);
  }

  public static boolean isPossible(int[][] box){
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if(box[i][j] == 0){
          return false;
        }
      }
    }
    return true;
  }
}


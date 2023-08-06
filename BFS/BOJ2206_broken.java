import java.util.*;
import java.io.*;
// 벽 부수고 이동하기
public class Main{
    public static int N, M;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};
    public static int[][] map;
    public static int[][][] distance;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // ------
        distance = new int[1000][1000][2];
        map = new int[1000][1000];
        // ------

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        for(int i=0; i<N; i++){
            line = br.readLine().split("");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        // 맵 구성은 끝났고, BFS의 최종 결과로 답을 구할 것이다.
        System.out.println(BFS());
    }

    public static int BFS(){
        // distance를 -1로 초기화
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                distance[i][j][0] = distance[i][j][1] = -1;
            }
        }
        // 첫 위치는 distance 1임
        distance[0][0][0] = distance[0][0][1] = 1;
        // 큐 생성
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        while(!q.isEmpty()){
            int[] now = q.poll();
            int y = now[0];
            int x = now[1];
            int b = now[2];
            if(y==N-1 && x==M-1) return distance[y][x][b];  // ★종료조건★
            for(int i=0; i<4; i++){
                int xx = x + dx[i];
                int yy = y + dy[i];
                if(yy<0||xx<0||xx>=M||yy>=N) continue;
                if(map[yy][xx]==0 && distance[yy][xx][b]==-1){ //방문하지 않은 경우
                    distance[yy][xx][b] = distance[y][x][b] + 1; // 갱신하고 q에 add
                    q.add(new int[]{yy, xx, b});
                }
                // 여기가 벽일수도 있음
                // 벽 부수는 경우를 여기서 고려함.. (yy, xx)를 여기서 부숨
                if(b==0 && map[yy][xx]==1 && distance[yy][xx][1]==-1){ // 안부쉈었고, 벽이고, 안방문한..
                    // 이 (yy, xx)라는 벽은 이렇게 갱신이 된다 이말이죠
                    distance[yy][xx][1] = distance[y][x][0] + 1; // 벽을 '하나만' 부수니까 distance[y][x][0] + 1을 하는거임 (=> 이렇게 무조건 안부순 경우의 distance값을 넣어준다규)
                    q.add(new int[]{yy, xx, 1}); // 부순 이력에 이 좌표를 추가
                    // 그러면 어쨌거나 벽을 부술 때, 안부순 경우의 distance + 1을 하게됨. 그리고 그게 항상 맞음. 벽을 하나만 부수니까.. 그 이전 값으로 갱신되어야 하는 것이 당연스러움
                }
            }
        }
        return -1;
    }
}

// BFS하면서 한 지점까지 닿으면 그게 무조건 최단경로잖아
// 즉 도달한순간.. 그 첫번째순간.. 그게 바로 최단 경로임
// 이 때 벽을 부수는 경우도 단 하나이게 됨

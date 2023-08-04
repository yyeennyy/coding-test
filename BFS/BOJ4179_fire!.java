import java.util.*;
import java.io.*;

public class Main{
    public static int[][] map;
    public static int R;
    public static int C;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        // J는 9
        // F는 4
        // #는 1
        // .는 0
        map = new int[R][C];
        int[][] jihoonMap = new int[R][C];

        Queue<int[]> fire = new LinkedList<>();
        Queue<int[]> jihoon = new LinkedList<>();
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                char ch = input.charAt(j);
                switch(ch){
                    case '#':
                        map[i][j] = -1; 
                        jihoonMap[i][j] = -1; break;
                    case 'J':
                        map[i][j] = -9; 
                        jihoonMap[i][j] = -9;
                        jihoon.add(new int[]{i, j}); break;
                    case 'F':
                        map[i][j] = -4; 
                        jihoonMap[i][j] = -4;
                        fire.add(new int[]{i, j}); break;
                    case '.':
                        jihoonMap[i][j] = 0;
                        map[i][j] = 0; break;
                }
            }
        }
        // 지훈이는 불을 얼마나 빨리 탈출 가능?
        // 불은 퍼진다!
        // 불 퍼지는 걸 피하는 길로 가야할텐데!
        // - 불은 지훈이를 덮칠 수 있다 (반면 지훈이가 불에 뛰어들면 자살행위지)
        // - 지훈이가 불이 퍼질 위치로 가는 건 안된다 (즉 불을 먼저 퍼뜨린 뒤 지훈이를 퍼뜨리자)
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0}; 
        int x, y;
        int x_, y_;
        int min = 1000*1000;
        while(!jihoon.isEmpty()){
            //불 (큐에 있는 불을 모두 poll해봐야 함)
            ArrayList<int[]> tmp = new ArrayList<>();
            while(!fire.isEmpty()){
                int[] F = fire.poll();  // 아까 불난 자리
                x = F[1]; y = F[0];
                for(int i=0; i<4; i++){
                    x_ = x + dx[i]; 
                    y_ = y + dy[i];
                    if(x_<0||y_<0||y_>=R||x_>=C||map[y_][x_]==-1||map[y_][x_]==-4)
                        continue;
                    map[y_][x_] = -4;
                    tmp.add(new int[]{y_, x_});
                }
            }
            for(int[] f : tmp){
                fire.add(f);
            }

            // 지훈 (큐에 있는 지훈을 모두 poll해봐야 함)
            tmp = new ArrayList<>();
            while(!jihoon.isEmpty()){
                // 지훈 현위치
                int[] J = jihoon.poll();
                int jX = J[1];
                int jY = J[0];
                int val = jihoonMap[jY][jX];
                if(val == -9){
                    val = 0;
                }
                // 현위치 체크도 해야 함(탈출상태인지)
                if(jX==0 || jX==C-1 || jY==0 || jY==R-1){
                    if(jihoonMap[jY][jX] == -9){
                        min = 0;
                        System.out.println(min+1); return;
                    }
                    else if(jihoonMap[jY][jX]!=-4 && jihoonMap[jY][jX]!=-1){
                        if(jihoonMap[jY][jX] < min)  
                            min = jihoonMap[jY][jX];
                    }
                }
                for(int i=0; i<4; i++){
                    x_ = jX + dx[i]; y_ = jY + dy[i];
                    if(x_<0||y_<0||y_>=R||x_>=C||map[y_][x_]==-1||map[y_][x_]==-4)
                        continue;
                    // 갔던 곳을 되돌아가는 수는 없을까? 없다고 가정하고 일단 해보자.
                    if(map[y_][x_]==0){
                        tmp.add(new int[]{y_, x_});
                        jihoonMap[y_][x_] = val + 1;
                        map[y_][x_] = val + 1;
                    }
                }
            }
            for(int[] j : tmp){
                jihoon.add(j);
            }
        }
       
        if(min<1000*1000)
            System.out.println(min + 1);
        else
            System.out.println("IMPOSSIBLE");
    }
}

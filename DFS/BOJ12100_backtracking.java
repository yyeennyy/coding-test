문제를 잘못 이해해서 시간 허비!!
한 이동의 한 줄에서 "한번만"합쳐져야 하는 게 아니었다.
그러니까, 8 8 8 8 이면 16 8 8 이 아니고 16 16 이 될 수 있는 건데
내가 문제 설명 읽는데 꼼꼼하지 못했다.


  

import java.util.*;
import java.io.*;

public class Main {
    public static int N;
    public static int[][] map;
    public static int max = 0;


    // 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록을 출력한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            String[] line = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 이동시켜보기
        backtracking(0, map, new ArrayList<int[][]>());

        System.out.println(max);
    }


    public static void backtracking(int order, int[][] map, ArrayList<int[][]> maps){
        if(order == 5){
            // 현 맵에서 가장 큰 블록은? big
            int big = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j] > big)
                        big = map[i][j];
                }
            }
            if(big > max) max = big;
            return;
        }
        for(int d=1; d<=4; d++){
            int[][] map_ = slideUp(rotateMap(map, d));
            int reset = d == 1 ? 1 : d == 2 ? 4 : d == 3 ? 3 : 2;
            map_ = rotateMap(map_, reset);
            
            ArrayList<int[][]> newMap = new ArrayList<>();
            for(int[][] m : maps){
                newMap.add(cloneMap(m));
            }
            newMap.add(map_);
            backtracking(order+1, map_, newMap);
            
        }
    }
    public static int[][] cloneMap(int[][] map){
        int[][] map_ = new int[N][N];
        for(int i=0; i<N; i++){
            map_[i] = map[i].clone();
        }
        return map_;
    }

    public static int[][] rotateMap(int[][] map, int direction){
        int[][] map_ = new int[N][N];
        switch(direction){
            case 1:
                for(int i=0; i<N; i++){
                    map_[i] = map[i].clone();
                } break;
            case 2:
                for(int i=0; i<N; i++){
                    for(int j=0; j<N; j++){
                        map_[N-1-j][i] = map[i][j];
                    }
                } break;
            case 3:
                for(int i=0; i<N; i++){
                    for(int j=0; j<N; j++){
                        map_[N-1-i][N-1-j] = map[i][j];
                    }
                } break;                
            case 4:
                for(int i=0; i<N; i++){
                    for(int j=0; j<N; j++){
                        map_[j][N-1-i] = map[i][j];
                    }
                } break;
        }
        return map_;
    }

    public static int[][] slideUp(int[][] map){
        // map_
        int[][] map_ = new int[N][N];
        for(int i=0; i<N; i++){
            map_[i] = map[i].clone();
        }

        // merged
        int[][] merged = new int[N][N];
        for(int i=0; i<N; i++){
            merged[i] = map[i].clone();
        }

        // map_위에서 direction으로 slide 동작
        for(int i=1; i<N; i++){
            for(int j=0; j<N; j++){
                if(map_[i][j]==0) continue;
                // 현재 i, j
                // 다음 ii, j
                int ii = i - 1; //<<<<
                while(true){
                    // 갔는데 값이 0이면? 아무 것도 안 하고 ii 넘어감
                    if(ii>=0 && ii<N && map_[ii][j] == 0){
                        ii += -1; //<<<<
                    }
                    // 슬라이드
                    // 이력이있거나, next가 숫자가 다르거나, 인덱스가 벗어나면 +++ next숫자가 이미 합쳐진 숫자거나
                    // 현재 [i, j]를 [ii, j]의 위에 두자.
                    // break하여 다음 i, j로 넘어간다.
                    else if(ii<0 || ii>=N || merged[ii][j] == 1 | map_[ii][j] != map_[i][j]){
                        map_[ii+1][j] = map_[i][j];
                        if(ii+1 != i)
                            map_[i][j] = 0;
                        break;
                    }
                    // 이력이 없고, next 숫자가 같고, 인덱스 내부에 있으면 (else)
                    // [ii, j] += [i, j];
                    // [i, j] = 0
                    // col[j] = 1;
                    // break하여 다음 i, j로 넘어간다. 
                    else{
                        map_[ii][j] += map_[i][j];
                        map_[i][j] = 0;
                        merged[ii][j] = 1;
                        break;
                    }
                }
            }
        }
        return map_;
    }
}

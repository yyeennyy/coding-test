// 회전 좌표에서 꼬였다. 말이 돼? (명색이 나 수학과인데)
// 좌표에서 헤맸다는 것: 집중력을 잃지 말고 논리적으로 단계단계 생각하는 것이 부족했다.

// 또한 
// - 내가 다루는 array의 y좌표 
// - 좌표평면의 y좌표
// 이 두개의 기준이 다른데
// 동일한 기준을 적용해서 푼 것에서 시간을 허비했다.

// 다음부터는 좌표에서 꼬이지 않도록 
// 꼬였던 부분을... 다시 한번 깨끗하게 써봐야겠다.
// (조금 많이 현타가 오네요)


import java.util.*;
import java.io.*;

public class Main {
    public static int N, M, K;
    public static int cnt = 0;
    public static int[][] laptop;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);

        // 노트북
        laptop = new int[N][M];

        for(int k=0; k<K; k++){
            line = br.readLine().split(" ");
            int R = Integer.parseInt(line[0]);
            int C = Integer.parseInt(line[1]);

            // 각 스티커
            int[][] sticker = new int[R][C];
            for(int r=0; r<R; r++){
                line = br.readLine().split(" ");
                for(int c=0; c<C; c++){
                    sticker[r][c] = Integer.parseInt(line[c]);
                }
            }

            // 이 스티커 붙여보기
            tryIt(sticker, R, C);
        }
        // 최종 
        System.out.println(cnt);
    }

    public static void tryIt(int[][] inputSticker, int r, int c){
        for(int rotation=0; rotation<4; rotation++){
            // 회전해본다.
            int[][] sticker = rotateThis(inputSticker, r, c, rotation);
            int rr = sticker.length;
            int cc = sticker[0].length;

            // 왼쪽 위 우선 부착
            for(int n=0; n<N-rr+1; n++){
                for(int m=0; m<M-cc+1; m++){
                    // 스티커 대보기
                    boolean possible = true;
                    for(int i=0; i<rr; i++){
                        for(int j=0; j<cc; j++){
                            if(laptop[n+i][m+j]==1 && sticker[i][j]==1){
                                possible = false;
                                break;
                            }
                        }
                        if(!possible) break;
                    }
                    if(!possible) continue; // 불가능하면 다음 laptop 위치에서 붙이기 도전
                    
                    // 실제로 붙이기
                    for(int i=0; i<rr; i++){
                        for(int j=0; j<cc; j++){
                            // laptop[n+i][m+j] = sticker[i][j]; // 크나큰 실수! 이전 내역을 무용지물시키자나 이건
                            if(sticker[i][j] == 1){
                                laptop[n+i][m+j] = sticker[i][j];
                                cnt++;
                            }
                        }
                    }
                    return;
                }
            }
            // 안되네! 회전해서 재시도
        }
    }

    public static int[][] rotateThis(int[][] sticker, int r, int c, int rotation){
        int[][] newSticker = null;
        switch(rotation){
            case 0:
                return sticker;                
            case 1:
                newSticker = new int[c][r];
                for(int i=0; i<r; i++){
                    for(int j=0; j<c; j++){
                        newSticker[j][r-1-i] = sticker[i][j];
                    }
                }
                break;
            case 2:
                newSticker = new int[r][c];
                
                for(int i=0; i<r; i++){
                    for(int j=0; j<c; j++){
                        newSticker[r-1-i][c-1-j] = sticker[i][j];
                    }
                } 
                break;
            case 3:
                newSticker = new int[c][r];
                for(int i=0; i<r; i++){
                    for(int j=0; j<c; j++){
                        newSticker[c-1-j][i] = sticker[i][j];  // 하.. 예은아 너.. 이걸... 아..
                    }
                } 
                break;
        }
        return newSticker;
    }
}

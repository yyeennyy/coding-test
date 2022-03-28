// fractal triangle 문제
// recursion 문제

/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
이번에 System.out.print()문의 속도 한계를 체감했다. (시간초과)
∴ BufferedWriter로 갈아끼우는 계기가 되었다.

https://splendidlolli.tistory.com/464
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class Main{
  // int maxN = 3 * (int)Math.pow(2, 10);
  // int maxSize = (maxN/3) * 5 + (maxN/3-1);
  public static char[][] matrix = new char[3072][6143];

  public static void drawTriangle(int x, int y, int length){
    if (length == 3){
      // x, y 위치.. easy. 
      matrix[y][x+2] = '*';
      matrix[y+1][x+1] = '*';
      matrix[y+1][x+3] = '*';
      for (int i = 0; i < 5; i++)
        matrix[y+2][x+i] = '*';
      return;
    }
    int half = length / 2;

    drawTriangle(x + half, y, half);
    drawTriangle(x, y + half, half);
    drawTriangle(x + length, y+half, half);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int length = Integer.parseInt(br.readLine());

    for (int i = 0; i < length; i++){
      for (int j = 0; j < 2*length - 1 ; j++)
        matrix[i][j] = ' ';
    }

    drawTriangle(0, 0, length);

    for (int i = 0; i < length; i++){
      for (int j = 0; j < 2*length - 1 ; j++)
        bw.write(matrix[i][j]);
      bw.newLine();
    }
    br.close();
    bw.flush();
    bw.close();
  }
}

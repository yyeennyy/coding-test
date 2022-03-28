// 재귀로 다시 풀었다. 
// 이제 이런 재귀 문제는 자신감이 붙는다.

// 내가 처음 구현했던 방법(재귀X)보다, 재귀를 사용한 방법이 메모리&시간이 훨씬 우수하다. 
// ↓↓↓ (참고) 재귀를 쓰지 않은 구현 방법
// 링크: https://github.com/yyhh314/CodingTest/blob/main/Implementation/point_stars_03_BOJ10994.java


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main{
  // 1 <= input <= 100
  // max_length : (2*100-1)*2-1 => 397
  public static char[][] matrix = new char[397][397];
  public static int maxLength = 0;

  public static void recurBox(int x, int y, int step){
    if (step == 1){
      matrix[y][x] = '*';
      return;
    }
    /*
    input : 3
    step3 : line length : 9
    step2 : line length : 5
    step1 : line length : 1
    generalization : step k, line length is (2*k-1)*2-1 
    */
    int length = (2 * step - 1) * 2 - 1;
    
    for (int i = 0; i < length; i++){
      // upper, bottom line
      matrix[y][x+i] = '*';
      matrix[y+length-1][x+i] = '*';
    }
    for (int i = 0; i < length-1; i++){
      // both side line
      matrix[y+i][x] = '*';
      matrix[y+i][x+length-1] = '*';
    }
    
    recurBox(x+2, y+2, step - 1);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int n = Integer.parseInt(br.readLine());
    maxLength = (2 * n - 1) * 2 - 1;

    for(int i = 0; i < maxLength ; i++){
      for(int j = 0; j < maxLength; j++)
        matrix[i][j] = ' ';
    }
    
    recurBox(0, 0, n);

    for(int i = 0; i < maxLength; i++){
      for(int j = 0; j < maxLength; j++)
        bw.write(matrix[i][j]);
      bw.newLine();
    }
    
    br.close();
    bw.flush();
    bw.close();
  }
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡ[ 나의 개선점 ]ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
◆ 처음부터 깔끔하게 구현하지 못함. 3번이나 갈아치웠다.
   어제 구현해보기 시작했는데 오늘 저녁에서야 완성했다.
   조금만 더 하면 될 것 같은 마음에 손을 떼지 못했던 문제..
   이렇게 결국 완성은 해냈다.
   리뷰는 일단 블로그에 하겠다.
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.*;


public class Main {

  public static char[][] map;
  public static int questionLine, k, n;

  public static char[] afterReach(char[] origin, char[] after, int idx) {    
    // reach
    int j = idx;
    char ch = origin[idx];
    for(int i = 0; i < n; i++){
      if ( i == questionLine ){
        after[j] = ch;
        break;
      }
      if (map[i][j] == '-')
        j++;
      else if (j > 0 && map[i][j-1] == '-')
        j--;
      else 
        continue;
    }

    // list after
    return after;
  }

  public static char[] afterBackReach(char[] origin, char[] after, int idx) {
    // back reach
    int j = idx;
    char ch = origin[idx];
    for(int i = n-1; i >= 0; i--){
      if ( i == questionLine ){
        after[j] = ch;
        break;
      }
       if (map[i][j] == '-')
        j++;
      else if (j > 0 && map[i][j-1] == '-')
        j--;
      else 
        continue;
    }
    
    // list after
    return after;
  }

  public static void main(String[] args) throws IOException{
    // treatment of input
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    k = Integer.parseInt(br.readLine());
    n = Integer.parseInt(br.readLine());
    String target_str = br.readLine();
    char[] target = new char[k];
    for(int i = 0; i < k; i++){
      target[i] = target_str.charAt(i);
    }
    map = new char[n][k];
    for(int i = 0; i < n; i++){
      String str_ = br.readLine();
      if (str_.charAt(0) == '?') questionLine = i;
      for(int j = 0; j < k-1; j++){
        map[i][j] = str_.charAt(j);
      }
    }

    // list of man
    char[] man = new char[k];
    for (int i = 0; i < k; i++){
      man[i] = (char) (65 + i);
    }


    char[] after = new char[k];
    char[] after2 = new char[k];
    char[] midList = new char[k];
    char[] midList2 = new char[k];
    for(int i = 0; i < k; i++){
      midList = afterReach(man, after, i);
      midList2 = afterBackReach(target, after2, i);
    }

    char[] result = new char[k-1];

    boolean flag = false;
    for(int i = 0; i < k-1; i++){
      if(flag){
        result[i] = '*';
        flag = false;
        continue;
      }
      if (midList[i] == midList2[i]) {
        result[i] = '*';
      } else if (midList[i] == midList2[i+1]
                && midList[i+1] == midList2[i]){
        result[i] = '-';
        flag = true;
        continue;
      }
    }

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    boolean notDetermined = false;
    for(int i = 0; i < k-1; i++){
      if (result[i] == '\0')
        notDetermined = true;
    }

    if (notDetermined == true){
      for(int i = 0; i < k-1; i++)
        bw.write("x");
    }
    if (notDetermined == false){
      for(int i = 0; i < k-1; i++)
        bw.write(result[i]);
    }

    bw.flush();
    bw.close();
    br.close();
    
    
  }
}

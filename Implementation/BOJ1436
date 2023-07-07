>> 영화감독 숌 풀기
숌이 만든 N번째 영화의 제목에 들어간 수를 출력

>> 후기: 
- 규칙 찾기로 시도했으나 정리가 깨끗하고 빠르게 되지 않아서 시간을 소모했다.
: 실수원인 cnt변수는 몇번째로 큰지 오름차순의 개수 변수임.
: 반면 (실수 인지 후 추가한) increase변수는 현재 따져야 할 대상인 숫자임. increase변수를 따로 두었어야 했는데, cnt변수를 increase변수처럼 사용하고 있었음.
: 왜 그랬을까? 깔끔하지 못하게 생각하다가 꼬여버렸다.



import java.util.*;

// 숌이 만든 N번째 영화의 제목에 들어간 수를 출력

public class Main {
  static int increase = 0;
  static int cnt = 0;
  static int N = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    int answer;
    while (true) {
      if ((answer = check(increase)) == -1) {
        if (cnt == N) {
          System.out.println(Integer.parseInt(String.valueOf(increase) + "666"));
          break;
        }
      } else {
        System.out.println(answer);
        break;
      }
      increase++;
    }
    sc.close();
  }

  static int check(int i) {
    String str = String.valueOf(i) + "666";
    // 666이 나온 위치 찾기
    int here = str.indexOf("666");
    int right = str.length() - 3;
    // 666이 나온 위치가 i + 666 의 위치가 아니라면?
    if (here != right) {
      // 666이 나온 위치 이후를 다 ___... => n개 masking
      int maskCnt = str.length() - here - 3;
      for (int k = 0; k < Math.pow(10, maskCnt); k++) {
        cnt++;
        if (cnt == N) {
          String format = "%0" + maskCnt + "d";
          String preStr = str.substring(0, str.length() - maskCnt);
          return Integer.parseInt(preStr + String.format(format, k));
        }
      }
    } else {
      cnt++;
    }
    return -1;
  }
}

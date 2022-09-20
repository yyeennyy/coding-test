// 일단 난 이렇게 했는데...
// 훨씬 간단한 방법이 존재한다. 맨밑에서 보쟛


import java.util.*;

public class Main{
    static String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

  // ▼ return boolean
  // String.contains(String)
  // String.matches(regex)
  // ▼ return index
  // String.indexOf(String, startIndex)
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    int cnt = 0;
    for(String croa : croatia){
      int idx = input.indexOf(croa);

      while(idx != -1){
        cnt ++;
        String replaceStr = "";
        for(int i=0; i<croa.length(); i++){
          replaceStr += "ㅇ";
        }
        input = input.replaceFirst(croa, replaceStr);
        idx = input.indexOf(croa, idx+croa.length());
      }
    }
    for(int i=0; i<input.length(); i++){
      if (input.charAt(i) != 'ㅇ'){
        cnt++;
      }
    }
    System.out.println(cnt); sc.close();
  }
}

// ▼ 일단 replace는 찾은 것을 모두 바꾼다
// String.replace(기존String, 변경String)
// String.replaceAll(regex, 변경String)
// ▼ 첨으로 찾은 것만 바꾸고 싶으면 따로 있다.
// String.replaceFirst(기존String, 변경String)



//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

// 훨씬 간단한 방법... 맘에든닷
// repalceAll(정규식,길이1인문자)를 활용하여 특정 패턴(문자열)수를 세는 거다.
// String.
String output = str.replaceAll("c=|c-|dz=|d-|lj|nj|s=|z=", "a");
System.out.println(output.length());

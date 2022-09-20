// 난 굳이 이렇게 했다.
// 원래 아스키 넘버를 int로 활용하려고 했는데
// ABC가 같은 값, DFG가 같은같.. 이런 식이라서 그냥 case by case로 했다.
// 아래와 같이..

  static int charToTime(char ch){
    if (65 <= ch & ch <= 67 ){
        return 3;
    } else if (68 <= ch & ch <= 70){
      return 4;
    } else if (71 <= ch & ch <= 73){
      return 5;
    } else if (74 <= ch & ch <= 76){
      return 6;
    } else if (77 <= ch & ch <= 79){
      return 7;
    } else if (80 <= ch & ch <= 83){
      return 8;
    } else if (84 <= ch & ch <= 86){
      return 9;
    } else if (87 <= ch & ch <= 90){
      return 10;
    } else {
      return 0;
    }
  }


// 근데 이런 방법.. 좋다.

// 어차피.. A~Z까지 26개밖에 안되고 해당하는 숫자가 명확하니까..
// A~Z에 해당하는 값을 쭈루룩 배열에 저장해두는 것이다.
char b[]={3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,8,9,9,9,10,10,10,10}; //★

int cnt = 0;
for(int i=0; i<str.length();i++){
  // cnt 셀 때 그 char에 대한 배열 값 갖다 쓰면 되니까..
  cnt += b[str.charAt(i)-65];
}

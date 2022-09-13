// BOJ 6364872


import java.util.*;

/**
 * 난 첨에 이렇게 했는데
 * 인덱스를 활용하는 쪽이 훨씬 빠르고 깔끔할 것이다.
 * 
 * 일단 내 생각 과정
 * 1. 셀프 넘버가 아니라는 것을 알기 위해서는.. 더해봐야 알 것이다.
 * 2. 전체집합 - 셀프 넘버가 아닌 것 = 셀프넘버
 */

public class Main {
    static boolean findElement(List<Integer> list, int i){
        for (int element : list){
            if (element == i){
                return true;
            }
        }
        return false;
    }

    static List<Integer> makeNotSelfNumber(){
        List<Integer> notSelfArr = new ArrayList<>();
        // 1 ~ 10000
//        1 -> 1+1, memo
//        2 -> 2+2, memo
        for(int i=0; i<=10000; i++){
            int sum = 0;
            // 자신
            sum += i;
            // 각 자리
            String str = String.valueOf(i);
            int len = str.length();
            for(int j = 0; j < len; j++){
                sum += str.charAt(j) - '0';
            }
            if (findElement(notSelfArr, sum)){
                continue;
            }
            notSelfArr.add(sum);
        }
        return notSelfArr;
    }

    public static void main(String[] args){
        // 셀프 넘버가 아닌 리스트
        List<Integer> notSelf = makeNotSelfNumber();

        for(int i = 0; i<=10000; i++){
            if (!findElement(notSelf, i)) {
                System.out.println(i);
            }
        }
    }
}


/**
 * 앞으로는 이런 경우에 대해서
 * 인덱스를 사용하는 방법을 바로 떠올릴 수 있겠다.
 * 
 * 다음과 같다.
 */


public class Main {

    public static void main(String[] args){
        boolean[] notSelfNumber = new boolean[10001];

        int num; int i;
        for(i=1; i<10001; i++){
            // 내가 알아야 할 것:
            // 몇의 자리수를 가졌던 간에, 만약 i % 1000000을 하더라도 해당안하면 0이나까 상관 없잖아.. 바보..
            // 좀전에 익힌 거: int 나누기하면 int라는 그 기본...을...다음부터는 절대 망설이지X
            if ((num = i + i%10 + i/10%10 + i/100%10 + i/1000%10 + i/10000%10) <= 10000) {
                notSelfNumber[num] = true;
            }
        }
        for(i=1; i<10001; i++){
            if(notSelfNumber[i] == false)
                System.out.println(i);
        }
    }
}




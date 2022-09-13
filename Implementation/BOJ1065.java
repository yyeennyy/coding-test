/**
* 난 이렇게 했다.. 그냥 범위 특징별로 케이스 나눠서..
* 숏코딩 보니까 같은 맥락으로 많이 더 간결하게 작성가능했다.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int hansu = 0;
        if (N < 100) {
            hansu += N;
        } else if (N >= 100) {
            hansu += 99;
        }

        // 1000보다 작거나 같은 & 1000은 한수가 아니다
        // 등차수열.. 오름차거나 내림차거나
        if (N > 100) {
            if (N >= 1000) N = 999;
            for (int i = 101; i <= N; i++) {
                // 내림차
                if ((i / 10 % 10 - i % 10) == (i / 100 % 10 - i / 10 % 10)) {
                    hansu++;
                    continue;
                }
                // 오름차
                if ((i % 10 - i / 10 % 10) == (i / 10 % 10 - i / 100 % 10)) {
                    hansu++;
                    continue;
                }
            }
        }
        System.out.println(hansu);

    }
}

/**
* 같은 방법이긴 하다지만...
* 난 뻘짓+코드낭비를 좀 했다면 아래는 간결하다.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int hansu = 0;
        // 이렇게 그냥 i를 N까지 돌면서 범위별로 조건 나눠두 되잖아.. 생각난거 바로하지말고 10초쯤 더 정리하자
        for(int i = 1; i <= N; i++){
            if(i < 100) hansu++;
            else{
                // 어차피 최대 몇백단위라서 i/100%10 요따위로 안해도 된다.
                // 그리고 당연히 오름차, 내림차 생각 안해도 됨.. 왜그랬어 바보.. ㅠ
                if((i/100 - i/10%10) == (i/10%10 - i%10)) hansu++;
            }
        }
        System.out.println(hansu);
    }
}






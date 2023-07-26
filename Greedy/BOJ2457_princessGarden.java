import java.util.*;
import java.io.*;

public class Main{
    public static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static ArrayList<int[]> flowers;
    public static int N;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        flowers = new ArrayList<>();
        for(int i=0; i<N; i++){
            String[] line = sc.nextLine().split(" ");
            int[] nums = new int[4];
            for(int j=0; j<4; j++){
                nums[j] = Integer.parseInt(line[j]);
            }
            flowers.add(i, nums);
        }

        // end의 내림차순으로 정렬
        Collections.sort(flowers, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                int st1 = toIndex(a[0], a[1]);
                int st2 = toIndex(b[0], b[1]);
                int end1 = toIndex(a[2], a[3]);
                int end2 = toIndex(b[2], b[3]);
                return end1 < end2 ? 1
                 : end1 == end2 ? (st1 > st2 ? 1 : st1 < st2 ? -1 : 0) : -1;
            }
        });

        int cnt = 0;
        int endM = 3, endD = 1;
        boolean flag = false;
        // 항상 end의 앞날은 커버되는 상태이다.
        // 그러므로 종료조건은, 현재꽃이 11월 31일을 커버하는지만 보면 된다.
        while(true){
            flag = false;
            for(int[] flower : flowers){
                // 현재 꽃
                int m1 = flower[0]; int d1 = flower[1];
                int m2 = flower[2]; int d2 = flower[3];
                // 현재 꽃이 end일을 커버하는가?
                if(isCovered(endM, endD, m1, d1, m2, d2)){
                    cnt++;
                    endM = m2; endD = d2;
                    flag = true;
                    // 종료조건
                    if(toIndex(endM, endD) >= toIndex(12, 1)){
                        System.out.println(cnt);
                        return;
                    }
                    // 지울거 지워주기: end일 이전에 끝나는 것들을 삭제
                    ArrayList<int[]> tmp = new ArrayList<>();
                    for(int[] f : flowers){
                        int a = f[2];
                        int b = f[3];
                        if(toIndex(a, b) <= toIndex(endM, endD)){
                            tmp.add(f);
                        }
                    }
                    for(int[] f : tmp){
                        flowers.remove(f);
                    }
                }
            }
            if(!flag){
                System.out.println(0);
                return;
            }
            if(flowers.size() == 0){
                System.out.println(0);
                return;
            }
        }
    }
    public static boolean isCovered(int month, int day, int m1, int d1, int m2, int d2){
        if(toIndex(m1, d1) <= toIndex(month, day) && toIndex(month, day) < toIndex(m2, d2)){
            return true;
        }
        return false;
    }
    public static int toIndex(int m, int d){
        return m*100 + d;
    }
}



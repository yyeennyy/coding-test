import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
        }
        // 줄 어떻게 세워야 1~k 부분합의 합들이 최소? 시간 작은순!
        Arrays.sort(arr);
        int sum = 0;
        int prev = 0;
        for(int i=0; i<N; i++){
            sum += prev + arr[i];
            prev = prev + arr[i];
        }
        System.out.println(sum);

        sc.close();
    }
}



import java.util.*;
import java.io.*;

public class Main {

    public static int stone;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        stone = sc.nextInt();

        // 상 -> 찬 -> 상 -> 찬 ...
        // 앗 아래 패턴 발견
        // 돌이 8개 남았을 때 반드시 짐
        // 돌이 7개 남았을 떄 반드시 이김
        // 돌이 6개 남았을 때 무조건 짐
        // 돌이 5개 남았을 때 반드시 이김
        // 돌이 4개 남았을 때는 반드시 짐
        // 돌이 3개, 1개 남으면 반드시 이김
        // 돌이 2개 남았으면 반드시 짐
        // 즉 input stone이 짝수개면, 먼저 시작한 상근이가 무조건 짐

        System.out.println(stone % 2 == 0 ? "CY" : "SK");
    }
}
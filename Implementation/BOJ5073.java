import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String[] input = sc.nextLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            if (a == 0 && b == 0 && c == 0) {
                break;
            }
            String res = "";

            if (!(a + b > c && b + c > a && c + a > b)) {
                res = "Invalid";
            } else if (a == b && b == c && c == a) {
                res = "Equilateral";
            } else if ((a == b && b != c) || (b == c && c != a) || (c == a && a != b)) {
                res = "Isosceles";
            } else {
                res = "Scalene";
            }

            System.out.println(res);
        }
    }
}
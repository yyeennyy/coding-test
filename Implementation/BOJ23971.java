import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = sc.nextLine().split(" ");
        int[] input = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
        // 1,1 자리는 항상 앉아있다
        System.out.println(((input[0]-1) / (input[2]+1) + 1)
                * ((input[1]-1) / (input[3]+1) + 1));
    }
}
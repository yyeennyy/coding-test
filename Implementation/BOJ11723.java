import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Set<Integer> set = new HashSet();
        Set<Integer> initSet = new HashSet<>();
        for(int i=1; i<=20; i++) {
            initSet.add(i);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            String operator = input[0];
            if (input.length == 2) {
                int operand = Integer.parseInt(input[1]);

                switch(operator) {
                    case "add":
                        set.add(operand); break;
                    case "remove":
                        set.remove(operand); break;
                    case "check":
                        sb.append(set.contains(operand) ? 1 : 0).append("\n"); break;
                    case "toggle":
                        if (set.contains(operand)) {
                            set.remove(operand);
                        } else {
                            set.add(operand);
                        } break;
                }
            }
            // not operand
            if (operator.equals("all")) {
                set.addAll(initSet);
            }
            if (operator.equals("empty")) {
                set.clear();
            }
        }
        System.out.println(sb);
    }
}
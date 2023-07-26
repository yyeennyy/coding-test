import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        int ans = 0, tmp = 0;
        int sign = 1;
        for(int i=0; i<line.length(); i++){
            char ch = line.charAt(i);
            if(ch=='+' || ch=='-'){
                ans += tmp * sign;
                if(ch=='-') sign = -1;
                tmp = 0;
            }
            else {
                tmp *= 10;
                tmp += ch - '0';
            }
        }
        ans += tmp * sign;
        System.out.println(ans);
    }
}

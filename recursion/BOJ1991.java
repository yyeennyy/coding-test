import java.util.*;
import java.io.*;

public class Main {
    public static Map<String, String[]> map = new HashMap<>();
    public static ArrayList<String> preorder = new ArrayList<>();
    public static ArrayList<String> inorder = new ArrayList<>();
    public static ArrayList<String> postorder = new ArrayList<>();

    public static int blue = 0;
    public static int white = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            String[] line = br.readLine().split(" ");
            map.put(line[0], new String[]{line[1], line[2]});
        }

        // recur
        preOrder("A");
        inOrder("A");
        postOrder("A");

        // print
        for(int i=0; i<preorder.size(); i++)
            bw.write(preorder.get(i));
        bw.newLine();
        for(int i=0; i<inorder.size(); i++)
            bw.write(inorder.get(i));
        bw.newLine();
        for(int i=0; i<postorder.size(); i++)
            bw.write(postorder.get(i));
        bw.flush();
    } 

    public static void preOrder(String root){
        if(root.equals(".")) return;
        String[] now = map.get(root);
        String left = now[0];
        String right = now[1];

        preorder.add(root);
        preOrder(left);
        preOrder(right);
    }

    public static void inOrder(String root){
        if(root.equals(".")) return;
        String[] now = map.get(root);
        String left = now[0];
        String right = now[1];

        inOrder(left);
        inorder.add(root);
        inOrder(right);
    }

    public static void postOrder(String root){
        if(root.equals(".")) return;
        String[] now = map.get(root);
        String left = now[0];
        String right = now[1];

        postOrder(left);
        postOrder(right);
        postorder.add(root);
    }

}

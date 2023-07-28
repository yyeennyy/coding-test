//간단한 deq 문제

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            String[] cmd = br.readLine().split(" ");
            if(cmd.length == 1){
                switch(cmd[0]){
                    case "back":
                        System.out.println(back()); break;
                    case "front":
                        System.out.println(front()); break;
                    case "pop_front":
                        System.out.println(pop_front()); break;
                    case "pop_back":
                        System.out.println(pop_back()); break;
                    case "empty":
                        System.out.println(empty()); break;
                    case "size":
                        System.out.println(size()); break;
                }
            } else {
                int number = Integer.parseInt(cmd[1]);
                switch(cmd[0]){
                    case "push_front":
                        push_front(number); break;
                    case "push_back":
                        push_back(number); break;
                }
            }
        }
    }
    public static int[] deq = new int[30000];
    public static int head = 15000;
    public static int tail = 15000;
    public static int size = 0;

    public static int back(){
        if(empty()==1) return -1;
        return deq[tail-1];
    }   
    public static int front(){
        if(empty()==1) return -1;
        return deq[head];
    }
    public static int empty(){
        return head == tail ? 1 : 0;
    }
    public static int size(){
        return size;
    }   
    public static int pop_front(){
        if(empty()==1) return -1;
        size--;
        return deq[head++];
    }
    public static int pop_back(){
        if(empty()==1) return -1;
        size--;
        return deq[--tail];
    }
    public static void push_front(int n){
        deq[--head] = n;
        size++;
    }
    public static void push_back(int n){
        deq[tail++] = n;
        size++;
    }
}

import java.io.*;
import java.util.*;

public class boj_9019_Re {

    static class Node{
        int str;
        String cmd;

        public Node(int str, String cmd){
            this.str = str;
            this.cmd = cmd;
        }
    }
    static String ans = "";
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            Queue<Node> q = new LinkedList<>();
            q.add(new Node(start, ""));

            boolean[] visited = new boolean[10000];

            while(!q.isEmpty()){
                Node cur = q.poll();

                if(cur.str == target){
                    System.out.println(cur.cmd);
                    break;
                }

                if(!visited[D(cur.str)]){
                    q.add(new Node(D(cur.str) , cur.cmd + "D"));
                    visited[D(cur.str)] = true;
                }
                if(!visited[S(cur.str)]){
                    q.add(new Node(S(cur.str) , cur.cmd + "S"));
                    visited[S(cur.str)] = true;
                }
                if(!visited[L(cur.str)]){
                    q.add(new Node(L(cur.str) , cur.cmd + "L"));
                    visited[L(cur.str)] = true;
                }
                if(!visited[R(cur.str)]){
                    q.add(new Node(R(cur.str) , cur.cmd + "R"));
                    visited[R(cur.str)] = true;
                }
            }

            System.out.println(ans);
        }

    }

    static int D(int n){
        return (n*2) % 10000;
    }

    static int S(int n){
        return (n) != 0 ? n - 1 : 9999;
    }

    static int L(int n){
        return n % 1000 * 10 + n / 1000;
    }

    static int R(int n){
        return n % 10 * 1000 + n / 10;
    }

}

/*
 * note : bfs
 * performance : 3980ms
 * title : DSLR
 * etc : 0~9999까지 숫자가 가능하니 이미 지난 적이 있는 숫자는 visited[] true로 
 * 
 */
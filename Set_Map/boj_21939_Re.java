import java.io.*;
import java.util.*;

public class boj_21939_Re {

    static class Node implements Comparable<Node>{
        int level;
        int numb;

        public Node(int level, int numb){
            this.level = level;
            this.numb = numb;
        }

        public int compareTo(Node o){
            return this.level == o.level ? this.numb - o.numb : this.level - o.level;
        }
    }
    static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        TreeSet<Node> tSet = new TreeSet<>();
        HashMap<Integer, Integer> hMap = new HashMap<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int numb = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            tSet.add(new Node(level, numb));
            hMap.put(numb, level);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("add")){
                int numb = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                tSet.add(new Node(level, numb));
                hMap.put(numb, level);
            }
            else if(cmd.equals("recommend")){
                int x = Integer.parseInt(st.nextToken());
                if(x == 1){
                    System.out.println(tSet.last().numb);
                }
                else{
                    System.out.println(tSet.first().numb);
                }
            }
            else{
                int n = Integer.parseInt(st.nextToken());
                int l = hMap.get(n);
                tSet.remove(new Node(l, n));
            }
        }

    }

}

/*
 * note : treeSet, hashMap
 * performance : 604ms
 * title : 문제추천시스템 v1
 * 
 */

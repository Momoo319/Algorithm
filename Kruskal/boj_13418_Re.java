import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_13418_Re {

    static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] p;
    static int N, M;
    static Node[] nodelist;
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        N++;
        M++;
        nodelist = new Node[M];
        make();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodelist[i] = new Node(from, to, cost);
        }

        Arrays.sort(nodelist);

        int cntOne = 0;
        int cntZero = 0;
        int cnt = 0;
        for(int i = 0; i < M; i++) {
            if (union(nodelist[i].from, nodelist[i].to)) {
                if (nodelist[i].cost == 1) {
                    cntOne++;
                } else cntZero++;
                cnt++;
                if(cnt == N - 1)break;
            }
        }
        int ans1 = cntZero * cntZero;

        cnt = 0;
        Arrays.sort(nodelist, Collections.reverseOrder());
        make();
         cntOne = 0;
         cntZero = 0;
         for(int i = 0; i < M; i++){
             if(union(nodelist[i].from, nodelist[i].to)){
                 if(nodelist[i].cost == 1){
                     cntOne++;
                 }
                 else cntZero++;
                 cnt++;
                 if(cnt == N - 1)break;
             }
         }
         int ans2 = cntZero * cntZero;
        System.out.println(Math.abs(ans1 - ans2));
     }

    static void make(){
         p = new int[N + 1];
         for(int i = 0; i < N + 1; i++){
             p[i] = -1;
         }
    }

    static int find(int x){
         if(p[x] < 0)return x;
         return p[x] = find(p[x]);
    }

    static boolean union(int x, int y){
         x = find(x);
         y = find(y);
         if(x == y)return false;

         if(p[x] > p[y]){
             int temp = x;
             x = y;
             y = temp;
         }

         if(p[x] == p[y])p[x]--;

         p[y] = x;

         return true;
    }
}

/*
* note : kruskal
* performance : 976ms
* title : 학교 탐방하기
 */
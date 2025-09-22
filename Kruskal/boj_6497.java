import java.io.*;
import java.util.*;

public class boj_6497 {

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
    static int m, n;
    static int[] p;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st  = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if(m == 0 && n == 0)return;

            make();

            Node[] alist = new Node[n];

            int sum = 0;
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                sum += z;
                alist[i] = new Node(x, y, z);
            }

            Arrays.sort(alist);

            int cnt = 0; // 연결된 길 개수
            int sumCnt = 0; // 연결된 길의 누적 cost
            for(int i = 0; i < n; i++){
                if(union(alist[i].from, alist[i].to)){
                    cnt++;
                    sumCnt += alist[i].cost;
                }

                if(cnt == m-1)break;
            }

            System.out.println(sum - sumCnt);
        }

    }

    static void make(){
        p = new int[m+1];
        Arrays.fill(p, -1);
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
 * performance : 716ms
 * title : 전력난
 * 
 */
import java.io.*;
import java.util.*;

public class boj_4386_Re {

    static class Node implements Comparable<Node>{
        int to;
        int from;
        double cost;

        public Node(int to, int from, double cost){
            this.to = to;
            this.from = from;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Double.compare(this.cost, o.cost);
        }
    }
    static int N;
    static int[] p;
    static Node[] nodelist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        p = new int[N];
        Arrays.fill(p, -1);

        nodelist = new Node[(N * (N - 1)) / 2];

        double[] xlist = new double[N];
        double[] ylist = new double[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            xlist[i] = x;
            ylist[i] = y;
        }

        int idx = 0;
        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                double dist = Math.sqrt(Math.pow(xlist[i] - xlist[j], 2) + Math.pow(ylist[i] - ylist[j],2));

                nodelist[idx++] = new Node(i, j, dist);
            }
        }

        Arrays.sort(nodelist);

        int cnt = 0;
        double ans = 0;
        for(Node cur : nodelist){
            if(union(cur.to, cur.from)){
                cnt++;
                ans += cur.cost;
            }

            if(cnt == (N * (N - 1)) / 2){
                break;
            }
        }

        System.out.println(String.format("%.2f", ans));
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
 * note : Kruskal
 * performance : 116ms
 * title : 별자리 만들기
 * etc : 우선 (x, y)를 입력 받으며 저장한 후 이중 for문으로 모든 간선의 dist를 확인하며 nodelist 배열 채움
 *      nodelist의 크기 : (N (N - 1)) / 2 -> 무방향(nC2)
 */
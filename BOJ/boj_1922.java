import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1922 {

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

        make();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        nodelist = new Node[M];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodelist[i] = new Node(from, to, cost);
        }

        Arrays.sort(nodelist);

        int cnt = 0;
        int ans = 0;

        for(int i = 0; i < M; i++){
            if(union(nodelist[i].from, nodelist[i].to)){
                ans += nodelist[i].cost;
                cnt++;
                if(cnt == N-1)break;
            }
        }

        System.out.println(ans);
        return;
    }

    static void make(){
        p = new int[N+1];
        for(int i = 0; i < N+1; i++){
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

        if(p[x] == p[y])p[y]--;

        p[y] = x;

        return true;
    }

}

/*
 * note : kruskal
 * performance : 560ms
 * title : 네트워크 연결
 */
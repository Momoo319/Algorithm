import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1774 {

    static class Node implements Comparable<Node>{
        int from;
        int to;
        double cost;

        public Node(int from, int to, double cost){
            this.from = from; // 출발 우주신 입력 받은 순서
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Double.compare(this.cost, o.cost);
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

        int[][] arr = new int[N][2];
        nodelist = new Node[fabo(N)];
        double len = 0;
        int cnt = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;

            for(int j = 0; j < i; j++){
                len = Math.sqrt(Math.pow(arr[j][0] - x, 2) + Math.pow(arr[j][1] - y, 2));
                nodelist[cnt] = new Node(i+1, j+1, len);
                cnt++;
            }
        }

        make();

        int rootCnt = 0;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int g1 = Integer.parseInt(st.nextToken());
            int g2 = Integer.parseInt(st.nextToken());
            if(union(g1, g2))rootCnt++;
        }

        Arrays.sort(nodelist);

        double ans = 0;
        for(int i = 0; i < nodelist.length; i++){
            if(union(nodelist[i].from, nodelist[i].to)){
                ans += nodelist[i].cost;
                rootCnt++;

                if(rootCnt == N - 1)break;
            }
        }

        System.out.println(String.format("%.2f", ans));
     }

    static void make(){
        p = new int[N+1];
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

    static int fabo(int x){
        if(x == 2){
            return x-1;
        }
        else{
            return x - 1 + fabo(x-1);
        }
    }
}

/*
* note : kruskal
* performance : 1460ms
* title : 우주신과의 교감
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1261_Re {

    static class Node implements Comparable<Node>{
        int idx;
        int cost;

        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
    static int[][] map;
    static ArrayList<Node>[] graph;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for(int j=0; j <M; j++) {
                int num = Integer.parseInt(line[j]);
                map[i][j] = num;
            }
        }

        graph = new ArrayList[N*M + 1];
        for(int i = 0; i < N*M + 1; i++){
            graph[i] = new ArrayList<> ();
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                for(int dir = 0; dir < 4; dir++){
                    int nx = j + dx[dir];
                    int ny = i + dy[dir];

                    if(nx < 0 || nx == M || ny < 0 || ny == N)continue;

                    graph[i*M + j + 1].add(new Node(ny*M + nx+1, map[i][j]));
                }
            }
        }

        dijkstra(N, M, 1, N*M);

     }

     static void dijkstra(int N, int M, int start, int end){
        boolean[] visit = new boolean[N*M + 1];
        int[] dist = new int[N*M + 1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            int now = pq.poll().idx;

            if(visit[now])continue;
            visit[now] = true;

            for(Node next : graph[now]){
                if(dist[next.idx] > dist[now] + next.cost){
                    dist[next.idx] = dist[now] + next.cost;

                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        if(dist[end] == INF){
            System.out.println(0);
        }
        else{
            System.out.println(dist[end]);
        }
     }
}

/*
* note : dijkstra
* performance : 192ms
* title : 알고스팟
 */
import java.io.*;
import java.util.*;

public class boj_18352 {

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

    static int N, M, K, X, cnt;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, 1));
        }

        cnt = 0;
        dijkstra(X);

    }

    static void dijkstra(int start){
        int[] dist = new int[N+1];
        boolean[] visit = new boolean[N+1];

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

        for(int i = 1; i < N+1; i++){
            if(dist[i] == K){
                System.out.println(i);
                cnt++;
            }
        }
        if(cnt == 0)System.out.println(-1);
    }

}

/*
 * note : dijkstra
 * performance : 1344ms
 * title : 특정 거리의 도시 찾기
 */
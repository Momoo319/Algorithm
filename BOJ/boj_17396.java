import java.io.*;
import java.util.*;

public class boj_17396 {

    static class Node implements Comparable<Node>{
        int idx;
        long cost;

        public Node(int idx, long cost){
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Long.compare(this.cost, o.cost);
        }
    }

    static int N, M;
    static int[] visible;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }

        visible = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            visible[i] = Integer.parseInt(st.nextToken());
        }
        visible[N] = 0;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }
        dijkstra(0);
    }

    static void dijkstra(int start){
        long [] dist = new long[N + 1];
        boolean[] visit = new boolean[N + 1];

        long INF = 9999900001L; //
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            int now = pq.poll().idx;
            if(visit[now] == true || visible[now] == 1)continue;
            visit[now] = true;

            for(Node next : graph[now]){
                if(dist[next.idx] > dist[now] + next.cost){
                    dist[next.idx] = dist[now] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        if(dist[N-1] == INF){
            System.out.println(-1);
        }
        else{
            System.out.println(dist[N-1]);
        }

    }


}

/*
 * note : dijkstra
 * performance : 1012ms
 * title : 백도어
 * etc : INF 범위 체크!!
 * 
 */
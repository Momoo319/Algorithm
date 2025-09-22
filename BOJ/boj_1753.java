import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1753 {

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

    static int V, E;
    static ArrayList<Node> [] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());//정점 개수
        E = Integer.parseInt(st.nextToken());//간선 개수

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());//시작 정점

        graph = new ArrayList[V + 1];

        for(int i = 0; i <= V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        dijkstra(V, start);
    }

    static void dijkstra(int V, int start){
        boolean[] check = new boolean[V+1];
        int[] dist = new int[V+1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            int now = pq.poll().idx;

            if(check[now])continue;
            check[now] = true;

            for(Node next : graph[now]){
                if(dist[next.idx] > dist[now] + next.cost){
                    dist[next.idx] = dist[now] + next.cost;

                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        for(int i = 1; i < V + 1; i++){
            if(dist[i] == INF)System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}

/*
 * note : dijkstra 기본
 * performance : 908ms
 * title : 최단 경로
 */
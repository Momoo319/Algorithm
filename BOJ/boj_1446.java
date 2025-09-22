import java.io.*;
import java.util.*;

public class boj_1446 {

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

    static int N, D;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 지름길의 개수
        D = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        graph = new ArrayList[D+1];
        for(int i = 0; i < D; i++){
            graph[i] = new ArrayList<>();
            graph[i].add(new Node(i+1, 1));
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(to > D)continue;
            graph[from].add(new Node(to, cost));
        }

        dijkstra(0, D);
    }

    static void dijkstra(int start, int end){
        int[] dist = new int[D + 1];
        boolean[] visit = new boolean[D + 1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visit[now.idx])continue;
            visit[now.idx] = true;

            if(now.idx == D)break;

            for(Node next : graph[now.idx]){
                if(dist[next.idx] > dist[now.idx] + next.cost){
                    dist[next.idx] = dist[now.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        System.out.println(dist[end]);
    }

}

/*
 * note : dijkstra
 * performance : 108ms
 * title : 지름길
 * 
 */

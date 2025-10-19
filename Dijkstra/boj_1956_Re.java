import java.io.*;
import java.util.*;

public class boj_1956_Re {

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

    static ArrayList<Node> [] graph;
    static int N, E;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 정점 수
        E = Integer.parseInt(st.nextToken()); // 간선 수
        graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        int INF = Integer.MAX_VALUE;

        dist = new int[N + 1][N + 1];
        for(int i = 1; i < N + 1; i++){
            for(int j = 1; j < N + 1; j++){
                dist[i][j] = INF;
            }
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        for(int i = 1; i < N + 1; i++){
            dijkstra(i);
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i < N + 1; i++){
            for(int j = 1; j < N + 1; j++){
                if(i == j)min = Math.min(min, dist[i][j]);
            }
        }

        if(min == Integer.MAX_VALUE)System.out.println(-1);
        else System.out.println(min);

    }

    static void dijkstra(int start){

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[start][cur.idx] < cur.cost)continue;

            for(Node next : graph[cur.idx]){
                if(dist[start][next.idx] > next.cost + cur.cost){
                    dist[start][next.idx] = next.cost + cur.cost;
                    pq.add(new Node(next.idx, next.cost + cur.cost));
                }
            }

        }

    }

}

/*
 * note : dijkstra
 * performance : 4252ms
 * title : 운동
 * etc : notion
 * 
 */
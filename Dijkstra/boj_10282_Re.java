import java.io.*;
import java.util.*;

public class boj_10282_Re {

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

    static int n, d, c;
    static ArrayList<Node>[] graph;
    static int cnt, ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
            d = Integer.parseInt(st.nextToken()); // 의존성(간선) 개수
            c = Integer.parseInt(st.nextToken()); // 최초 해킹 컴퓨터

            graph = new ArrayList[n + 1];
            for(int i = 0; i < n+1; i++){
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i < d; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a, s));

            }

            cnt = 0;
            ans = 0;
            dijkstra(n, c);
            System.out.println(cnt + " " + ans);
        }
    }

    static void dijkstra(int N, int start){
        int[] dist = new int[N+1];
        boolean[] visit = new boolean[N+1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            int now = pq.poll().idx;

            if(visit[now])continue;
            visit[now] = true; // 해당 정점의 최단거리가 확정되는 순간
            ans = Math.max(ans, dist[now]);
            cnt++;

            for(Node next : graph[now]){
                if(dist[next.idx] > dist[now] + next.cost){

                    dist[next.idx] = dist[now] + next.cost;

                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }

        }
    }
}

/*
 * note : dijkstra
 * performance : 744ms
 * title : 해킹
 * 정점의 최단거리가 확정되는 순간에 ans를 업데이트 한다. 
 * 마지막으로 확정되는 순간이 가장 마지막으로 해킹이 된 순간.
 */
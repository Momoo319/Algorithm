import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class boj_1854_Re {
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

    static Comparator<Integer> comparator = new Comparator<Integer>(){
        public int compare(Integer o1, Integer o2){
            return o1 < o2 ? 1 : -1;
        }
    };

    static ArrayList<Node>[] graph;
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        dijkstra(N, K, 1);

     }

    static void dijkstra(int N, int K, int start) {
//        int[] dist = new int[N + 1];
        PriorityQueue<Integer>[] dist = new PriorityQueue[N + 1];
        for(int i = 0; i <= N; i++){
            dist[i] = new PriorityQueue<>(K, comparator);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start].add(0);

        while(!pq.isEmpty()){
            Node now = pq.poll();

            for(Node next : graph[now.idx]){
                if(dist[next.idx].size() < K){
                    //저장된 경로가 k개보다 작으면 경로 저장
                    dist[next.idx].add(now.cost + next.cost);
                    pq.add(new Node(next.idx, now.cost + next.cost));
                }
                else if(dist[next.idx].peek() > now.cost + next.cost){
                    //저장된 경로가 k개이고 새로운 경로가 가장 큰 값보다 작으면 경로 저장
                    dist[next.idx].poll();
                    dist[next.idx].add(now.cost + next.cost);
                    pq.add(new Node(next.idx, now.cost + next.cost));
                }
            }
        }
        for(int i = 1; i < N + 1; i++){
            if(dist[i].size() == K){
                System.out.println(dist[i].peek());
            }
            else System.out.println(-1);
        }
     }
}

/*
* note : dijkstra
* performance : 1100ms
* title : K번째 최단경로 찾기
* https://velog.io/@10000ji_/%EB%B0%B1%EC%A4%80-1854%EB%B2%88-K%EB%B2%88%EC%A7%B8-%EC%B5%9C%EB%8B%A8%EA%B2%BD%EB%A1%9C-%EC%B0%BE%EA%B8%B0JAVA
 */
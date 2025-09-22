import java.io.*;
import java.util.*;

public class boj_13549_Re {

    static class Node implements Comparable<Node>{
        int idx;    // 현재 지점
        int cost;   // 소비 시간

        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, K;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dijkstra();
    }

    static void dijkstra(){
        int Max_pos = 100000;
        int[] dist = new int[Max_pos + 1];
        int INF = Integer.MAX_VALUE;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(dist, INF);
        dist[N] = 0;
        pq.add(new Node(N, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(dist[now.idx] < now.cost)continue;

            int np1 = now.idx - 1; //next position 1 : -1 이동했을 경우
            if(np1 >= 0 && np1 <= Max_pos && dist[np1] > now.cost + 1){
                //np1이 범위 내에 있고
                //now에서 이동했을 때의 시간이 기존 np1에 도달한 시간보다 적을 경우
                dist[np1] = now.cost + 1;
                pq.add(new Node(np1, dist[np1]));
            }

            int np2 = now.idx + 1;
            if(np2 >= 0 && np2 <= Max_pos && dist[np2] > now.cost + 1){
                dist[np2] = now.cost + 1;
                pq.add(new Node(np2, dist[np2]));
            }

            int np3 = now.idx * 2;
            if(np3 >= 0 && np3 <= Max_pos && dist[np3] > now.cost){
                // 순간이동은 시간이 흐르지 않기 때문에 + 1 하지 않는다.
                dist[np3] = now.cost;
                pq.add(new Node(np3, dist[np3]));
            }
        }
        System.out.println(dist[K]);
    }
}

/*
 * note : Dijkstra
 * performance : 180ms
 * etc : ArrayList<Node>[] graph에 노드를 저장해서 검사하는 것이 아니라
 * pq에서 바로 검사. from-to 구조가 아닌 각각의 idx를 반복해서 방문하여 최단거리 갱신
 */
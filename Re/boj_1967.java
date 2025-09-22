import java.io.*;
import java.util.*;

public class boj_1967 {

    static class Node{
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static int N, max, max_idx;
    static ArrayList<Node> [] list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        if(N == 1){
            System.out.println(0);
            return;
        }

        list = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());
            list[n1].add(new Node(n2, n3));
            list[n2].add(new Node(n1, n3));
        }

        max = 0;
        max_idx = 0;
        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[N + 1];
        visited[max_idx] = true;
        dfs(max_idx, 0);

        System.out.println(max);

    }

    static void dfs(int idx, int cnt){
        if(max < cnt){
            max = cnt;
            max_idx = idx;
        }

        for(Node next : list[idx]){
            if(!visited[next.idx]){
                visited[next.idx] = true;
                dfs(next.idx, cnt + next.cost);
            }
        }
    }


}

/*
 * note : dfs
 * performance : 196ms
 * title : 트리의 지름
 * etc : 1st dfs = 루트 노드(1)에서 가장 먼 노드(max_idx)를 찾는다
 * 2nd dfs = max_idx에서 가장 먼 노드를 찾는다.
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17619_Re {

    static class Node implements Comparable<Node>{
        int start;
        int end;
        int idx;

        public Node(int start, int end, int idx){
            this.start = start;
            this.end = end;
            this.idx = idx;
        }

        public int compareTo(Node o){

            return Integer.compare(this.start, o.start);
        }
    }

    static int N, Q;
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        Node[] nodelist = new Node[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = x1;
            arr[i][1] = x2;
            nodelist[i] = new Node(x1, x2, i);
        }

        make();

        Arrays.sort(nodelist);

        int curEnd = nodelist[0].end;
        for(int i = 0; i < N - 1; i++){
            if(curEnd >= nodelist[i+1].start){ // 현재 최후미랑 i+1의 start가 겹칠 경우 해당 번째 node들 union
                union(nodelist[i].idx, nodelist[i+1].idx);
            }

            curEnd = Math.max(curEnd, nodelist[i+1].end);
            // 2-4, 2-3, 4-5 순으로 nodelist가 정렬되어 있다면
            // 그냥 nodelist[i].end와 nodelist[i+1].start를 비교하면 연결이 안 된다고 인식
            // 현재까지 체크한 애들 중 가장 큰 end랑 비교할 필요 있음
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
             if(find(one-1) == find(two-1))System.out.println(1);
             else System.out.println(0);
        }

     }

    static void make(){
        p = new int[N+1];
        for(int i = 0; i < N + 1; i++){
            p[i] = -1;
        }
    }

    static int find(int x){
        if(p[x] < 0)return x;
        return p[x] = find(p[x]);
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y)return false;

        if(p[x] > p[y]){
            int temp = x;
            x = y;
            y = temp;
        }

        if(p[x] == p[y])p[x]--;

        p[y] = x;

        return true;
    }
}

/*
* note :union-find
* performance : 1364ms
* title : 개구리 점프
 */
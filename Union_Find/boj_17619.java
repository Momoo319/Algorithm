import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17619 {

    static int N, Q, max;
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];

        max = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = x1;
            arr[i][1] = x2;
            max = Math.max(max, x2);
        }

        make();

        for(int i = 0; i < N; i++){
            for(int j = arr[i][0]; j <= arr[i][1]; j++){
                union(arr[i][0], j);
            }
        }

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            if(find(arr[one-1][0]) == find(arr[two-1][0]))System.out.println(1);
            else System.out.println(0);
        }

     }

    static void make(){
        p = new int[max + 1];
        //x의 범위는 10의 9승까지 가능하기 때문에 10억 크기의 배열로 인한 메모리 초과
        for(int i = 0; i < max + 1; i++){
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
* note : union-find
* performance : 100ms 19점
* title : 개구리 점프
 */
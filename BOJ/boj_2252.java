import java.io.*;
import java.util.*;

public class boj_2252 {

    static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        ArrayList<Integer>[] aList = new ArrayList[N+1];
        for(int i = 0; i < N + 1; i++){
            aList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[to]++;
            aList[from].add(to);
        }

        Queue<Integer> pq = new LinkedList<>();
        for(int i = 1; i < N+1; i++){
            if(arr[i] == 0){
                pq.add(i);
            }
        }

        int[] ans = new int[N];
        int cnt = 0;
        while(!pq.isEmpty()){
            int now = pq.poll();

            ans[cnt] = now;
            cnt++;

            for(int to : aList[now]){
                arr[to]--;
                if(arr[to] == 0)pq.add(to);
            }
        }

        for(int i = 0; i < cnt; i++){
            System.out.print(ans[i] + " ");
        }
    }

}

/*
 * note : 위상 정렬
 * performance : 700ms
 * title : 줄 세우기
 * 
 */
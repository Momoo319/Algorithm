import java.io.*;
import java.util.*;

public class boj_2623_Re {

    static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        ArrayList<Integer>[] aList = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++){
            aList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num-1; j++){
                int to = Integer.parseInt(st.nextToken());
                aList[from].add(to);
                arr[to]++;

                from = to;
            }

        }

        Queue<Integer> q = new LinkedList<>();
        int addCnt = 0;
        for(int i = 1; i < N + 1; i++){
            if(arr[i] == 0){
                q.add(i);
                addCnt++;
            }
        }
        if(addCnt == 0){ // 처음부터 indegree가 0인 정점이 없음(사이클 존재)
            System.out.println(0);
            return;
        }

        int[] ans = new int[N];
        int cnt = 0;
        while(!q.isEmpty()){
            int now = q.poll();
            ans[cnt] = now;
            cnt++;

            for(int next : aList[now]){
                arr[next]--;
                if(arr[next] == 0){
                    //만약 사이클이 존재한다면 사이클에 해당하는 정점들의 indegree는 0이 되지 못함
                    //그래서 q에 add 될 수 없고 ans 배열에도 들어갈 수 없다.
                    q.add(next);
                }
            }
        }

        if(cnt != N){ 
            // ans 배열의 크기가 정점 크기보다 작다면 사이클로 인해 들어오지 못한 정점이 존재함 의미
            System.out.println(0);
            return;
        }
        for(int i = 0; i < cnt; i++){
            System.out.println(ans[i]);
        }

    }
}

/*
 * note : 위상 정렬
 * performance : 140ms
 * title : 음악 프로그램
 * etc : 사이클 판별하는 과정 잊지 말 것!
 * 
 */
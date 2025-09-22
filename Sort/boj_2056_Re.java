import java.io.*;
import java.util.*;

public class boj_2056_Re {

    static int N;
    static ArrayList<Integer>[] graph; // 선행 작업들 보관할 arraylist
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i = 0; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        int[] arr = new int[N+1]; // 작업당 (남은) indegree 개수
        int[] time = new int[N+1]; // 해당 작업의 소요 시간

        for(int i = 1; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken()); // 소요 시간

            int num = Integer.parseInt(st.nextToken());
            // i 작업 전에 완료되어야 하는 선행 작업 개수

            for(int j = 0; j < num; j++){
                int temp = Integer.parseInt(st.nextToken());
                graph[temp].add(i);
                arr[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        int[] result = new int[N + 1];

        for(int i = 1; i < N + 1; i++){
            result[i] = time[i];
            if(arr[i] == 0)q.offer(i);
        }

        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : graph[now]){
                arr[next]--;

                result[next] = Math.max(result[next], result[now]+time[next]);

                if(arr[next] == 0){
                    q.offer(next);
                }
            }
        }

        int ans = 0;
        for(int i = 1; i < N + 1; i++){
            ans = Math.max(ans, result[i]);
        }
        System.out.println(ans);
    }
}



/*
 * note : 위상 정렬
 * performance : 740ms
 * title : 작업
 * etc : 선수 계획에 있지 않은 정점들은 동시 수행 가능
 * 모든 작업 완료하는 최소 시간 구하라 
 * -> 가장 오래 걸리는 작업을 구하면 그 시간 안에 다른 작업들은 모두 수행 가능
 * 
 */
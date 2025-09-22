import java.io.*;
import java.util.*;

public class boj_14567 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N + 1];
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            indegree[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[N + 1];
        for(int i = 1; i < N + 1; i++){
            if(indegree[i] == 0){
                q.offer(i);
                result[i] = 1;
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : graph[now]){
                indegree[next]--;
                if(indegree[next] == 0){
                    q.offer(next);
                }

                result[next] = Math.max(result[next], result[now] + 1);
            }
        }

        for(int i = 1; i < N + 1; i++){
            System.out.print(result[i] + " ");
        }
    }
}


/*
 * note : 위상 정렬
 * performance : 604ms
 * title : 선수 과목
 * etc : 다익스트라와 유사하게 계속해서 갱신.
 * 차이점이 있다면 여기선 선수과목을 모두 포함해야 되기 때문에 max로 갱신한다.
 * 
 */
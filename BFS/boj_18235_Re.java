import java.io.*;
import java.util.*;

public class boj_18235_Re {

    static class Node{
        int day;
        int x;

        public Node(int day, int x){
            this.day = day;
            this.x = x;
        }
    }

    static HashSet<Integer>[]ori;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        ori = new HashSet[N+1];
        for(int i = 1; i < N+1; i++){
                ori[i] = new HashSet<>();
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, A));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i = 0; i < 2; i++){
                int day = cur.day + 1;
                int dist = (int) Math.pow(2, day - 1);
                int nx = 0;

                if(i == 0){
                    nx = cur.x + dist;
                }
                else{
                    nx = cur.x - dist;
                }

                if(nx <= 0 || nx > N)continue;
                if(ori[nx].contains(day))continue;
                ori[nx].add(day);
                q.add(new Node(day, nx));

            }
        }

        q = new LinkedList<>();
        q.add(new Node(0, B));

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i = 0; i < 2; i++){
                int day = cur.day + 1;
                int dist = (int) Math.pow(2, day - 1);
                int nx = 0;

                if(i == 0){
                    nx = cur.x + dist;
                }
                else{
                    nx = cur.x - dist;
                }

                if(nx <= 0 || nx > N)continue;
                if(ori[nx].contains(day)){
                    System.out.println(day);
                    return;
                }
                q.add(new Node(day, nx));
            }
        }
        System.out.println(-1);

    }

}


/*
 * note : bfs(일차원)
 * performance : 668ms
 * title : 지금 만나러 갑니다
 * etc : 
 * 일차원 bfs로 우선 오리가 x 위치에 도달할 수 있는 날짜들을 전부 HashSet 배열에 저장
 * 그 이후 육리가 bfs를 돌면서 해당 위치, 해당 날짜에 오리가 있었는지 확인
 * bfs이기 때문에 poll 되는 Node들은 day가 순서대로
 */
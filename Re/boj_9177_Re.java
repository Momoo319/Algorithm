import java.io.*;
import java.util.*;

public class boj_9177_Re {

    static char[] s1, s2, comb;
    static boolean[][] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());

            s1 = st.nextToken().toCharArray();
            s2 = st.nextToken().toCharArray();
            comb = st.nextToken().toCharArray();

            if(func()){
                sb.append("Data set " + (tc + 1) + ": yes\n");
            }
            else{
                sb.append("Data set " + (tc + 1) + ": no\n");
            }
        }
        System.out.println(sb);
    }
    static boolean func(){
        Queue<int[]> q = new LinkedList<>();
        check = new boolean[201][201];
        q.add(new int[] {0, 0, 0});
        //각각 순서대로 s1, s2, comb의 몇번째 철자를 체크하는지 의미

        check[0][0] = true;

        while(!q.isEmpty()){
            int idx1 = q.peek()[0];
            int idx2 = q.peek()[1];
            int outIdx = q.peek()[2];
            q.poll();

            if(outIdx == comb.length)return true;

            if(idx1 < s1.length 
                // idx1이 s1.length보다 크거나 같다면 이미 s1은 전부 체크 됨
                && !check[idx1 + 1][idx2]
                && s1[idx1] == comb[outIdx]){
                q.add(new int[] {idx1 + 1, idx2, outIdx + 1});
                check[idx1 + 1][idx2] = true;
            }

            if(idx2 < s2.length
                && !check[idx1][idx2 + 1]
                && s2[idx2] == comb[outIdx]){
                q.add(new int[] {idx1, idx2 + 1, outIdx + 1});
                check[idx1][idx2 + 1] = true;
            }
        }

        return false;
    }
}

/*
 * note : bfs
 * performance : 240ms
 * title : 단어 섞기
 * etc : notion
 */

import java.io.*;
import java.util.*;

public class boj_2167_Re {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M =Integer.parseInt(st.nextToken());

        int[][] sum = new int[N+1][M+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            int ans = sum[y2][x2] - sum[y1-1][x2] - sum[y2][x1 - 1] + sum[y1-1][x1-1];

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

}

/*
*   note : 누적합
*   performance : 288ms(단순 구현 : 2152ms)
*   title : 2차원 배열의 합
*   etc : notion
*/
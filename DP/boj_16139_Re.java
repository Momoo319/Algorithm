import java.io.*;
import java.util.*;

public class boj_16139_Re {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        String str = st.nextToken();
        int len = str.length();
        int[][] arr = new int[len + 1][26];

        for(int i = 1; i <= str.length(); i++){
            int idx = str.charAt(i - 1) - 'a';

            // 이전 누적값 복사
            for (int j = 0; j < 26; j++) {
                arr[i][j] = arr[i - 1][j];
            }

            // 현재 문자 count +1
            arr[i][idx]++;
        }


        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int idx = c - 'a';
            int ans = arr[y + 1][idx] - arr[x][idx];

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

}

/*
*   note : 누적합
*   performance : 712ms
*   title : 인간-컴퓨터 상호작용
*   etc : notion
*/
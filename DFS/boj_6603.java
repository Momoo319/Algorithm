import java.io.*;
import java.util.*;

public class boj_6603 {

    static int k;
    static int[] arr;
    static int[] ans;
    static StringBuilder sb;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            if(k == 0)break;
            arr = new int[k];
            ans = new int[6];
            visit = new boolean[k];

            int n;
            for(int i = 0; i < k; i++){
                n = Integer.parseInt(st.nextToken());
                arr[i] = n;
            }

            func(0, 0);
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static void func(int cnt, int idx){
        if(cnt == 6){
            for(int i = 0; i < 6; i++){
                sb.append(ans[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = idx; i < k; i++){
            if(!visit[i]){
                ans[cnt] = arr[i];
                visit[i] = true;
                func(cnt + 1, i+1);
                visit[i] = false;
            }
        }

    }
}

/*
 * note : combination
 * performance : 
 * title : 로또
 * 
 */
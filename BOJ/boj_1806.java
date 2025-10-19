import java.io.*;
import java.util.*;

public class boj_1806 {

    static int N, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 1;
        int ans = Integer.MAX_VALUE;

        while(true){
            if(sum >= S){
                if(len == 1){
                    System.out.println(1);
                    return;
                }

                ans = Math.min(ans, len);
                sum -= arr[left];
                left++;
                len--;
            }
            else{
                right++;
                if(right == N)break;
                len++;
                sum += arr[right];
            }
        }

        if(ans == Integer.MAX_VALUE)System.out.println(0);
        else System.out.println(ans);

    }

}

/*
 * note : two-pointer
 * performance : 252ms
 * title : 부분합
 * 
 */
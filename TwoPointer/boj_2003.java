import java.io.*;
import java.util.*;

public class boj_2003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;

        int cnt = 0;
        int sum = 0;
        while(right < N){
            if(sum + arr[right] >= M){
                if(sum + arr[right] == M){
                    cnt++;
                }
                sum -= arr[left];
                left++;
            }
            else{
                sum += arr[right];
                right++;
            }
        }

        System.out.println(cnt);
    }


}

/*
 * note : two-pointer
 * performance : 124ms
 * title : 수들의 합2
 * etc : https://bbangson.tistory.com/72 (투포인터에 대하여)
 * 
 */
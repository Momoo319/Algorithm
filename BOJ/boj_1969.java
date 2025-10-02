import java.io.*;
import java.util.*;

public class boj_1969 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // DNA 개수
        int M = Integer.parseInt(st.nextToken()); // 문자열 길이

        String[] arr = new String[N];
        int[] A = new int[M];
        int[] T = new int[M];
        int[] G = new int[M];
        int[] C = new int[M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = st.nextToken();

            for(int j = 0; j < M; j++){
                char temp = arr[i].charAt(j);

                switch(temp){
                    case 'A':
                        A[j]++;
                        break;
                    case 'T':
                        T[j]++;
                        break;
                    case 'G':
                        G[j]++;
                        break;
                    case 'C':
                        C[j]++;
                        break;
                }
            }
        }

        String ans = "";

        int temp = 0;
        for(int i = 0; i < M; i++){
            if(A[i] >=  T[i] && A[i] >= G[i] && A[i] >= C[i]){
                ans += "A";
                temp += T[i] + G[i] + C[i];
            }
            else if(C[i] >=  T[i] && C[i] >= G[i] && C[i] >= A[i]){
                ans += "C";
                temp += T[i] + G[i] + A[i];
            }
            else if(G[i] >=  T[i] && G[i] >= A[i] && G[i] >= C[i]){
                ans += "G";
                temp += T[i] + A[i] + C[i];
            }
            else if(T[i] >=  A[i] && T[i] >= G[i] && T[i] >= C[i]){
                ans += "T";
                temp += A[i] + G[i] + C[i];
            }
        }

        System.out.println(ans);
        System.out.println(temp);
    }

}

/*
 * note : string
 * performance : 124ms
 * title : DNA
 * 
 */
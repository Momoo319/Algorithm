import java.io.*;
import java.util.*;

public class boj_10816 {

    static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> numb = new HashMap<>();

        for(int i = 0; i < N; i++){
            int temp = Integer.parseInt(st.nextToken());

            numb.put(temp, numb.getOrDefault(temp, 0) + 1);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int check = Integer.parseInt(st.nextToken());

            sb.append(numb.getOrDefault(check, 0)).append(' ');
        }

        System.out.println(sb);

    }

}

/*
 * note : HashMap
 * performance : 1036ms
 * title : 숫자 카드2
 * 
 */
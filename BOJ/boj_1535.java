import java.io.*;
import java.util.*;

public class boj_1535 {

    static int N, c, j, max;
    static int[] cardio, joy;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        cardio = new int[N];
        joy = new int[N];
        visit = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            cardio[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            joy[i] = Integer.parseInt(st.nextToken());
        }

        c = 100;
        j = 0;
        max = 0;

        func(0, 0, c, j);

        System.out.println(max);
    }

    static void func(int cnt, int idx, int car, int jo){
        if(car <= 0 || cnt > N){
            return;
        }
        max = Math.max(jo, max);

        for(int j = idx; j < N; j++){
            visit[j] = true;
            func(cnt + 1, j+1, car - cardio[j], jo + joy[j]);
            visit[j] = false;
        }
    }

}

/*
 * note : combination
 * performance : 120ms
 * title : 안녕
 */
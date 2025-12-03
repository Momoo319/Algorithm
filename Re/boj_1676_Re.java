import java.io.*;
import java.util.*;

public class boj_1676_Re {

    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int cnt = 0;

        while(N >= 5){
            cnt += N / 5;
            N /= 5;
        }

        System.out.println(cnt);

    }

}

/*
* note : math
* performance : 104ms
* title : 팩토리얼 0의 개수
* etc : notion
*/
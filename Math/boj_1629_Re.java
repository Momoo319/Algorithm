import java.io.*;
import java.util.*;

public class boj_1629_Re {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long ans = pow(a, b, c);

        System.out.println(ans);
    }
    static long pow(int a, int b, int mod){
        if(b == 0){
            return 1;
        }

        long n = pow(a, b/2, mod);

        if(b % 2 == 0){
            return n * n % mod;
        }
        else{
            return (n * n % mod) * a % mod;
        }
    }

}

/*
 * note : 모듈러
 * performance : 104ms
 * title : 곱셈
 * etc : notion
 * 
 */
import java.io.*;
import java.util.*;

public class boj_16953 {

    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int cnt = 1;

        while(B > A){
            if(B % 2 == 0){
                B /= 2;
            }
            else if(B % 10 == 1){
                B /= 10;
            }
            else{
                System.out.println(-1);
                return;
            }

            cnt++;
        }

        if(B == A){
            System.out.println(cnt);
        }
        else{
            System.out.println(-1);
        }
    }


}

/*
 * note : greedy
 * performance : 108ms
 * title : A => B
 * etc : B에서 A로 만들 수 있는지
 * 2로 나누어 떨어질 경우 / 2
 * 맨 끝자리가 1일 경우 /= 10
 * 둘 다 가능하지 않을 경우 A가 될 수 없음 -> -1
 */
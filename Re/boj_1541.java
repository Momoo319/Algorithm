import java.io.*;
import java.util.*;

public class boj_1541 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = Integer.MAX_VALUE;

        StringTokenizer st1 = new StringTokenizer(br.readLine(), "-");

        while(st1.hasMoreTokens()){

            StringTokenizer st2 = new StringTokenizer(st1.nextToken(), "+");

            int temp = 0;
            while(st2.hasMoreTokens()){
                temp += Integer.parseInt(st2.nextToken());
            }

            if(sum == Integer.MAX_VALUE){
                sum = temp;
            }
            else{
                sum -= temp;
            }

        }

        System.out.println(sum);

    }

}

/*
 * note : greedy
 * performance : 108ms
 * title : 잃어버린 괄호
 * etc : - 를 기준으로 수를 나누고 나머지를 다 + 한 후 
 * 첫 - 가 나오기 이전의 수 (sum이 아직 Integer.MAX_VALUE)를 sum으로
 * 한 뒤 다음 + 한 수들은 다 빼주면 최소값이 된다.
 */
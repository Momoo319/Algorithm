import java.io.*;
import java.util.*;

public class boj_1259 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            String str = br.readLine();

            if(str.equals("0"))break;
            if(str.length() == 1){
                sb.append("yes").append("\n");
                continue;
            }

            for(int i = 0; i < str.length() / 2; i++){
                if(str.charAt(i) == str.charAt(str.length() - i - 1)){}
                else{
                    sb.append("no").append("\n");
                    break;
                }

                if(i + 1 == str.length() / 2)sb.append("yes").append("\n");
            }

        }

        System.out.println(sb);
    }
}

/*
 * note : 
 * performance : 104ms
 * title : 팰리드롬수
 * 
 */




import java.io.*;
import java.util.*;

public class boj_1620 {

    static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> dic1 = new HashMap<>();
        HashMap<String, Integer> dic2 = new HashMap<>();

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            dic1.put(i, name);
            dic2.put(name, i);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            String temp = st.nextToken();
            if(Character.isDigit(temp.charAt(0))){
                System.out.println(dic1.get(Integer.parseInt(temp)));
            }
            else{
                System.out.println(dic2.get((temp)));
            }
        }

    }

}

/*
 * note : HashMap
 * performance : 1100ms
 * title : 포켓몬 마스터
 * 
 */
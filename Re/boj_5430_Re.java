import java.io.*;
import java.util.*;

public class boj_5430_Re {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++){
            String p = br.readLine();
            Deque<Integer> dq = new ArrayDeque<>();


            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            String temp = br.readLine();
            StringTokenizer st2 = new StringTokenizer(temp, "[],");
            for(int i = 0; i < N; i++){
                dq.add(Integer.parseInt(st2.nextToken()));
            }

            boolean dir = true;
            boolean err = false;
            for(int i = 0; i < p.length(); i++){

                if(p.charAt(i) == 'R'){
                    if(dir)dir = false;
                    else dir = true;
                }
                else{
                    if(dq.isEmpty()){
                        sb.append("error").append("\n");
                        err = true;
                        break;
                    }
                    else{
                        if(dir){
                            dq.pollFirst();
                        }
                        else{
                            dq.pollLast();
                        }
                    }

                }
            }

            if(err)continue;
            sb.append('[');
            int size = dq.size();
            if(dir){
                for(int i = 0; i < size; i++){
                    sb.append(dq.poll());
                    if(i + 1 != size)sb.append(',');
                }
                sb.append(']').append("\n");

            }
            else{
                for(int i = size - 1; i >= 0; i--){
                    sb.append(dq.pollLast());
                    if(i - 1 >= 0)sb.append(',');
                }
                sb.append(']').append("\n");
            }
        }

        System.out.println(sb);

    }

}

/*
*   note : string
*   performance : 680ms
*   title : AC
*   etc : notion(문자열 입력)
*/
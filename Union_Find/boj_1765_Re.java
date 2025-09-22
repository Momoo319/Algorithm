import java.io.*;
import java.util.*;

public class boj_1765_Re {

    static int N, M;
    static int[] p;
    static ArrayList<Integer>[] eList;
    static ArrayList<Integer>[] fList;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        eList = new ArrayList[N+1];
        fList = new ArrayList[N+1];
        make();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String r = st.nextToken();
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if(r.equals("F")){
                fList[p].add(q);
                fList[q].add(p);
            }
            else{
                eList[p].add(q);
                eList[q].add(p);
            }

        }
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < eList[i].size(); j++){
                int n = eList[i].get(j);
                for(int k = 0; k < eList[n].size(); k++){
                    if(i == eList[n].get(k))continue; 
                //eList[p].add(q);
                //eList[q].add(p);
                //이런 식으로 양방향으로 넣었기 때문에 원수의 원수가 자기 자신이 되는 경우 skip
                    fList[i].add(eList[n].get(k));
                    fList[eList[n].get(k)].add(i);
                }
            }
        }

        for(int i = 1; i <= N; i++){
            for(int j = 0; j < fList[i].size(); j++){
                union(i, fList[i].get(j));
            }
        }

        int cnt = 0;
        for(int i = 1; i <= N; i++){
            if(p[i] < 0)cnt++;
        }

        System.out.println(cnt);

    }

    static void make(){
        p = new int[N+1];
        for(int i = 0; i < N+1; i++){
            eList[i] = new ArrayList<>();
            fList[i] = new ArrayList<>();
            p[i] = -1;
        }
    }

    static int find(int x){
        if(p[x] < 0)return x;
        return p[x] = find(p[x]);
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y)return false;

        if(p[x] > p[y]){
            int temp = x;
            x = y;
            y = temp;
        }

        if(p[x] == p[y])p[x]--;

        p[y] = x;

        return true;
    }

}

/*
 * note : union-find
 * performance : 360ms
 * title : 닭싸움
 * 
 */

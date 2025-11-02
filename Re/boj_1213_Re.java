import java.io.*;

public class boj_1213_Re {

    static boolean[] visit;
    static String ans = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int[] c = new int[26];
        visit = new boolean[str.length()];

        for(int i = 0; i < str.length(); i++){
            int idx = str.charAt(i) - 'A';
            c[idx]++;
        }

        int oddIdx = -1; //홀수인 알파벳 idx
        int cnt = 0; // 알파벳 등장 횟수 홀수 check
        for(int i = 0; i < 26; i++){
            if(c[i] == 0)continue;

            if(c[i] % 2 == 1){
                cnt++;
                oddIdx = i;
            }

            if(str.length() % 2 == 1 && cnt > 1){
                System.out.println("I'm Sorry Hansoo");
                return;
            }
            else if(str.length() % 2 == 0 && cnt > 0){
                System.out.println("I'm Sorry Hansoo");
                return;
            }
        }

        StringBuilder left = new StringBuilder();
        for(int i = 0; i < 26; i++){
            for(int j = 0; j < c[i] / 2; j++){
                left.append((char) (i + 'A'));
    // 사전 순으로 앞에 있는 알파벳부터 / 2개 만큼 먼저 배치
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);
        if(oddIdx != -1){
    // 빈도수가 1인 알파벳은 한개만 있을 수 있음
    // 2개 이상이었다면 펠린드롬 자체가 성립되지 않기 때문에 30, 34 line if문에서 걸러짐
            ans.append((char)(oddIdx + 'A'));
        }
        ans.append(new StringBuilder(left).reverse());

        System.out.println(ans);
    }



}

/*
 * note : string, greedy
 * performance : 100ms
 * title : 펠린드롬 만들기
 * etc : notion
 */
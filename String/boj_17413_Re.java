import java.io.*;

public class boj_17413_Re {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();

        char[] arr = br.readLine().toCharArray();
        boolean check = false;
        for(char cur : arr){
            if(cur == '<'){
                result.append(sb.reverse());
                // '<' 이전의 일반 문자(reverse 대상)을 result에 삽입
                // 만약 '<' 이전이 공백이었다면 sb는 비어있을테니 문제 없음!

                sb = new StringBuilder();
                sb.append('<');
                check = true;
            }
            else if(cur == '>'){
                sb.append('>');
                result.append(sb);
                sb = new StringBuilder();
                check = false;
            }
            else if(cur == ' ' && !check){ // < > 내부의 공백이 아닐 경우
                result.append(sb.reverse()).append(" ");
                sb = new StringBuilder();
            }
            else{ // < > 내부 아닌 문자/숫자 등일 경우
                sb.append(cur);
            }
        }

        result.append(sb.reverse());
        //마지막이 일반 문자로 끝날 경우 reverse 삽입의 대상이기 때문에 append해줌.
        //만약 공백이나 '>'로 끝났다면 sb는 비어있을 것!

       System.out.print(result);
    }


}

/*
* note : String
* performance : 168ms
* title : 단어 뒤집기 2
* etc : notion
 */
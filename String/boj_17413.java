import java.io.*;
import java.util.*;

public class boj_17413 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

       String str = br.readLine();

       for(int i = 0; i < str.length(); i++){
           if(str.charAt(i) == '<'){

               while(true){
                   sb.append(str.charAt(i));
                   i++;

                   if(str.charAt(i) == '>'){
                       sb.append(str.charAt(i));
                       break;
                   }
               }
           }

           else if(str.charAt(i) != ' '){
               String temp = "";
               while(true){
                   temp += str.charAt(i);
                   i++;

                   if(i == str.length() || str.charAt(i) == ' ' || str.charAt(i) == '<'){
                       i--;
                       break;
                   }
               }

               for(int j = temp.length() - 1; j >= 0; j--){
                   sb.append(temp.charAt(j));
               }
           }
           else{
               sb.append(' ');
           }
       }



       System.out.print(sb);
    }


}

/*
 * note : String
 * performance : 976ms
 * title : 단어 뒤집기 2
 * etc : notion
 */
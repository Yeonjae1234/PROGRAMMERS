import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static int solution(String numbers) {
        int answer = 0;
        int[] numCheck = new int[10];
        for(int i=0;i<numbers.length();i++){
            int k = numbers.charAt(i)-48;
            numCheck[k]++;
        }

        for(int i=1;i<10;i++){
            if(numCheck[i]!=0){
                numCheck[i]--;
                answer = dfs(numCheck,i+"",answer);
                numCheck[i]++;
            }
        }

        return answer;
    }

    public static int dfs(int[] numCheck,String number,int answer){
        if(isPrime(Integer.parseInt(number))) answer++;
        for(int i=0;i<10;i++){
            if(numCheck[i]!=0){
                numCheck[i]--;
                answer = dfs(numCheck,number+i,answer);
                numCheck[i]++;
            }
        }
        return answer;
    }

    public static boolean isPrime(int a){
        if(a==0||a==1) return false;

        for(int i=2;i<a;i++){
            if(a%i==0) return false;
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        bw.write(solution("011")+"");

        bw.flush();
        bw.close();
    }
}

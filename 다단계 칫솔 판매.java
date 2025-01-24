// https://school.programmers.co.kr/learn/courses/30/lessons/77486?language=java

import java.io.*;
import java.util.*;

public class Main {

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        HashMap<String,Integer> name = new HashMap<>(enroll.length);
        for (int i = 0; i < enroll.length; i++) {
            name.put(enroll[i], i);
        }

        int[] intRef = new int[enroll.length];
        for (int i = 0; i < referral.length; i++) {
            if(referral[i].equals("-")){
                intRef[i] = -1;
            }
            else intRef[i] = name.get(referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            dfs(amount[i]*100,name.get(seller[i]),answer,intRef);
        }


        return answer;
    }

    static int dfs(int profit, int nameIndex, int[] answer,int[] intRef) {
        int refProfit = profit / 10;
        answer[nameIndex]+=profit-refProfit;
        if(refProfit<1) return 0;
        if(intRef[nameIndex]==-1) {
            return 0;
        }
        dfs(refProfit,intRef[nameIndex],answer,intRef);
        return 0;
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        solution(enroll,referral,seller,amount);

        bw.flush();
        bw.close();

    }
}

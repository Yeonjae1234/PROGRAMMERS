import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


    public static int solution(int k, int[][] dungeons) {
        int answer = -1;
        Deque<Integer> visited = new LinkedList<>();

        answer = tracking(dungeons,visited,k,-1);

        return answer;
    }

    public static int tracking(int[][] dungeons,Deque<Integer> visited,int k,int max){
        Deque<Integer> avail = new LinkedList<>();
        if(max<visited.size()) max=visited.size();
        for(int i=0;i< dungeons.length;i++){
            if(visited.contains(i)) continue;
            if(k>=dungeons[i][0]) avail.add(i);
        }
        while(!avail.isEmpty()){
            int a = avail.pollFirst();
            visited.addLast(a);
            max = tracking(dungeons,visited,k-dungeons[a][1],max);
            visited.pollLast();
        }
        return max;
    }
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] dungeons = {{80,20},{50,40},{30,10}};
        int k = 80;
        
        bw.write(solution(k,dungeons)+"");

        bw.flush();
        bw.close();
    }
}

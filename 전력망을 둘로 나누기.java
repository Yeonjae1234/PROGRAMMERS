// https://school.programmers.co.kr/learn/courses/30/lessons/86971?language=java

import java.io.*;
import java.util.*;

public class Main {

    public static int solution(int n, int[][] wires) {
        int answer = n;

        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for(int i=0;i<n+1;i++){
            map.add(new ArrayList<Integer>());
        }

        for(int[] w:wires){
            int x=w[0];
            int y=w[1];
            map.get(x).add(y);
            map.get(y).add(x);
        }

        for(int[] w:wires){
            int x=w[0];
            int y=w[1];
            map.get(x).remove(Integer.valueOf(y));
            map.get(y).remove(Integer.valueOf(x));

            answer=Math.min(answer,bfs(map));

            map.get(x).add(y);
            map.get(y).add(x);

        }


        return answer;
    }

    public static int bfs(ArrayList<ArrayList<Integer>> map){
        int[] visited = new int[map.size()+1];
        Queue<Integer> Q = new LinkedList<>();
        int count=0;

        Q.add(1);
        while(!Q.isEmpty()){
            int x = Q.poll();
            visited[x]=1;
            count++;
            for(int i:map.get(Integer.valueOf(x))){
                if(visited[i]==0){
                    Q.add(i);
                }
            }
        }

        int another = map.size()-1-count;
        return Math.abs(another-count);
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        bw.write(solution(n,wires)+"");

        bw.flush();
        bw.close();

    }
}

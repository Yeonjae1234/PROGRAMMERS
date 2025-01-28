// https://school.programmers.co.kr/learn/courses/30/lessons/60059

import java.io.*;
import java.util.*;

public class Main {

    public static boolean solution(int[][] key, int[][] lock) {

        int M = key.length;
        int N = lock.length;



        ArrayList<int[][]> rotate = new ArrayList<>();
        rotate.add(key);
        int[][] current = key;
        for (int r = 0; r < 3; r++) {
            int[][] temp = new int[M][M];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = current[M-j-1][i];
                }
            }
            rotate.add(temp);
            current = temp;
        }

        if(check(lock,N,1)) return true;

        for(int[][] rkey : rotate){
            for (int i = 0; i < M - 1 + N; i++) {
                for (int j = 0; j < M - 1 + N; j++) {
                    if (fillKey(lock, N, M, rkey, i, j)) {
                        return true;
                    }
                    else{
                        continue;
                    }
                }
            }

        }
        return false;

    }

    public static boolean fillKey(int[][] lock,int N,int M, int[][] rkey, int startI, int startJ){
        int[][] lockMap = new int[2*(M-1)+N][2*(M-1)+N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                lockMap[M - 1 + i][M - 1 + j] = lock[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                lockMap[startI+i][startJ+j] += rkey[i][j];
                if(lockMap[startI+i][startJ+j]==2){
                    return false;
                }
            }
        }

        return check(lockMap,N,M);

    }

    public static boolean check(int[][] lockMap,int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lockMap[M - 1 + i][M - 1 + j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0},{1, 0, 1}};

        bw.write(solution(key,lock)+"");

        bw.flush();
        bw.close();

    }
}

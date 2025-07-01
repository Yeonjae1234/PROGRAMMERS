// https://school.programmers.co.kr/learn/courses/30/lessons/72413

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        // fares 간선들의 정보를 정점 중심으로 저장. -> graph
        // graph[1] : 1번 정점에 연결된 간선들의 정보를 저장
        // 저장 형태 : [목적지 노드, 가중치]
        List<Integer[]>[] graph = new List[n+1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] cur : fares) {
            int x = cur[0];
            int y = cur[1];
            int value = cur[2];
            graph[x].add(new Integer[]{y, value});
            graph[y].add(new Integer[]{x, value});
        }

        // a, b, s 를 기준으로 다른 노드들까지의 거리를 구함. 그 정보를 배열의 형태로 각각 distA, distB, distS
        int[] distA = dijkstra(n,graph,a);
        int[] distB = dijkstra(n,graph,b);
        int[] distS = dijkstra(n,graph,s);

        
        // 최적의 합승 위치 찾기
        // s에서 A와 B가 같이 합승하여 k 노드까지 이동한다고 가정
        // (s->k) + (k->a) + (k->b) 의 합이 가장 작은게 우리가 구하고자 하는 답
        // k는 s, a, b 모두 될 수 있음
        for (int i = 1; i < n + 1; i++) {
            if (distS[i] == Integer.MAX_VALUE) {  // 그래프로 연결되어있지 않은 노드만 제외
                continue;
            }
            answer = Integer.min(answer, distS[i] + distA[i] + distB[i]);
        }

        return answer;
    }

    // 다익스트라 구현
    // 우선순위큐를 사용하면 효율성을 높일 수 있다
    // : 해당 위치까지 거리가 작은 노드를 먼저 뽑아서 이동하고, 지금까지 구한 최단 거리보다 더 크게 갱신된 내용이 큐에 저장되어 있으면 skip하면 된다
    public int[] dijkstra(int n,List<Integer[]>[] graph,int start){
        int[] dist = new int[n+1];      // 거리값을 저장하기 위한 배열
        Arrays.fill(dist,Integer.MAX_VALUE);

        // 큐에는 [목적지 노드, 출발점에서 해당 노드까지 거리] 형태로 저장할 것임.
        // 거리가 작은값을 먼저 큐에서 꺼낼 수 있도록 구현
        PriorityQueue<Integer[]> Q = new PriorityQueue<>(       
                (a, b) -> Integer.compare(a[1], b[1])
        );
        Q.add(new Integer[]{start, 0});
        dist[start] = 0;

        while(!Q.isEmpty()){
            Integer[] node = Q.poll();
            int v = node[0];        // v : v에서 연결된 노드로 이동할 것임

            if (dist[v] < node[1]) {  // 기준점(start)에서 v까지의 거리가 최단값으로 갱신되기 전에 더 큰 값으로 큐에 들어갔으면 skip
                continue;
            }

            for(Integer[] cur:graph[v]){        // v에서 연결된 간선들 확인
                int newDist = dist[v] + cur[1];
                if (newDist < dist[cur[0]]) {
                    dist[cur[0]] = newDist;         // dist[] 갱신
                    Q.add(new Integer[]{cur[0], newDist});      // 해당 위치 값 갱신되었으니 큐에 넣어서 다음 노드로 이동 필요
                }
            }
        }


        return dist;
    }

}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(sol.solution(n, s, a, b, fares));

    }
}

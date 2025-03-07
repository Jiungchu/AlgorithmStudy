import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int V, E, t;
    static Map<Integer, List<Integer>> listIn, listOut;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        
        for (t = 1; t <= 10; t++) {
        	init();
            solution();
        }
        System.out.println(sb.toString());
        br.close();
    }

    static void solution() {
    	sb.append("#").append(t).append(" ");
    	
    	Queue<Integer> q = new ArrayDeque<>();
    	for(int i=1;i<=V;i++) {
    		if(!listIn.containsKey(i)) {
    			q.offer(i); // 들어오는 간선이 없는 지점을 큐에 추가
    			visited[i] = true;
    		}
    	}
    	while(!q.isEmpty()) {
    		int curr = q.poll();
//    		System.out.println(curr);
    		if(listOut.containsKey(curr)) {
    			for(int next : listOut.get(curr)) {
    				if(visited[next]) continue;
    				boolean possible=true;
    				for(int toNext : listIn.get(next)) { // next 노드의 선행 노드들
    					if(!visited[toNext]) { // 선행 노드 중 아직 방문하지 않는 노드가 있을 경우
    						possible=false;
    						break; 
    					}
    				}
    				if(possible) {
    					visited[next] = true;
    					q.offer(next);
    				}
    			}
    		}
    		sb.append(curr).append(" ");
    	}
    	sb.append("\n");
    }

    static void init() throws IOException{
    	StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        // 들어오는 간선, 나가는 간선을 따로 관리
        listIn = new HashMap<>();
        listOut = new HashMap<>();
        visited = new boolean[V+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<E;i++) {
        	int start = Integer.parseInt(st.nextToken());
        	int end= Integer.parseInt(st.nextToken());
        	
        	if(!listIn.containsKey(end)) listIn.put(end, new ArrayList<>());
        	if(!listOut.containsKey(start)) listOut.put(start, new ArrayList<>());
        	listIn.get(end).add(start); // 들어오는 간선
        	listOut.get(start).add(end); // 나가는 간선
        }
    }
}

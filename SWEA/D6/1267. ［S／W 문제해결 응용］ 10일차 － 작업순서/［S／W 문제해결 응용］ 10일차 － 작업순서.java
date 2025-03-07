
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
    static Map<Integer, List<Integer>> listOut;
    static int[] inDegree;

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
    		if(inDegree[i]==0) q.offer(i); // 들어오는 간선이 없는 지점을 큐에 추가
    	}
    	while(!q.isEmpty()) {
    		int curr = q.poll();
//    		System.out.println(curr);
    		if(listOut.containsKey(curr)) {
    			for(int next : listOut.get(curr)) {
    				inDegree[next]--;
    				if(inDegree[next]==0) {
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
        
        // 나가는 간선을 관리
        listOut = new HashMap<>();
        inDegree = new int[V+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<E;i++) {
        	int start = Integer.parseInt(st.nextToken());
        	int end= Integer.parseInt(st.nextToken());
        	
        	if(!listOut.containsKey(start)) listOut.put(start, new ArrayList<>());
        	listOut.get(start).add(end); // 나가는 간선
        	
        	inDegree[end]++; //  들어오는 간선의 개수 증가
        }
    }
}

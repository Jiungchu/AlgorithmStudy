
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	static int V, E, start;
	static List<List<int[]>> edges;
	
    public static void main(String[] args) throws IOException{
    	init();
    	solution();
    }
    
    static void solution() {
    	int[] distance = new int[V+1];
    	Arrays.fill(distance, Integer.MAX_VALUE);
    	PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
    	pq.add(new int[] {start, 0});
    	while(!pq.isEmpty()) {
    		int[] cur = pq.poll();
    		int num = cur[0], curDist = cur[1];
    		if(distance[num] < curDist ) continue;
    		distance[num] = curDist;
    		for(int[] edge : edges.get(num)) {
    			int next = edge[0], weight = edge[1];
    			if(distance[next]>curDist+weight) {
    				distance[next]=curDist+weight;
    				pq.add(new int[] {next, distance[next]});
    			}
    		}
    	}
    	StringBuilder sb = new StringBuilder();
    	for(int i=1;i<=V;i++) {
    		if(distance[i]== Integer.MAX_VALUE) sb.append("INF\n");
    		else sb.append(distance[i]+"\n");
    	}
    	System.out.println(sb.toString());
    }
    
    static void init() throws IOException {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	V = Integer.parseInt(st.nextToken());
    	E = Integer.parseInt(st.nextToken());
    	start = Integer.parseInt(br.readLine());
    	
    	edges =new ArrayList<>();
    	for(int i=0;i<=V+1;i++) {
    		edges.add(new ArrayList<>());
    	}
    	
    	for(int i=0;i<E;i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int w = Integer.parseInt(st.nextToken());
    		edges.get(a).add(new int[] {b,w});
    	}
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N,M, inDegree[];
	static List<List<Integer>> edges;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(sb);
	}
	
	static void solution() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1;i<=N;i++) {
			if(inDegree[i]==0) pq.offer(i);
		}
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur).append(" ");
			for(int next : edges.get(cur)) {
				inDegree[next]--;
				if(inDegree[next]==0) pq.offer(next);
			}
		}
		
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		inDegree = new int[N+1];
		edges = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			edges.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges.get(a).add(b);
			inDegree[b]++;
		}
	}

}

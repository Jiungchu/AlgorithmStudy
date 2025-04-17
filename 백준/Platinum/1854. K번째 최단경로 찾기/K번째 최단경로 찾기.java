
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int to;
		int dist;
		
		public Edge(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return this.dist-o.dist;
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N,M,K, count[];
	static int[][] distance;
	static List<List<Edge>> edges;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1,0));
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int node = cur.to, dist=cur.dist;
			if(count[node]>=K) continue;
			// 거리 정보 저장
			distance[node][count[node]++] = dist;
			for(Edge e:edges.get(node)) {
				if(count[e.to]<K) {
					pq.offer(new Edge(e.to, dist+e.dist));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			if(count[i]<K) sb.append(-1).append("\n");
			else sb.append(distance[i][K-1]).append("\n");
		}
		System.out.println(sb);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			edges.add(new ArrayList<>());
		}
		
		distance = new int[N+1][K];
		count = new int[N+1]; // 경로의 수를 저장
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.get(a).add(new Edge(b,w));
		}
	}

}


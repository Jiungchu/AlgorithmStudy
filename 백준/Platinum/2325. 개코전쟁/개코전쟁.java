
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	
	static class Edge implements Comparable<Edge>{
		int id, a, b, weight;

		public Edge(int id, int a, int b, int weight) {
			this.id = id;
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}

		@Override
		public String toString() {
			return "Edge [id=" + id + ", a=" + a + ", b=" + b + ", weight=" + weight + "]";
		}
		
		
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, dist[], prev[], id;
	static List<List<Edge>> edges;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		int min = dijkstra(-1); // 최단 경로를 구하기 위해 최초 호출
		
		ArrayList<Edge> list = new ArrayList<>();
		int cur = N;
		while(cur != 1) {
			// 두 점 사이의 경로는 항상 하나이므로, cur과 연결된 prev 점 찾기
			for(Edge e : edges.get(cur)) {
				int next = e.a==cur ? e.b: e.a ;
				if(next==prev[cur]) {
					cur = next;
					list.add(e);
				}
			}
		}
		int ans = 0;
		for(Edge e : list) {
			int ban = e.id;
			int distance = dijkstra(ban);
			ans = Math.max(ans, distance);
		}
		System.out.println(ans);
	}
	
	static int dijkstra(int ban) {
		dist = new int[N+1];
		prev = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(prev, -1);
		
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(a[1]-b[1]));
		pq.offer(new int[] {1,0});
		dist[1] = 0;
		
		while (!pq.isEmpty()) {
			int[] e = pq.poll();
			int node = e[0], distance=e[1];
			if (distance > dist[node]) continue;
			for (Edge edge : edges.get(node)) {
				if (edge.id == ban) continue;
				int next = (edge.a == node) ? edge.b : edge.a;
				if (dist[next] > dist[node] + edge.weight) {
					dist[next] = dist[node] + edge.weight;
					prev[next] = node;
					pq.offer(new int[] {next, dist[next]});
				}
			}
		}
		return dist[N];
	}

	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			edges.add(new ArrayList<>());
		}
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		id= 1;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Edge e = new Edge(id++, a, b, w);
			edges.get(a).add(e);
			edges.get(b).add(e);
		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int a, b, weight;
		
		public Edge(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, planets[][], parents[];
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		long ans = 0;
		int count = 0;
		while(count<N && !pq.isEmpty()) {
			Edge cur = pq.poll();
			int a = cur.a, b=cur.b;
			if(!union(a,b)) continue;
			ans += cur.weight;
		}
		System.out.println(ans);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa==pb) return false;
		parents[pb] = pa;
		return true;
	}
	
	static int find(int x) {
		if(x==parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
		static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		planets = new int[N][4];
		parents = new int[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			planets[i][0] = i;
			planets[i][1] = Integer.parseInt(st.nextToken());
			planets[i][2] = Integer.parseInt(st.nextToken());
			planets[i][3] = Integer.parseInt(st.nextToken());
			parents[i] = i;
		}
		
		// X, Y, Z를 기준으로 각각 정렬하고, 인접한 행성들끼리의 간선만 큐에 추가
		pq = new PriorityQueue<>();
		for(int i=1;i<=3;i++) {
			final int idx = i;  // 람다식 내에서 변수 i를 사용할 수 없음
			Arrays.sort(planets, (a,b)->(a[idx]-b[idx]));
			for(int j=1;j<N;j++) {
				int weight = Math.abs(planets[j-1][idx]-planets[j][idx]);
				pq.offer(new Edge(planets[j-1][0],planets[j][0],weight));
			}
		}
	}

}

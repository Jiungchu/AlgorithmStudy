
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
		int state; // 상태에 따라 이동속도를 다르게 설정
		int next;
		long distance;
		
		public Edge(int state, int next, long distance) {
			this.state = state;
			this.next = next;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(distance, o.distance);
		}

		@Override
		public String toString() {
			return "Edge [state=" + state + ", next=" + next + ", distance=" + distance + "]";
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M;
	static List<List<Edge>> edges;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			edges.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.get(a).add(new Edge(0, b, w));
			edges.get(b).add(new Edge(0, a, w));
		}
		
		distance1 = new long[N+1];
		distance2 = new long[N+1];
		Arrays.fill(distance1, Integer.MAX_VALUE);
		Arrays.fill(distance2, Integer.MAX_VALUE);
	}
	
	// 각각 여우, 늑대의 최단거리
	static long[] distance1, distance2;
			
	static void solution() {
		// 여우, 늑대 각각에 대해 다익스트라 수행
		dijkstra(false);
		dijkstra(true);
		
		int ans = 0;
		for(int i=2;i<=N;i++) {
			if(distance1[i]<distance2[i]) ans++;
		}
//		System.out.println(Arrays.toString(distance1));
//		System.out.println(Arrays.toString(distance2));
		
		System.out.println(ans);
	}
	
	static void dijkstra(boolean isWolf) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		long[] distance;
		long visited[][] = new long[N+1][5];
		for(int i=0;i<=N;i++) {
			for(int j=1;j<5;j++) {
				if(j!=3) visited[i][j] = Long.MAX_VALUE;
			}
		}
		
		// 여우, 늑대에 따라 거리 배열, 속도를 다르게 설정
		if(isWolf) distance = distance2;
		else distance = distance1;
		
		// 간선의 거리 x state가 각 거리가 되도록 설정. 따라서 2는 정속, 1과 4가 번갈아 나오게 설정
		int[] speedMap = {0, 4, 2, 0, 1};
		int initSpeed;
		if(isWolf) initSpeed = 1;
		else initSpeed = 2;
		
		pq.offer(new Edge(initSpeed, 1, 0));
//		System.out.println("#########");
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
//			System.out.println(cur);
			int state = cur.state, node = cur.next;
			long dist = cur.distance;
			if(visited[node][state] < dist) continue;
			
			int nextState = speedMap[state];
			for(Edge e : edges.get(node)) {
				int next = e.next;
				long weight = e.distance;
//				System.out.println("\t"+Arrays.toString(visited[next]));
				long nextDist = dist+ weight*state;
				if(visited[next][nextState]>nextDist) {
					visited[next][nextState] = nextDist;
//					System.out.println("\t"+e);
					if(distance[next] > nextDist)
						distance[next] = nextDist;
					pq.offer(new Edge(nextState, next, dist+ weight*state));
				}
			}
		}
		
	}
	

}


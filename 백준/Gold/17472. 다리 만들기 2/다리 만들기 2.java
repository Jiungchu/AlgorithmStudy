import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge>{
		int a;
		int b;
		int length;

		public Edge(int a, int b, int length) {
			this.a = a;
			this.b = b;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {
			return this.length-o.length;
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N,M, map[][];
	static int islandCount, parents[]; // 섬 관련
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬을 id별로 표시
		islandCount = 0; 
		boolean[][] visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0 && !visited[i][j]) {
					bfs(++islandCount, i,j,visited);
				}
			}
		}
		
		// parents 배열 초기화
		parents = new int[islandCount+1];
		for(int i=1;i<=islandCount;i++) {
			parents[i] = i;
		}
		
		// 간선 만들기. 행, 열에 대해 한 번씩 수행
		pq = new PriorityQueue<>();
		makeEdge(true);
		makeEdge(false);
	}
	

	static int[] dr= {-1,1,0,0}, dc= {0,0,-1,1};
	static void bfs(int id, int r, int c, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		visited[r][c]=true;
		map[r][c] = id;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cr = cur[0], cc=cur[1];
			for(int d=0;d<4;d++) {
				int nr = cr+dr[d], nc = cc+dc[d];
				if(nr>=0&&nr<N&&nc>=0&&nc<M&& !visited[nr][nc] && map[nr][nc]==1) {
					map[nr][nc] = id;
					visited[nr][nc] = true;
					q.offer(new int[] {nr,nc});
				}
			}
		}
	}
	
	static void makeEdge(boolean isRow) {
		int outer = isRow ? N : M;
		int inner = isRow ? M : N;
		
		for (int i = 0; i < outer; i++) {
			int from = -1, to = -1;
			int length = 0;
			
			for (int j = 0; j < inner; j++) {
				int value = isRow ? map[i][j] : map[j][i];
				
				if (value != 0) {
					if (from == -1) {
						from = value;
					} else if (value != from) {
						to = value;
						if (length > 1) {
							pq.offer(new Edge(from, to, length));
						}
						from = to;
					}
					length = 0;
				} else if (from != -1) {
					length++;
				}
			}
		}
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
	
	static void solution() {
		int ans = 0; 
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(union(e.a, e.b)) {
				ans += e.length;
			}
		}
		
		// 모든 섬이 연결되었는지 확인. 연결되지 않으면 -1 return
		int p = find(1);
		for(int i=2;i<=islandCount;i++) {
			if(find(i)!=p) {
				System.out.println(-1); return;
			}
		}
		System.out.println(ans);
	}
	
}


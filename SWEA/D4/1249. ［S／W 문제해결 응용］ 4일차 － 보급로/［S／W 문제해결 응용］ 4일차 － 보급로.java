
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int cost;
		
		public Node() {}
		public Node(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost;
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int t, N, map[][], weights[][];
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(t=1;t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}
	
	static int[] dr = {1,-1,0,0}, dc= {0,0,1,-1};
	
	static void solution() {
		// pq에 r, c, 시작점에서 현재까지 가중치를 저장
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0,0,0));
		weights[0][0] = 0;
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			// 이미 최적 경로가 개선된 경우
			if(weights[n.r][n.c]!=n.cost) continue;
			for(int d=0;d<4;d++) {
				int nr = n.r+dr[d], nc = n.c+dc[d];
				if(nr>=0&&nr<N && nc>=0&&nc<N && n.cost+map[nr][nc]<weights[nr][nc]) {
					pq.add(new Node(nr,nc,map[nr][nc]+n.cost));
					weights[nr][nc] = n.cost+map[nr][nc];
				}
			}
		}
		sb.append("#").append(t).append(" ").append(weights[N-1][N-1]).append("\n");
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		weights = new int[N][N];
		for(int i=0;i<N;i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j=0;j<N;j++) {
				map[i][j] = arr[j]-'0';
				weights[i][j] = Integer.MAX_VALUE;
			}
		}
	}

}

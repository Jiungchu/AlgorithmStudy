
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
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
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[2]-b[2]);
		pq.add(new int[] {0,0,0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0], c = cur[1], weight=cur[2];
			if(weights[r][c]<weight) continue;
			weights[r][c] = weight;
			for(int d=0;d<4;d++) {
				int nr = r+dr[d], nc = c+dc[d];
				if(nr>=0&&nr<N && nc>=0&&nc<N && weight+map[nr][nc]<weights[nr][nc]) {
					pq.add(new int[] {nr,nc,weight+map[nr][nc]});
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

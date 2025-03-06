
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, max, min, maxBlock;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution();
			sb.append("#").append(t).append(" ").append(maxBlock).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution() {
		for(int day=0;day<=max;day++) {
			boolean[][] visited = new boolean[N][N];
			int count = 0;
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(!visited[r][c] && arr[r][c]>day) {
						count++;
						dfs(r,c,day,visited);
					}
				}
			}
			maxBlock = Math.max(maxBlock, count);
		}
	}
	
	static int[] dr = {1,-1,0,0}, dc = {0,0,1,-1};
	
	static void dfs(int r, int c, int day, boolean[][] visited) {
		visited[r][c]=true;
		for(int d=0;d<4;d++) {
			int nr = r+dr[d]; int nc=c+dc[d];
			if(nr>=0&&nr<N && nc>=0&&nc<N && !visited[nr][nc] && arr[nr][nc]>day) {
				dfs(nr,nc,day,visited);
			}
		}
	}

	static void init() throws IOException {
		min = 101;
		max = 0;
        maxBlock=0;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				min = Math.min(min, num);
				max = Math.max(max, num);
			}
		}
	}
}

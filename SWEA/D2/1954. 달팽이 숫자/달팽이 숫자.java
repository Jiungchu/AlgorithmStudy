
import java.io.*;

public class Solution {
	static int[][] grid;
	static boolean[][] visited;
	// 오른쪽, 아래쪽, 왼쪽, 위쪽 순서로 설정
	static int[][] direction = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int n, num;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			grid = new int[n][n];
			visited = new boolean[n][n];
			
			visited[0][0]=true;
			num=1;
			move(0,0,0);
			
			sb.append("#").append(t).append("\n");
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					sb.append(grid[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void move(int r, int c, int d) {
		grid[r][c] = num;
		if(num==n*n) return; // 최대 크기에 도달하면 return
		int nr = r + direction[d][0];
		int nc = c + direction[d][1];
		if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
			num++;
			visited[nr][nc] = true;
			move(nr, nc, d);
		} else {
			// 막혔으면 방향 전환
			move(r, c, (d + 1) % 4);
		}
	}
}

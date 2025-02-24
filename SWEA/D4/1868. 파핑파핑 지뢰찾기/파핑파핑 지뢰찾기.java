
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static char[][] grid;
	static boolean[][] removed;
	static int[][] dir = { { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 } };
	static int n, count;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] != '*' && !removed[i][j]) {
						dfs(i, j);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void init() throws IOException {
		n = Integer.parseInt(br.readLine());
		grid = new char[n][];
		removed = new boolean[n][n];
		count=0;
		for (int i = 0; i < n; i++) {
			grid[i] = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '.')
					count++;
			}
		}
	}

	static void dfs(int r, int c) {
		boolean isZero = true;
		for (int d = 0; d < 8; d++) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && !removed[nr][nc])
				if (grid[nr][nc] == '*') {
					isZero = false; // 0이 아닌 점
					break;
				}
		}
		if (!isZero)
			return;
		removed[r][c]=true;
		for (int d = 0; d < 8; d++) {
			int nr = r + dir[d][0];
			int nc = c + dir[d][1];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && !removed[nr][nc] && grid[nr][nc] != '*') {
				count--;
				removed[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}

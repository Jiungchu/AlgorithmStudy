
import java.io.*;
import java.util.*;

public class Main{

	static String[][] costs;
	static int[][] path;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n;
		int t = 0;
		while ((n = Integer.parseInt(br.readLine())) != 0) {
			t++;
			costs = new String[n][n];
			path = new int[n][n];
			for (int i = 0; i < n; i++) {
				costs[i] = br.readLine().split(" ");
				Arrays.fill(path[i], -1);
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n ; j++) {
					// 값 넣기
					// 기본적으로 위, 왼쪽 값 중 작은 값을 기반으로 넣기
					if (i == 0 && j == 0)
						path[i][j] = Integer.parseInt(costs[i][j]);
					else if (i == 0)
						path[i][j] = Integer.parseInt(costs[i][j]) + path[i][j - 1];
					else if (j == 0)
						path[i][j] = Integer.parseInt(costs[i][j]) + path[i - 1][j];
					else {
						int path1 = path[i - 1][j];
						int path2 = path[i][j - 1];
						path[i][j] = Math.min(path1, path2) + Integer.parseInt(costs[i][j]);
					}
					update(i, j);
				}
			}
			System.out.println("Problem " + t + ": " + path[n - 1][n - 1]);
		}
		br.close();

	}

	// 깊이 우선으로 업데이트
	static void update(int r, int c) {
		int n = path.length;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 갱신이므로 아직 방문하지 않은 점의 경우 업데이트 x
			if (nc >= 0 && nc < n && nr >= 0 && nr < n && path[nr][nc] != -1) {
				int newCost = path[r][c] + Integer.parseInt(costs[nr][nc]);
				if (path[nr][nc] > newCost) {
					path[nr][nc] = newCost;
					update(nr, nc);
				}
			}
		}
	}

}

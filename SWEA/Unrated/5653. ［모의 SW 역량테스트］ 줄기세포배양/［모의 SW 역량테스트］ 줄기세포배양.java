
import java.io.*;
import java.util.*;

public class Solution {
	static int[][] grid, times;
	static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
	static int n, m, k;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			grid = new int[n + k + 1][m + k + 1];
			times = new int[n + k + 1][m + k + 1];
			// n, m 이후에 k/2만큼 크기를 붙이기
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					grid[i + k / 2][j + k / 2] = Integer.parseInt(st.nextToken());
				}
			}
			// 크기가 초반 줄기세포에 비해 매우 크므로 현재 세포가 있는 범위를 설정
			int minr = k / 2;
			int maxr = k / 2 + n;
			int minc = k / 2;
			int maxc = k / 2 + m;
			int count = 0;
			for (int time = 0; time <= k; time++) {
				for (int r = minr; r <= maxr; r++) {
					for (int c = minc; c <= maxc; c++) {
						// 비활성을 1~10, 활성을 11~20, 죽은 세포를 21~30으로 설정
						if (grid[r][c] >= 1 && grid[r][c] <= 10) {
							if (time == 0)
								count++; // 초기 세포
							if (times[r][c] == -time)
								continue; // 방금 태어난 세포
							else if (times[r][c] == -time + 1) { // 이전 time에 태어난 세포
								times[r][c] = 1; // 다시 양수로 전환
							}
							if (times[r][c] == grid[r][c]) {
								times[r][c] = 0;
								grid[r][c] += 10; // 바뀐 후 바로 1초 흐름
							}
							times[r][c]++;
						} else if (grid[r][c] >= 11 && grid[r][c] <= 20) {
							for (int i = 0; i < 4; i++) {
								int nr = r + dr[i], nc = c + dc[i];
								if (grid[nr][nc] == 0) { // 범위를 벗어날 일은 없음
									grid[nr][nc] = grid[r][c] - 10;
									count++;
									minr = Math.min(minr, nr);
									maxr = Math.max(maxr, nr);
									minc = Math.min(minc, nc);
									maxc = Math.max(maxc, nc); // 탐색 범위 업데이트
									times[nr][nc] = -time; // 방금 태어난 세포임을 명시
								} else if (times[nr][nc] == -time) {
									// 방금 태어난 세포가 있다면 더 생명력이 큰 세포가 자리잡기
									grid[nr][nc] = Math.max(grid[nr][nc], grid[r][c]-10);
								}
							}
							if (times[r][c] == grid[r][c] - 10) {
								times[r][c] = 0;
								grid[r][c] += 10;
								count--;
							}
							times[r][c]++;
						}
					}
				}

			}
			System.out.println("#" + t + " " + count);

		}

		br.close();
	}

}

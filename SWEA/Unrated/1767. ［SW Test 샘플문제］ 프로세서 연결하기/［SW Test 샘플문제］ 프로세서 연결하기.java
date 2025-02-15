
import java.io.*;
import java.util.*;

public class Solution {
	static int[][] grid;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static List<int[]> cores;
	static int n, t, maxCount, minLength;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (t = 0; t < T; t++) {
			// 값 입력, cores에 외곽에 위치하지 않은 코어들 저장
			n = Integer.parseInt(br.readLine());
			grid = new int[n][n];
			cores = new ArrayList<>();
			maxCount = 0; minLength = 99999;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1 && i != 0 && j != 0) {
						cores.add(new int[] { i, j });
					}
					grid[i][j] = num;
				}
			}
			dfs(0, 0, 0);
			System.out.println("#" + (t + 1) + " " + minLength);
		}
		br.close();
	}

	static void dfs(int index, int count, int length) {
		// index = 코어 번호, count = 현재 담긴 코어의 수, length = 현재 전선 길이의 합
		if (index == cores.size()) {
			if (count > maxCount) {
				maxCount = count;
				minLength = length;
			} else if (count == maxCount)
				minLength = Math.min(minLength, length);
			return;
		}
		// 가장 많은 코어를 담는 경우는 길이를 저장, 코어의 수가 동일하면 최소값을 저장
		int r = cores.get(index)[0];
		int c = cores.get(index)[1];
		for (int d = 0; d < 4; d++) {
			int currLength = lengthCalculate(r, c, d);
			if (currLength != -1) {
				// 경로가 있는 경우 포함하고 재귀호출
				dfs(index + 1, count + 1, length + currLength);
				removeTrace(r + dr[d] * currLength, c + dc[d] * currLength, d); // 다음 재귀호출을 위해 경로 지우기
			}
		}
		// 해당 core를 연결하지 않는 경우
		dfs(index + 1, count, length);
	}

	static int lengthCalculate(int r, int c, int d) {
		int length = 0;
		// 한 방향으로 쭉 직진
		int nr=r; int nc=c;
		while (true) {
			nr = nr + dr[d];
			nc = nc + dc[d];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
//				System.out.println("#"+r+","+c+" "+nr+" "+nc+ " "+grid[nr][nc]);
				if (grid[nr][nc] == 0) {
					length++;
					grid[nr][nc] = 2; // 전선은 2로 표시
				} else {
					// 길이 막힌 경우, 지금까지 지나온 경로 지우고 -1 return
					removeTrace(nr - dr[d], nc - dc[d], d);
					return -1;
				}
			} else {
				// 맵의 끝에 도달했으므로 연결 가능
				return length;
			}
		}
	}

	// [r,c]에서 d방향의 반대로 core를 만날 때까지 경로를 지우는 함수
	static void removeTrace(int r, int c, int d) {
		while (true) {
			if (grid[r][c] == 1)
				return;

			grid[r][c] = 0;
			r -= dr[d];
			c -= dc[d];
		}
	}
}

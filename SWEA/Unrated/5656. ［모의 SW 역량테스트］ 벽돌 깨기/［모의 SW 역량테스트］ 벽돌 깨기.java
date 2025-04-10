
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int t, N, W, H, blockCount, map[][], max;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
			int[][] newMap = new int[H][W];
			for (int j = 0; j < H; j++) {
				newMap[j] = Arrays.copyOf(map[j], W);
			}
			solution(0, 0, newMap);
			sb.append("#").append(t).append(" ").append(blockCount - max).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void solution( int count, int breaked, int[][] curMap) {

		if (count == N || breaked==blockCount) {
			max = Math.max(max, breaked);
			return;
		}
		for (int i = 0; i < W; i++) {
			int start = 0; // 벽돌이 깨지기 시작하는 지점 찾기
			while (start < H && curMap[start][i] == 0)
				start++;
			if (start != H) {
				int[][] newMap = new int[H][W];
				for (int j = 0; j < H; j++) {
					newMap[j] = Arrays.copyOf(curMap[j], W);
				}
				int next = go(start, i, newMap);
				down(newMap);
				solution(count + 1, breaked + next, newMap);
			}
		}
	}

	static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };

	static int go(int r, int c, int[][] curMap) {
		int num = curMap[r][c];
		int breaked = 1;
		curMap[r][c] = 0;
		for (int i = 1; i < num; i++) {
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d] * i, nc = c + dc[d] * i;
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && curMap[nr][nc] != 0) {
					breaked += go(nr, nc, curMap);
				}
			}
		}
		return breaked;
	}

	static void down(int[][] curMap) {
		for (int c = 0; c < W; c++) {
			int index = H - 1;
			for (int r = H - 1; r >= 0; r--) {
				if (curMap[r][c] != 0) {
					curMap[index--][c] = curMap[r][c];
					if (index + 1 != r)
						curMap[r][c] = 0;
				}
			}
		}
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		max = 0;

		map = new int[H][W];
		blockCount = 0;
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num != 0)
					blockCount++;
			}
		}
	}

}

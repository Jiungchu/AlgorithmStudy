
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;

	static void init() throws IOException {
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		// h가 세로 길이, w가 가로 길이이므로 크기를 아래와 같이 만들기
		map = new int[H][W];
		// 점프 횟수에 따라 visited를 따로 관리
		visited = new boolean[K + 1][][];
		for (int i = 0; i < K + 1; i++) {
			visited[i] = new boolean[H][W];
		}

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
	}

	static void solution() {
		Queue<int[]> q = new ArrayDeque<>();
		int[] dr = { 1, -1, 0, 0, -2, -1, 1, 2, 2, 1, -1, -2 };
		int[] dc = { 0, 0, 1, -1, 1, 2, 2, 1, -1, -2, -2, -1 };

		// 위치 정보, 점프 횟수, 이동 거리 정보를 관리
		q.offer(new int[] { 0, 0, 0, 0 });
		visited[0] = new boolean[H][W];
		visited[0][0][0] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int k = curr[2];
			int count = curr[3];
			if (r == H - 1 && c == W - 1) {
				System.out.println(count);
				return;
			}
			// 기본 4방 탐색,knight 탐색을 한 번에 수행
			for (int i = 0; i < 12; i++) {
				if (k >= K && i >= 4)
					break; // 남은 점프 횟수가 없다면, 사방탐색이 끝난 후 break
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 1) {
					if (i < 4 && !visited[k][nr][nc]) {
						q.offer(new int[] { nr, nc, k, count + 1 });
						visited[k][nr][nc] = true;
					} else if (i >= 4 && !visited[k + 1][nr][nc]) { 
						// knight 탐색. 점프 횟수를 하나 늘리고 큐에 넣기
						q.offer(new int[] { nr, nc, k + 1, count + 1 });
						visited[k + 1][nr][nc] = true;
					}
				}
			}
		}
		// 도달하지 못한 경우
		System.out.println(-1);
	}

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

}

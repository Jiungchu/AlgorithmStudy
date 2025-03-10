
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, M, D, max;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		init();
		solution(0, 0, 0);
		System.out.println(max);
		br.close();
	}

	// 재귀를 통한 조합으로 궁수의 위치 3곳 정하기
	static void solution(int index, int mask, int count) {
		if (count == 3) {
			max = Math.max(max, play(mask));
			return;
		}
		if (M - index + count < 3)
			return; // 가지치기
		// index번 자리에 궁수를 배치한 경우
		solution(index + 1, mask | (1 << index), count + 1);
		// 배치하지 않은 경우
		solution(index + 1, mask, count);
	}

	static int play(int mask) {
		int defeat = 0;
		List<Integer> archer = new ArrayList<>();
		// 궁수의 위치 확인
		for (int i = 0; i < M; i++) {
			if ((mask & (1 << i)) != 0)
				archer.add(i);
		}
		int time = 0;
		boolean[][] defeated = new boolean[N][M];
//		System.out.println("###");
//		System.out.println(archer);
		while (time < N) {
			List<int[]> curr = new ArrayList<>();
			for (int index : archer) {
				int[] currDefeat = bfs(N - time, index, defeated);
//				if (currDefeat != null)
//					System.out.println(currDefeat[0] + " " + currDefeat[1]);
				if (currDefeat != null) {
					curr.add(currDefeat);
				}
			}
			for (int[] enemy : curr) {
				if (!defeated[enemy[0]][enemy[1]]) {
					defeated[enemy[0]][enemy[1]] = true;
					defeat++;
				}
			}
			time++;
//			System.out.println(defeat);
//		System.out.println(archer);
//		for(int i=0;i<N;i++) {
//			System.out.println(Arrays.toString(defeated[i]));
//		}
//		System.out.println();
		}
		return defeat;
	}

	// 아래쪽은 탐색할 필요 x
	static int[] dr = { 0, -1, 0 }, dc = { -1, 0, 1 };

	static int[] bfs(int r, int c, boolean[][] defeated) {
		Queue<int[]> q = new ArrayDeque<>();
		// 현재 궁수의 바로 위 위치부터 탐색
		q.offer(new int[] { r - 1, c, 1 });
		boolean[][] visited = new boolean[N][M];
		visited[r - 1][c] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cr = curr[0], cc = curr[1];
			if (map[cr][cc] == 1 && !defeated[cr][cc])
				return new int[] { cr, cc }; // 해당 점에 적이 있으면 바로 처치
			for (int d = 0; d < 3; d++) {
				int nr = cr + dr[d], nc = cc + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && getDistance(nr, nc, r, c) <= D) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		return null;
	}

	static int getDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		// 위상 정렬로 해결하기 위해, 단방향 간선 저장, 진입 차수 저장

		// 적이 존재하는 위치를 리스트로 관리
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
			}
		}
	}

}

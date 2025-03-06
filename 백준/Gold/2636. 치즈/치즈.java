
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, M, lastCount, time, nxtr, nxtc; // 다음 탐색 시작 지점
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		init();
		solution();
		System.out.println(time-1);
		System.out.println(lastCount);
		br.close();
	}

	static void solution() {
		time = 0;
		while (nxtr != -1) {
			bfs(nxtr,nxtc);
			time++;
//			for(int i=0;i<N;i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
//			System.out.println();
		}
	}

	static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };

	static void bfs(int r, int c) {
		visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;
		nxtr = nxtc = -1;
		int count = 1;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cr = curr[0], cc = curr[1];
			arr[cr][cc] = 0;
			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d], nc = cc + dc[d];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
					if (arr[nr][nc] == -time || arr[nr][nc] == 0) {
						if(arr[nr][nc]==-time) count++;
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });
					} else if (arr[nr][nc] == 1) {
						if (nxtr == -1) {
							nxtr = nr;
							nxtc = nc; // 다음 탐색의 출발지점 설정
						}
						arr[nr][nc] = -time - 1; // 다음 탐색의 대상이 되는 지점은 음스로 설정로 설정
					}
				}
			}
		}
		lastCount = count;
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		nxtr = nxtc = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

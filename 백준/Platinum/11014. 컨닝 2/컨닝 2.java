
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main{

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, M, broken, nodeNum[][];
	static boolean canSit[][];
	static List<List<Integer>> linked;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution();
		}
		System.out.println(sb);
	}

	// 연결될 수 있는 6방향
	static int[][] dirs = { { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };

	static void solution() {
		// 6방향 탐색하며 점 연결
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c += 2) { // 이분 매칭을 위해 홀수/짝수 그룹으로 나눠서 연결
				if (canSit[r][c]) {
					for (int[] dir : dirs) {
						int nr = r + dir[0], nc = c + dir[1];
						// 노드 일련번호를 사용해서 연결 처리
						if (nr >= 0 && nr < N && nc >= 0 && nc < M && canSit[nr][nc]) {
							linked.get(nodeNum[r][c]).add(nodeNum[nr][nc]);
						}
					}
				}
			}
		}
		
		int max = N*M-broken-matching();
		sb.append(max).append("\n");
	}

	static int visited[];
	static int matched[];

	static int matching() {
		visited = new int[N * M];
		// 노드 i와 연결된 노드 정보 j를 저장. 연결되지 않았다면 -1
		matched = new int[N * M];
		Arrays.fill(matched, -1);
		int point = 0;
		int size = 0;
		for(int r=0;r<N;r++) {
			for(int c=0;c<M;c+=2) {
				point++;
				size += dfs(nodeNum[r][c], point);
			}
		}
		return size;
	}

	static int dfs(int node, int point) {
		if (visited[node] != point) {
			visited[node] = point; // visited는 짝수 그룹만을 대상으로 함
			
			for(int next : linked.get(node)) {
				if (matched[next] == -1 || dfs(matched[next], point) == 1) {
					// 홀수 그룹 정점들을 대상으로만 정보를 저장하므로, 한쪽만 연결해줘도 괜찮음
					// dfs 함수는 짝수 그룹에서만 호출됨
					matched[next] = node;
					return 1;
				}

			}
		}

		return 0;
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		canSit = new boolean[N][M];
		nodeNum = new int[N][M];
		linked = new ArrayList<>();

		broken = 0;
		int num = 0;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = str.charAt(j);
				nodeNum[i][j] = num++; // 노드의 일련번호를 저장
				if (c == '.')
					canSit[i][j] = true;
				else
					broken++;
				linked.add(new ArrayList<>());
			}
		}
	}

}

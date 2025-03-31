
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, t, minSum, maxConnected;
	static int[][] map;
	static List<int[]> cores;
	// 오른쪽부터 시계방향으로 돌기
	static int[] dr = { 0, 1, 0, -1 }, dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
			solution(0, 0, 0);
			sb.append("#").append(t).append(" ").append(minSum).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution(int index, int connected, int length) {
		if(connected==maxConnected && (minSum>length)) {
				minSum= length;  // 최대로 연결된 경우 짧은 쪽으로 update
		}
		else if(connected>maxConnected) { // 연결된 코어의 수가 늘어나면 무조건 update
			maxConnected = connected;
			minSum = length;
		}
		// 모든 코어를 다 고려한 경우 return
		if(index>=cores.size()) return;
		
		int r = cores.get(index)[0], c = cores.get(index)[1];
		for(int d=0;d<4;d++) {
			int curLen = drawLine(r,c,d);
			if(curLen>0) {
				solution(index+1, connected+1, length+curLen);
				removeLine(r,c,d); // 백트래킹을 위해 경로 지우기
			}
		}
		// 연결하지 않은 경우의 수로 넘기기
		solution(index+1, connected, length);
	}

	// 음수로 line을 표현
	static int drawLine(int r, int c, int d) {
		int length = 0;
		int nr = r + dr[d], nc = c + dc[d];
		while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			if (map[nr][nc] != 0) {
				removeLine(nr, nc, (d + 2) % 4); // 거꾸로 돌아가서 라인 지우기
				return -1;
			}
			map[nr][nc]--;
			nr += dr[d];
			nc += dc[d];
			length++;
		}
		return length;
	}
	
	// 라인 지우기
	static void removeLine(int r, int c, int d) {
		int nr = r + dr[d], nc = c + dc[d];
		while (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 1) {
			map[nr][nc]++;
			nr += dr[d];
			nc += dc[d];
		}
	}

	static void arrayShow(int[][] arr) {
		System.out.println("#######");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		minSum = Integer.MAX_VALUE;
		maxConnected = 0;
		map = new int[N][N];
		cores = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num == 1 && i != 0 && j != 0 && i != N - 1 && j != N - 1) { // 가장자리 core는 아예 고려 x
					cores.add(new int[] { i, j });
				}
			}
		}

	}

}

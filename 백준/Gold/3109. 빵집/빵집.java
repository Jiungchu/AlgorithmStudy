
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,0,1};
	static int R, C, ans;

	public static void main(String[] args) throws IOException {

		init();
		solution();
		
		System.out.println(ans);
		br.close();
	}

	static void solution() {
		for(int r=0;r<R;r++) {
			if(dfs(r,0)) ans++;
		}
	}
	
	static boolean dfs(int r, int c) {
		visited[r][c] = true;
		if(c==C-1) {
			// 끝까지 도달하면 연결 성공
			return true;
		}
		// 오른쪽 위로 갈 수 있으면 무조건 이동, 다음은 옆으로, 다음은 오른쪽 아래로 이동
		int nc=c+1;
		for(int d=0;d<3;d++) {
			int nr = r+dr[d];
			if(nr<0 || nr>=R || map[nr][nc] == 'x' || visited[nr][nc]) continue;
			if(dfs(nr,nc)) return true;
		}
		return false;
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
	}
}

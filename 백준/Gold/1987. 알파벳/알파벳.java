
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int R,C,maxLen; // 다음 탐색 시작 지점
	static char[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		init();
		visited[arr[0][0]-'A']=true;
		dfs(0, 0, 1);
		System.out.println(maxLen);
		br.close();
	}

	static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };

	static void dfs(int r, int c, int count) {
		maxLen = Math.max(maxLen, count);
		for(int d=0;d<4;d++) {
			int nr = r+dr[d], nc=c+dc[d];
			if(nr>=0&&nr<R && nc>=0&&nc<C && !visited[arr[nr][nc]-'A']) {
				visited[arr[nr][nc]-'A']=true;
				dfs(nr, nc, count+1);
				visited[arr[nr][nc]-'A']=false;
			}
		}
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][];
		visited = new boolean[26];
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
	}
}

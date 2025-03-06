
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int R,C,maxLen; 
	static char[][] arr;

	public static void main(String[] args) throws IOException {

		init();
		dfs(0, 0, 1, 1 << (arr[0][0] - 'A'));
		System.out.println(maxLen);
		br.close();
	}

	// dfs 사용
	static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
	static void dfs(int r, int c, int count, int mask) {
		maxLen = Math.max(maxLen, count);
		for(int d=0;d<4;d++) {
			int nr = r+dr[d], nc=c+dc[d];
			if(nr>=0&&nr<R && nc>=0&&nc<C && (mask & (1<<(arr[nr][nc]-'A')))==0) {
				dfs(nr, nc, count+1, mask|(1<<(arr[nr][nc]-'A')));
			}
		}
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][];
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
	}
}

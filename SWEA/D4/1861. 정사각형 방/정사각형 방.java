
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int[] dr = {1,-1,0,0}, dc = {0,0,1,-1}, length;
	static int maxIndex, maxLength, N;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution();
			sb.append("#").append(t).append(" ").append(maxIndex).append(" ").append(maxLength).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int currLen = dfs(i,j);
				if(currLen>maxLength) {
					maxLength = currLen;
					maxIndex = map[i][j];
				} else if(currLen==maxLength) maxIndex = Math.min(map[i][j],maxIndex);
			}
		}
	}
	
	static int dfs(int r, int c) {
		int curr = map[r][c];
		if(length[curr]!=0) return length[curr]; // 저장된 값이 있다면 바로 사용
		
		for(int d=0;d<4;d++) {
			int nr=r+dr[d];
			int nc=c+dc[d];
			if(nr>=0&&nr<N && nc>=0&&nc<N && map[nr][nc]==curr+1) {
				// 이동 가능한 곳이 있다면 재귀호출, 길이 1 증가
				length[curr] =  1 + dfs(nr, nc);
				return length[curr];
			}
				
		}
		// 상하좌우에 이동할 수 있는 곳이 없는 경우
		length[curr] = 1;
		return 1;
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		length = new int[N* N+1]; // 1차원 배열에 값을 저장
		maxIndex = Integer.MAX_VALUE; maxLength = 0;
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

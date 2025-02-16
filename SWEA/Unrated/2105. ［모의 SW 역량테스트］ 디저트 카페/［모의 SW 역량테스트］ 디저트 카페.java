
import java.io.*;
import java.util.*;

public class Solution {
	static int grid[][];
	static int[][] direction = {{1,1},{1,-1},{-1,-1},{-1,1}};
	static boolean[] eated;
	static int n, maxCount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			grid = new int[n][n];
			for (int r = 0; r < n; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < n; c++) {
					grid[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 초기화하고 탐색 시작
			maxCount = -1;
			// 항상 시작점에서 우측 아래로만 탐색 시작. 중복 방지
			for(int r=0;r<n;r++) {
				for(int c=0;c<n;c++) {
					if(r==0&&(c==0 || c==n-1) || r==n-1 &&(c==0 || c==n-1)) continue;
					eated = new boolean[101]; // 디저트 종류
					dfs(r,c,r,c,0,0);
				}
			}
			System.out.println("#"+(t+1)+" "+maxCount);
		}
		br.close();
	}

	static void dfs(int startR, int startC, int currR, int currC, int d, int count) {
//		System.out.println(currR+" "+currC);
		if(startR==currR && startC==currC && count>1) { // 다시 제자리로 돌아온 경우
			if(maxCount<count) maxCount = count; // 어떻게 해도 maxCount가 안되는 경우 가지치기도 가능할 듯
			return;
		}
		if(d==0 || d==1) {
			for(int i=0;i<2;i++) {
				// 직진 경로, 우회전 경로를 모두 고려
				recursionCall(startR, startC, currR, currC, d+i, count);
			}
		} else if(d==2) {
			// 좌측 위로 향하는 대각선은 출발점으로 돌아갈 길이 생기면 바로 돌아가야 함
			// 즉 우측 위 대각선으로 이동해야 함. 이때는 출발점과 row+col이 일정함
			if(startR+startC == currR+currC) {
				recursionCall(startR, startC, currR, currC, d+1, count);
			} else recursionCall(startR, startC, currR, currC, d, count);
		} else {
			// d==3인 경우 직진만 고려
			recursionCall(startR, startC, currR, currC, d, count);
		}
	}
	static void recursionCall(int sr, int sc, int r, int c, int d, int count) {
		int nr = r+direction[d][0];
		int nc = c+direction[d][1];
		if(nr>=0&&nr<n && nc>=0&&nc<n &&!eated[grid[nr][nc]]) {
			eated[grid[nr][nc]] = true;
			dfs(sr, sc, nr, nc, d, count+1);
			eated[grid[nr][nc]] = false;
		}
	}
}

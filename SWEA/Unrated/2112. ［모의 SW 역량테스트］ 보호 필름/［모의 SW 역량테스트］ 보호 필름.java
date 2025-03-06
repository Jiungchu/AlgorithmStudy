
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int d, w, k, t;
	static int[][] grid;
	static int[] row, col;
	static boolean success;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
		br.close();

	}
	
	static void solution() {
		if(k==1) { // k==1이면 바로 출력
			sb.append("#").append(t).append(" ").append(0).append("\n");
			return;
		}
		for (int i = 0; i <= k; i++) {
			dfs(0, 0, i);
			if (success) {
				sb.append("#").append(t).append(" ").append(i).append("\n");
				return;
			}
		}
	}

	static void dfs(int curr, int count, int maxCount) {
		// 남은 행을 다 바꿔도 maxCount가 되지 앟는 경우
		if (success || count + (d - curr) < maxCount)
			return;
		if (count == maxCount) {
			if (check())
				success = true;
		} else {
			for (int i = curr; i < d; i++) {
				if (row[i] != -1)
					continue;
				for (int type : new int[] { 0, 1 }) {
					row[i] = type;
					dfs(i + 1, count + 1, maxCount); // 현재 바꾼 행의 다음 행부터 바꾸기
				}
				row[i] = -1;
			}
		}
	}

	static boolean check() {
		int pass = 0;
		int[] currCol = Arrays.copyOf(col,col.length);
//		System.out.println(Arrays.toString(currCol));
		for(int r=0;r<d;r++) {
			if(row[r]==-1) continue;
			for(int c=0;c<w;c++) {
				if(row[r]==1) currCol[c] = currCol[c] | (1<<r); 
				else currCol[c] &= ~(1<<r); 
			}
		}
//		System.out.println(Arrays.toString(currCol));
		for(int c=0;c<w;c++) {
			int col = currCol[c];
			int checkVal = (1<<k)-1;
			while(checkVal < (1<<d)-1) {
				if(((col&checkVal)==0) || (col&checkVal)==checkVal) {
					pass++;
					break;
				}
				checkVal <<=1;
			}
			if(pass != c+1) return false;
		}
		return true; 
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		grid = new int[d][w];
		row = new int[d];
		col = new int[w];
		Arrays.fill(row, -1);
		success = false;
		for (int i = 0; i < d; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 열 정보를 정수 하나로 관리
		for(int j=0;j<w;j++) {
			int hashVal = 0;
			// 뒤에서부터 저장. i번 열의 정보는 i번 비트에 저장됨
			for(int i=d-1;i>=0;i--) {
				hashVal <<=1;
				hashVal+=grid[i][j];
			}
			col[j] = hashVal;
		}
	}
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] grid,sums;
	static int n,m,max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			grid = new int[n][n];
			sums = new int[n+1][n+1];
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					grid[i][j]= Integer.parseInt(st.nextToken());
				}
			}
			// 오른쪽 아래에 
			for(int i=1;i<n+1;i++) {
				for(int j=1;j<n+1;j++) {
					// i, j는 1부터 시작하므로 아래와 같이 누적합 구하기
					sums[i][j] = grid[i-1][j-1]+sums[i-1][j]+sums[i][j-1]-sums[i-1][j-1]; 
				}
			}
			max = 0;
			for(int i=m;i<n+1;i++) {
				for(int j=m;j<n+1;j++) {
					int curr = sums[i][j]-sums[i-m][j]-sums[i][j-m]+sums[i-m][j-m];
					max = Math.max(max, curr);
				}
			}
			sb.append("#").append(t).append(" ").append(max).append("\n");
			
		}
		System.out.println(sb.toString());
		br.close();
	}

}

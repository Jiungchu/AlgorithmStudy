import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][n];
		int[][] sums = new int[n+1][n+1];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 누적합 구하기
		for(int i=1;i<n+1;i++) {
			for(int j=1;j<n+1;j++) {
				sums[i][j] = arr[i-1][j-1] + sums[i-1][j] + sums[i][j-1] - sums[i-1][j-1];
			}
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int sum = sums[r2][c2] - sums[r1-1][c2] - sums[r2][c1-1] + sums[r1-1][c1-1];
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}

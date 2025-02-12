
import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] dp = new int[2][n];
			String[] top = br.readLine().split(" ");
			String[] bottom = br.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				int numTop = Integer.parseInt(top[i]);
				int numBottom = Integer.parseInt(bottom[i]);
				dp[0][i] += numTop;
				dp[1][i] += numBottom;
				int prev1 = 0;
				int prev2 = 0;
				int prev3 = 0;
				int prev4 = 0;
				if (i >= 1) {
					prev1 = dp[0][i - 1];
					prev2 = dp[1][i - 1]; // 바로 전 칸
				}
				if (i >= 2) {
					prev3 = dp[0][i - 2];
					prev4 = dp[1][i - 2]; // 두 번째 전 칸
				}
				dp[0][i] += Math.max(prev2, prev4); // 위쪽 칸
				dp[1][i] += Math.max(prev1, prev3); // 아래쪽 칸

			}
//			for(int i=0;i<2;i++) {
//				System.out.println(Arrays.toString(dp[i]));
//			}
			System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
		}
		
		br.close();

	}

}

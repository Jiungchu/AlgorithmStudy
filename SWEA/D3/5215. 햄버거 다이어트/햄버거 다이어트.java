
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int[][] ingredients;
	static boolean[] used;
	static int n, l, maxScore, t;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solution() {
		int[] dp = new int[l + 1];
		maxScore = 0;
		// 뒤에서부터 돌기
		for (int[] item : ingredients) {
			for (int i = l; i >= 0; i--) {
				int score = item[0];
				int calories = item[1];
				if (i >= calories)
					dp[i] = Math.max(dp[i], dp[i - calories] + score);
			}
		}
		sb.append("#").append(t).append(" ").append(dp[l]).append(" ").append("\n");
		
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		ingredients = new int[n][2];
		used = new boolean[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				ingredients[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

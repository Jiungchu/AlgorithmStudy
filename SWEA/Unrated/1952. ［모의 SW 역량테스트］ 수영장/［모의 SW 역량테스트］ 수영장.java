
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] fee, plan;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution();
			sb.append("#").append(t).append(" ").append(Math.min(dp[1][11], fee[3])).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution() {
		for (int i = 0; i < 12; i++) {
			int times = plan[i];
			if (i != 0) {
				dp[0][i] += dp[0][i - 1]; // 저번 달 요금 누적
			}
			int curr = Math.min(fee[0] * times, fee[1]);
			dp[0][i] += curr; // 일권, 월권 중 최적 값을 저장
			// 3달 이용권을 고려하는 경우. 여기에 최적 값을 저장
			if (i < 3)
				dp[1][i] = Math.min(fee[2], dp[0][i]);
			else {
				// 3달치 이용권을 더한 값과 3달 이용권 값을 비교해서 싼 값 넣기
				dp[1][i] = Math.min(dp[1][i - 3] + fee[2],dp[1][i-1]+curr);
			}
		}
	}

	static void init() throws IOException {
		fee = new int[4];
		plan = new int[12];
		dp = new int[2][12];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			fee[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 12; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
	}
}

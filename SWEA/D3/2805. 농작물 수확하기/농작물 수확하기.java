
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, ans;
	static int[][] farm;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution(0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution(int index, int height) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				// 중간 지점과의 거리가 N/2 이하인 점만 탐색
				if(Math.abs(N/2-i)+Math.abs(N/2-j)<=N/2) {
					ans += farm[i][j];
				}
			}
		}
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		ans = 0;
		farm = new int[N][N];
		for(int i=0;i<N;i++) {
			char[] nums = br.readLine().toCharArray();
			for(int j=0;j<N;j++) {
				farm[i][j] = nums[j]-'0';
			}
		}
	}
}

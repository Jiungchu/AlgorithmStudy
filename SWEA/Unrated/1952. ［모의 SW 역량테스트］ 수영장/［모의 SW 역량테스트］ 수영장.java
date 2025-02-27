
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] fee, plan;
	static int minPrice;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution(0,0);
			sb.append("#").append(t).append(" ").append(Math.min(minPrice, fee[3])).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution(int curr, int price) {
		if(curr>=12) {
			minPrice = Math.min(minPrice, price);
			return;
		}
		// 1달 이용권 가격과, 1달동안 1일 이용권을 사용했을 때 가격을 비교
		int currPrice = Math.min(plan[curr]*fee[0], fee[1]);
		solution(curr+1, price+currPrice);
		
		// 3달 이용권 가격 추가
		solution(curr+3, price+fee[2]);
	}

	static void init() throws IOException {
		fee = new int[4];
		plan = new int[12];
		StringTokenizer st = new StringTokenizer(br.readLine());
		minPrice = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			fee[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 12; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
	}
}

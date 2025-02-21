
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] ingredients;
	static long minDiff=Long.MAX_VALUE;
	static int n;
	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		ingredients = new int[n][2];
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<2;j++) {
				ingredients[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		findCombination(0, 0, 1, 0);
		System.out.println(minDiff);
		br.close();
	}
	
	static void findCombination(int cnt, int use, long sour, long bitter) {
		if(cnt==n) {
			if(use>=1) minDiff = Math.min(minDiff, Math.abs(sour-bitter));
			return;
		}
		int currSour = ingredients[cnt][0];
		int currbitter = ingredients[cnt][1];
		// 해당 재료를 넣은 경우, 넣지 않은 경우를 각각 탐색
		findCombination(cnt+1, use+1, sour*currSour, bitter+currbitter);
		findCombination(cnt+1, use, sour, bitter);
	}
}

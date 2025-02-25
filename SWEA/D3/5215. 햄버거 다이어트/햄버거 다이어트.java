
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] ingredients;
	static int[] used;
	static int n, l, maxScore;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			for(int i=n;i>=1;i--) {
				// i는 뽑는 재료의 수
				used = new int[n];
				for(int j=n-1;j>=n-i;j--) {
					used[j]=1;
				}
				comb();
			}
			sb.append("#").append(t).append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	// np를 사용
	static void comb() {
		while(true) {
			int score = 0, calories = 0;
//			System.out.println(Arrays.toString(used));
			for (int i = 0; i < n; i++) {
				if (used[i] == 1) {
					score += ingredients[i][0];
					calories += ingredients[i][1];
				}
			}
			// 모든 재료가 선택되고 칼로리가 초과되지 않았다면 maxScore와 비교
			if (calories <= l) 
				maxScore = Math.max(maxScore, score);
			// 더 이상 선택할 수 있는 조합이 없을 때
			if(!np()) break; 
		}
	}

	static boolean np() {
		// 꼭대기 찾기
		int i=n-1;
		while(i>0 && used[i-1]>=used[i]) i--;
		
		if(i==0) return false;
		
		// i-1보다 큰 j 찾기
		int j = n-1;
		while(used[i-1]>=used[j]) j--;
		swap(i-1,j);
		
//		i-1 뒤로 오름차순 정렬
		int k=n-1;
		while(i<k) swap(i++, k--);
		
		return true;
	}

	static void swap(int i, int j) {
		int temp = used[i];
		used[i] = used[j];
		used[j] = temp;
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		ingredients = new int[n][2];
		maxScore = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				ingredients[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

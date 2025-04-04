
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, rgbCost[][];
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		// i번 집을 각각 r, g, b로 칠했을 때의 최소 비용을 dp에 저장
		int[][] dp = new int[N][3];
		// 초기화
		dp[0][0] = rgbCost[0][0];
        dp[0][1] = rgbCost[0][1];
        dp[0][2] = rgbCost[0][2];
        
        // i-1에 칠해진 색을 제외한 최소값을 사용해서 dp 업데이트
        for (int i = 1; i < N; i++) {
            dp[i][0] = rgbCost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = rgbCost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = rgbCost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        
        int ans = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
        System.out.println(ans);
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		rgbCost = new int[N][3];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				rgbCost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, map[][], dp[][][];
	
	public static void main(String[] args) throws IOException{
		init();
		solution();
	}
	
	static void solution() {
		// 행, 열, 방향별로 dp 시용 
		int[][][] dp = new int[N][N][3];
		dp[0][1][0] = 1;

		for (int r=0; r<N;r++) {
		    for (int c=2; c<N;c++) {
		        if (map[r][c] == 1) continue;

		        // 이전 시점에 가로, 대각선이었을 경우 가로 가능 
		        dp[r][c][0] = dp[r][c-1][0]+dp[r][c-1][2];

		        // 이전 시점에 세로, 대각선이었을 때 세로 가능
		        if (r>0)
		            dp[r][c][1] = dp[r-1][c][1]+dp[r-1][c][2];

		        // 대각선은 모든 경우에서 가능. 단 벽을 긁으면 안됨
		        if (r > 0 && map[r-1][c] == 0 && map[r][c-1] == 0)
		            dp[r][c][2] = dp[r-1][c-1][0] + dp[r-1][c-1][1] + dp[r-1][c-1][2];
		    }
		}

		int result = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
		System.out.println(result);
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

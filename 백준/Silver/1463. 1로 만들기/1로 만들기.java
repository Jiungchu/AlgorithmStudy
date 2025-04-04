
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		int[] dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[N]=0;
 		for(int n = N; n>=1; n--) {
 			int next = dp[n]+1;
 			dp[n-1] = Math.min(dp[n-1], next);
 			if(n%3 == 0)
 				dp[n/3] = Math.min(dp[n/3], next);
 			if(n%2 == 0 )
 				dp[n/2] = Math.min(dp[n/2], next);
 		}
		System.out.println(dp[1]);
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
	}

}

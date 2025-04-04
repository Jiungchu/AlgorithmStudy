
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, dp[][];
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			init();
			System.out.println(comb(M,N));
		}
	}
	
	static int comb(int n, int r) {
		if(r==0 || r == n) return 1;
		if(dp[n][r]>0) return dp[n][r];
		return dp[n][r]=comb(n-1,r-1)+comb(n-1,r);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[M+1][M+1];
	}

}


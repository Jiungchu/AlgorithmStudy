
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, K, dp[], values[];
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		dp[0]=1; // 초기화
		for(int i=0;i<N;i++) {
			for(int v=values[i];v<=K;v++) {
				dp[v] += dp[v-values[i]];
			}
//			System.out.println(Arrays.toString(dp));
		}
		System.out.println(dp[K]);
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[K+1];
		values= new int[N];
		for(int i=0;i<N;i++) {
			values[i] = Integer.parseInt(br.readLine());
		}
	}
}

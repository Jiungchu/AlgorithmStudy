
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, K, items[][], dp[];
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		dp = new int[K+1];
		for(int i=0;i<N;i++) {
			int[] item = items[i];
			int w = item[0], v=item[1];
			for(int j=K; j>=w;j--) {
				dp[j] = Math.max(dp[j], dp[j-w]+v);
			}
		}
		System.out.println(dp[K]);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		items = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
	}

}

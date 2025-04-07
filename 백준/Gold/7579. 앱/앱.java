
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, sum;
	static int[] memories, costs, dp;
	
	public static void main(String[] args) throws IOException{
		init();
		solution();
	}
	
	static void solution() {
		for(int i=0;i<N;i++) {
			int mem = memories[i], cost = costs[i];
			// i번째 item까지 고려하고, cost를 j만큼 사용했을 때 얻을 수 있는 메모리의 최대값을 저장
			for(int j=sum;j>=cost;j--) {
				dp[j] = Math.max(dp[j], dp[j-cost]+mem); 
			}
		}
		int ans = 0;
		for(int i=0;i<=sum;i++) {
			if(dp[i]>=M) {
				ans=i; break;
			}
		}
		System.out.println(ans);
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memories = new int[N];
		costs = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			memories[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		sum=0;
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			costs[i] = num;
			sum += num;
		}
		dp = new int[sum+1];
	}
}

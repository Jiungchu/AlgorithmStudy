
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int t, N, ans, dist[][];
	
	public static void main(String[] args) throws IOException {
		int T= Integer.parseInt(br.readLine());
		for(t=1; t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}
	
	static void solution() {
		for(int k=0;k<N;k++) {
//			System.out.println("k : "+k);
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
//				System.out.println(Arrays.toString(dist[i]));
			}
		}
		
		for(int i=0;i<N;i++) {
			int sum = 0;
			for(int j=0;j<N;j++) {
				sum += dist[i][j];
			}
			ans = Math.min(ans, sum);
		}
		sb.append("#").append(t).append(" ").append(ans).append("\n");
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		ans=Integer.MAX_VALUE;
		
		dist = new int[N][N];
		for(int i=0;i<N;i++) {
			Arrays.fill(dist[i], 99999);
			dist[i][i] = 0;
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				if(i!=j && num != 0) dist[i][j] = num;
			}
		}
	}

}


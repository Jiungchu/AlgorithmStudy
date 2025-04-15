
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int t, ans, N, index[];
	
	public static void main(String[] args) throws IOException {
		int T= Integer.parseInt(br.readLine());
		for(t=1; t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}
	
static void solution() {
	int maxLen = 1;
	int curLen = 1;
	for(int i=2; i<=N; i++) {
		if(index[i-1] < index[i]) {
			curLen++;
			maxLen = Math.max(maxLen, curLen);
		} else {
			curLen = 1;
		}
	}
	ans = N - maxLen;
	sb.append("#").append(t).append(" ").append(ans).append("\n");
}

	

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		index = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = 0;
		for(int i=1;i<=N;i++) {
			int num = Integer.parseInt(st.nextToken());
			index[num] = i;
		}
		
	}

}



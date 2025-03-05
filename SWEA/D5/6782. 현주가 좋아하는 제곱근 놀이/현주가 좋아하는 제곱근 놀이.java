
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static long N;
	static int ans;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution() {
		while(N!=2) {
			long sqrt = (int)Math.sqrt(N);
			if(N==sqrt*sqrt) {
				ans++;
				N=sqrt;
			} else {
				long num = (sqrt+1)*(sqrt+1); // 다음 제곱수 
				ans += num-N;
				N=num;
			}
		}

	}

	static void init() throws IOException {
		 N = Long.parseLong(br.readLine());
		ans=0;
	}
}

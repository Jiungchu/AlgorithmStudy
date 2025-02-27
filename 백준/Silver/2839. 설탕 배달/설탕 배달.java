
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int[] counts;
	static int N;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		solution();
		System.out.println(counts[N]);
	}

	static void solution() {
		counts = new int[N+1];
		Arrays.fill(counts, -1); // 1로 배열 초기화
		// 초기값 할당
		counts[3] = 1;
		if(N>=5) counts[5] = 1;
		for(int i=6;i<=N;i++) {
			int num1 = Integer.MAX_VALUE;
			int num2 = Integer.MAX_VALUE;
			// 3킬로그램 봉지를 넣을 수 있는 경우
			if(counts[i-3]!=-1) num1 = counts[i-3]+1;
			if(counts[i-5]!=-1) num2 = counts[i-5]+1;
			if(num1 != Integer.MAX_VALUE || num2 != Integer.MAX_VALUE)
				counts[i] = Math.min(num1, num2);
		}
	}

}

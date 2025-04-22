
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static int[][] comb = new int[53][53]; // 0~52까지
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solution());
	}
	
	static int solution() {
		if(N<4) return 0;
		int min = N<42?1:13-(52-N); // 가장 적게 포함할 수 있는 포카드의 수
		int max = N/4; // 가장 많이 포함할 수 있는 포카드의 수
		long[] cases = new long[max+1];
		
		for(int i=max;i>=min;i--) {
			long fourCards = comb[13][i]%10007; // 포카드 종류 중 i개를 뽑는 경우의 수
			long others = comb[52-i*4][ N-i*4]%10007; // 나머지 카드를 뽑는 경우의 수
			cases[i] = fourCards*others;
			// 중복되는 경우의 수 빼주기
			for(int j=max;j>i;j--) {
				cases[i] -= (cases[j]*comb[j][i])%10007-10007;
			}
			cases[i] = (cases[i])%10007;
		}
		
		int sum = 0;
		for(int i=min; i<=max; i++) {
			sum += cases[i];
		}
		sum %= 10007;
		return sum;
	}
	
	static long getPer(int a, int b) {
		long num = 1;
		for(int i=a; i>b; i--) {
			num *= i;
		}
		return num;
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		
		// 조합 초기화
	    for (int i = 0; i <= 52; i++) {
	        comb[i][0] = comb[i][i] = 1;
	        for (int j = 1; j < i; j++) {
	            comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]);
	            if (comb[i][j] >= 10007) comb[i][j] -= 10007; // 모듈러 나눔으로 최댓값 제한
	        }
	    }
	}

}


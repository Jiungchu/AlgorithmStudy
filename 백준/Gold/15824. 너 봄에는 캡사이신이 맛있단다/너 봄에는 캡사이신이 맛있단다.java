
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, arr[];
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		long ans = 0;
		for(int i=0;i<N;i++) {
			ans += getRemainder(i, arr[i])-getRemainder(N-i-1, arr[i])+1000000007;
			ans %= 1000000007;
		}
		System.out.println(ans);
	}
	
	// 2^exp x num 을 1000000007로 나눈 나머지
	static long getRemainder(int exp, int num) {
	    long rem = 1;
	    long base = 2;

	    while(exp > 0) {
	        if (exp % 2 == 1) {  // exp가 홀수일 때 rem을 갱신
	            rem = (rem * base) % 1000000007;
	        }

	        // exp를 2로 나누기 전에 base 제곱
	        base = (base * base) % 1000000007;

	        // exp 반으로 나누기
	        exp /= 2;
	    }

	    // 모두 고르지 않는 경우의 수는 제외
	    return ((rem - 1) * num) % 1000000007;
	}

	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
	}

}


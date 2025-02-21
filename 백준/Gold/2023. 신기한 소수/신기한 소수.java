
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static List<Integer> primes = new ArrayList<>();
	static int[] odds = {1,3,5,7,9};
	static boolean[] primesBool;
	static int N, size;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		size = (int)Math.pow(10,(N+1)/2);
		primesBool = new boolean[size+1];
		findPrime();
		// 가장 첫 번째 숫자도 소수여야 하므로 2,3,5,7로 시작
		for(int i : new int[] {2,3,5,7}) {
			solution(1,i);
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solution(int count, int num) {
		// 소수일 경우에만 재귀호출되므로, N개 모두가 소수인 경우 ans++
		if(count==N) {
			sb.append(num).append("\n");
			return;
		}
		// 짝수를 더하면 소수가 될 수 없으므로 홀수만 더해보기
		for(int odd:odds) {
			if(isPrime(num*10+odd)) solution(count+1, num*10+odd);
		}
	}
	
	// size보다 작은 소수는 리스트에 포함됨
	// 그보다 큰 수는 현재 구한 소수의 배수라면 false를 return
	static boolean isPrime(int num) {
		for(int p : primes) {
			if(num<size) {
				if(num==p) return true;
			} else if(num%p==0) return false; 
		}
		if(num<size) return false;
		else return true;
	}
	
	// sqrt(10^n)까지 소수 구하기
	static void findPrime() {
		Arrays.fill(primesBool, true);
		primesBool[1] = false;
		for(int i=2;i<size;i++) {
			if(primesBool[i]) {
				primes.add(i);
				int mul = 2;
				while(i*mul<size) {
					primesBool[i*mul]=false;
					mul +=1;
				}
			}
		}
	}
}

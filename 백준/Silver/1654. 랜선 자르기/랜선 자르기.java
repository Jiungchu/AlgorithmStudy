import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int K, N, maxLen;
	static long max;
	static int[] lines;
	
	public static void main(String[] args) throws IOException{
		init();
		solution(1, maxLen);
		System.out.println(max);
	}
	
	// 이진탐색으로 해결
	static void solution(long left, long right) {
		if(left>right) return;
		
		long mid = (left+right)/2;
		// 각 랜선을 현재 길이로 나눈 몫이 한 랜선에서 나올 수 있는 개수
		int count = 0;
		for(int i=0;i<K;i++) {
			count += lines[i]/mid;
		}
//		System.out.println(left+" "+right+" "+mid+" "+count);
		if(count<N) solution(left, mid-1);
		else solution(mid+1,right);
		
		if(count>=N) max = Math.max(max, mid);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lines = new int[K];
		max=0;
		maxLen=0;
		for(int i=0;i<K;i++) {
			lines[i] = Integer.parseInt(br.readLine());
			maxLen = Math.max(maxLen, lines[i]);
		}
	}

}

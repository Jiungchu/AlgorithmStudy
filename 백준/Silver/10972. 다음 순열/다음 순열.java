
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[] numbers;
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		
		if(!np()) sb.append(-1);
		else {
			for(int i=0;i<N;i++) {
				sb.append(numbers[i]).append(" ");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	static boolean np() {
		// 꼭대기 찾기
		int i=N-1;
		while(i>0 && numbers[i-1]>=numbers[i]) i--;
		
		if(i==0) return false;
		
		// i-1보다 큰 j 찾기
		int j = N-1;
		while(numbers[i-1]>=numbers[j]) j--;
		swap(i-1,j);
		
//		i-1 뒤로 오름차순 정렬
		int k=N-1;
		while(i<k) swap(i++, k--);
		
		return true;
	}
	
	static void swap(int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}

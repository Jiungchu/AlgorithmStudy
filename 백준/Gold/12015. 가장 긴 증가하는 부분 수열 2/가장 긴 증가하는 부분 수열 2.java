
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, items[], lis[];
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		int lastIndex = 0;
		lis[0] = items[0];
		for(int i=1;i<N;i++) {
			int num = items[i];
			// 마지막 원소보다 크면 뒤에 붙이기
			if(lis[lastIndex] < num ) {
				lis[++lastIndex] = num;
			} else {
				int idx = binarySearch(num, lastIndex);
				// 중복값은 굳이 고려할 필요 없음. idx는 num보다 큰 바로 다음 위치
				if(idx != 0 && lis[idx-1]==num) continue;
				lis[idx] = num;
			}
		}
		System.out.println(lastIndex+1);
	}
	
	// num를 삽입할 위치를 찾는 탐색
	static int binarySearch(int num, int lastIndex) {
		int left = 0;
		int right = lastIndex;
		
		while(left<right) {
			int mid = (left+right)/2;
			if(lis[mid] <= num) left = mid+1;
			else right = mid;
		}
		return left;
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		items = new int[N];
		lis = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
	}

}


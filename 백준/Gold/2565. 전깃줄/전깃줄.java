import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main{

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, lines[][], lis[][];
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(sb);
	}
	
	static void solution() {
		
		int lastIndex = 0;
		lis[0] = lines[0];
		for(int i=1;i<N;i++) {
			int[] line = lines[i];
			// 마지막 원소보다 크면 뒤에 붙이기
			if(lis[lastIndex][1] < line[1] ) {
				lis[++lastIndex] = line;
			} else {
				int idx = binarySearch(line[1], lastIndex);
				lis[idx] = line;
			}
		}

		sb.append(N-(lastIndex+1));
	}
	
	// num를 삽입할 위치를 찾는 탐색
	static int binarySearch(int num, int lastIndex) {
		int left = 0;
		int right = lastIndex;
		
		while(left<right) {
			int mid = (left+right)/2;
			if(lis[mid][1] <= num) left = mid+1;
			else right = mid;
		}
		return left;
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		lines = new int[N][2];
		lis = new int[N][2];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1]  = Integer.parseInt(st.nextToken());
		}
		// 왼쪽 전봇대 기준으로 정렬
		Arrays.sort(lines, (a,b)->a[0]-b[0]);
	}

}

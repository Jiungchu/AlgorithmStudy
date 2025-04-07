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
		// lis상 앞에 위치한 원소를 저장
		int[] prev = new int[N];
		Arrays.fill(prev, -1);
		// 특정 값을 가지는 원소가 lines에서 어떤 인덱스에 있는지 저장
		int[] indexArr = new int[500001];
		
		int lastIndex = 0;
		lis[0] = lines[0];
		for(int i=1;i<N;i++) {
			int[] line = lines[i];
//			System.out.println(line[0]+" "+line[1]);
			// 마지막 원소보다 크면 뒤에 붙이기
			if(lis[lastIndex][1] < line[1] ) {
				prev[i] = indexArr[lis[lastIndex][1]];
				lis[++lastIndex] = line;
			} else {
				int idx = binarySearch(line[1], lastIndex);
				if(idx != 0) prev[i] = indexArr[lis[idx-1][1]];
				lis[idx] = line;
			}
//			System.out.println("### lis ###");
//			System.out.println(i+" "+prev[i]);
//			for(int j=0;j<lastIndex+1;j++) {
//				System.out.print(lis[j][1]+" ");
//			}
//			System.out.println("\n### end ### ");
			indexArr[line[1]] = i;
		}
		
		List<Integer> list = new ArrayList<>();
		int last = indexArr[lis[lastIndex][1]];
		for(int i=N-1;i>=0;i--) {
//			System.out.println(i+" "+last+" "+lines[i][0]);
			if(i>last) {
				list.add(lines[i][0]);
			}
			else last = prev[last];
		}
		sb.append(list.size()).append("\n");
		for(int i=list.size()-1;i>=0;i--) {
			sb.append(list.get(i)).append("\n");
		}
//		System.out.println(Arrays.toString(prev));
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

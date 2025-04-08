
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, items[], lis[];
	static Map<Integer, Integer> arrIndex;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(sb);
	}
	
	static void solution() {
		// lis상 앞에 위치한 원소를 저장
		int[] prev = new int[N];
		Arrays.fill(prev, -1);
		
		int lastIndex = 0;
		lis[0] = items[0];
		arrIndex.put(lis[0], 0);
		for(int i=1;i<N;i++) {
			int num = items[i];
			// 마지막 원소보다 크면 뒤에 붙이기
			if(lis[lastIndex] < num ) {
				prev[i] = arrIndex.get(lis[lastIndex]);
				lis[++lastIndex] = num;
			} else {
				int idx = binarySearch(num, lastIndex);
				// 중복값은 굳이 고려할 필요 없음. idx는 num보다 큰 바로 다음 위치
				if(idx != 0 && lis[idx-1]==num) continue;
				if(idx != 0 ) prev[i] = arrIndex.get(lis[idx-1]);
				lis[idx] = num;
			}
			arrIndex.put(num, i);
//			System.out.println(Arrays.toString(lis));
		}
		
		List<Integer> list = new ArrayList<>();
		int last = arrIndex.get(lis[lastIndex]);
		while(last>-1) {
			list.add(items[last]);
			last = prev[last];
		}
		sb.append(list.size()).append("\n");
		for(int i=list.size()-1;i>=0;i--) {
			sb.append(list.get(i)).append(" ");
		}
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
		
		arrIndex = new HashMap<>();
	}

}

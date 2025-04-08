
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int t, N, arr[], lis[];
	
	public static void main(String[] args) throws IOException {
		int T= Integer.parseInt(br.readLine());
		for(t=1; t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}
	
	static void solution() {
		int lastIndex = 0;
		lis[0] = arr[0];
		for(int i=1;i<N;i++) {
			int num = arr[i];
			if(num>lis[lastIndex]) {
				lis[++lastIndex] = num;
			}
			else {
				int idx = binarySearch(lastIndex, num);
				lis[idx] = num;
			}
		}
		
		sb.append("#").append(t).append(" ").append(lastIndex+1).append("\n");
	}
	
	static int binarySearch(int lastIndex, int num) {
		int left = 0, right = lastIndex;
		while(left<right) {
			int mid = (left+right)/2;
			if(lis[mid]<=num) left = mid+1;
			else right = mid;
		}
		return left;
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		lis = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

}

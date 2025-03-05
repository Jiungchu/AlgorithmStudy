
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, maxHeight;
	static int[] trees;
	
	public static void main(String[] args) throws IOException{
		init();
		int ans = solution();
		System.out.println(ans);
		br.close();
	}
	
	static int solution() {
		int start = 0, end = maxHeight;
		while(start<=end) {
			int mid = (start+end)/2;
			long length = getTree(mid);
			if(length==M) return mid;
			else if(length<M) end = mid-1;
			else start = mid+1;
		}
		return (start+end)/2;
	}
	
	static long getTree(int height) {
		long length = 0;
		for(int i=0;i<N;i++) {
			if(trees[i]>height) length += trees[i]-height;
		}
		return length;
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maxHeight = 0;
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int num=Integer.parseInt(st.nextToken());
			trees[i] = num;
			maxHeight = Math.max(maxHeight, num);
		}
		
	}

}

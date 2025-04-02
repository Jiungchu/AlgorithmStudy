import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	static int N, D, K, C, t, maxCount;
	static int[] items;
	
    public static void main(String[] args) throws IOException{
		init();
		solution();
    }
    
    static void solution() {
    	// 초기화, 시작점부터 k개를 연속으로 먹었을 때 종류
    	int start = 1, count=0;
    	int cur[] = new int[D+1];
    	for(int i=0;i<K;i++) {
    		int num = items[i];
    		if(cur[num]==0) count++;
    		cur[num]++;
    	}
    	if(cur[C]==0) count++;
    	maxCount = count;
    	while(start<N) {
    		// 이전 시작점 삭제
    		int prevNum = items[start-1];
    		cur[prevNum]--;
    		if(cur[prevNum]==0 && prevNum!=C) count--;
    		int num = items[(start+K-1)%N];
    		if(cur[num]==0 && num!=C) count++;
    		cur[num]++;
    		
    		maxCount = Math.max(maxCount, count);
    		start++;
//    		System.out.println(Arrays.toString(cur)+" "+count);
    	}
    	System.out.println(maxCount);
    }
    
    static void init() throws IOException {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	D = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	
    	items = new int[N];
    	for(int i=0;i<N;i++) {
    		items[i] = Integer.parseInt(br.readLine());
    	}
    }
}

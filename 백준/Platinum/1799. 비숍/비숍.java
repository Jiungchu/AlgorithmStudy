import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, maxCount;
	static boolean[] diag;
	static List<List<int[]>> diagPoints;
	
	public static void main(String[] args) throws IOException {
		init();
		solution(0, 0);
		System.out.println(maxCount);
	}
	
	static void solution(int index, int count) {
		if(count>maxCount) maxCount = count;
		
		if(index>=2*N-1 || count+2*N-1-index<=maxCount) return;
		
		for(int[] next : diagPoints.get(index)) {
			int r = next[0], c = next[1];
			if(!diag[r-c+N-1]) {
				diag[r-c+N-1] = true;
				solution(index+1, count+1);
				diag[r-c+N-1] = false;
			}
		}
		solution(index+1, count);
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		diag = new boolean[2*N-1];
		diagPoints = new ArrayList<>();
		for(int i=0;i<2*N-1;i++) {
			diagPoints.add(new ArrayList<>());
		}
		maxCount = 0;
		
		for(int i=0;i<N;i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num==1) {
					// 오른쪽 상단으로 향하는 대각선 번호에 가능한 점을 mapping
					diagPoints.get(i+j).add(new int[] {i,j}); 
				}
			}
		}
	
	}

}

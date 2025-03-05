import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[][] blockIndex = {{0,0},{0,1},{1,0},{1,1}};

	static int N, R, C, ans;
	
	public static void main(String[] args) throws IOException {

		init();
		System.out.println(solution());
		br.close();
	}

	static int solution() {
		int ans=0;
		while(N>0) {
			int size = (int) Math.pow(2, N);
			if(R==0 && C==0) {
				return ans;
			}
			// 블록 번호 구하기
			int currBlock = 0;
			currBlock += C>=size/2?1:0; 
			currBlock += R>=size/2?2:0; 
			ans += (size/2)*(size/2)*currBlock;
			// 사각형 크기 조절, 인덱스 조절
			N-=1; 
			R-=size/2*(blockIndex[currBlock][0]);
			C-=size/2*(blockIndex[currBlock][1]);
		}
		return ans;
	}


	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	}
}

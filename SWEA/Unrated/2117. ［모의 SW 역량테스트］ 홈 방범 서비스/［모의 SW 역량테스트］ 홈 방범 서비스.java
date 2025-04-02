
import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, t;
	static List<int[]> homes;
	
	public static void main(String[] args) throws Exception{
		int T=Integer.parseInt(br.readLine());
		for(t=1;t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb);
		br.close();
		
	}
	
	static void solution() {
		int[] counts = new int[2*N];
		int maxCount = 0;
		// 초기화. counts[i]는 k=i의 경계에 포함된 집의 수
		for(int[] home : homes) {
			counts[calculate(0,0,home[0],home[1])+1]++;
		}
		
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				Arrays.fill(counts, 0);
				// 모든 점과 각 집과의 거리 탐색
				for(int[] home : homes) {
					counts[calculate(r,c,home[0],home[1])+1]++;
				}
				for(int k=1;k<2*N;k++) {
					// k-> 범위 k에서 수용할 수 있는 집의 수로 설정
					counts[k] += counts[k-1];
					if(counts[k]*M >= k*k+(k-1)*(k-1)) {
						if(counts[k]>maxCount) maxCount = counts[k];
					}
				}
			}
		}
		sb.append("#").append(t).append(" ").append(maxCount).append("\n");
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		homes = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				String num = st.nextToken();
				if(num.equals("1")) homes.add(new int[] {i,j});
			}
		}
	}
	
	static int calculate(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2)+Math.abs(c1-c2);
	}

}


import java.util.*;
import java.awt.geom.Area;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			
			List<int[]> homes = new ArrayList<>();
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					String num = st.nextToken();
					if(num.equals("1")) homes.add(new int[] {i,j});
				}
			}
			
			int[] counts = new int[2*n];
			int maxCount = 0;
			for(int r=0;r<n;r++) {
				for(int c=0;c<n;c++) {
					Arrays.fill(counts, 0);
					// 모든 점과 각 집과의 거리 탐색
					for(int[] home : homes) {
						counts[calculate(r,c,home[0],home[1])+1]++;
					}
					for(int k=1;k<2*n;k++) {
						// k-> 범위 k에서 수용할 수 있는 집의 수로 설정
						counts[k] += counts[k-1];
						if(counts[k]*m >= k*k+(k-1)*(k-1)) {
							if(counts[k]>maxCount) maxCount = counts[k];
						}
					}
				}
			}
			System.out.println("#"+(t+1)+" "+maxCount);
		}
		br.close();
		
	}
	
	static int calculate(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2)+Math.abs(c1-c2);
	}

}

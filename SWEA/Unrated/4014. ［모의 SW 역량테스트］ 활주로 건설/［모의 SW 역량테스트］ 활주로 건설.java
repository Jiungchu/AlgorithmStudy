
import java.util.*;
import java.io.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, X, maps[][][], count, t;
	
	public static void main(String[] args) throws Exception{
		int T =Integer.parseInt(br.readLine());
		for(t=1;t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb);
		br.close();
	}
	
	static void solution() {
		int ans=0;
		for(int i=0;i<N;i++) {
			if(check(i,0)) ans++;
			if(check(i,1)) ans++;
		}
		sb.append("#").append(t).append(" ").append(ans).append("\n");
	}
	
	static boolean check(int rowNum, int mapNum) {
		int[] road = maps[mapNum][rowNum];
		
		int flat = 1; // 연속된 평지의 길이
		boolean isDownhill = false; // 현재 내리막 경사로인지
		
		for(int i=0;i<N-1;i++) {
			int cur = road[i], next=road[i+1];
			if(Math.abs(cur-next)>1) return false; // 2 이상 차이가 나는 블록
			
			if(cur==next) {
				flat++;
				if(isDownhill && flat==X) { // 내리막 종료
					flat = 0; isDownhill = false;
				} 
			} else {
				if(isDownhill && flat!=0) return false; // 경사로가 끝나지 않았는데, 다시 경사로가 나온 경우
				if(cur>next) { // 내리막
					isDownhill = true;
					flat = 1;
				} else { // 오르막, 지금까지 평지가 X 이상이면 바로 설치, 평지가 X보다 짧으 설치 불가능
					if(flat<X) return false; 
					flat = 1;
				}
			}
			
		}
		if(isDownhill) return false; // 내리막이 끝나지 않은 경우
		return true;
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		count=0;
		
		// 행, 열을 각가 보기 위해 map 2개 사용
		maps = new int[2][N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				maps[0][i][j] = num;
				maps[1][j][i] = num;
				
			}
		}
	}

}



import java.util.*;
import java.io.*;

public class Solution {
	static int[][] grid;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T =Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			count=0;
			grid = new int[n][n];
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				boolean possible = true;
				boolean down = false;
				int continuous=0;
				for(int j=0;j<n;j++) {
					int num = Integer.parseInt(st.nextToken());
					grid[i][j] = num;
					if(!possible) continue;
					if(j==0) {
						continuous = 1; continue;
					} else if(!down){ // 내려가는 길이 아니면
						if(grid[i][j-1]==num) { // 높이가 같은 경우
							continuous++;
						} else if(grid[i][j-1]<num) { // 올라가는 길
							if(continuous<x || num-grid[i][j-1]>1) { // 평지가 x만큼 연속되지 않았거나 1 이상 커지면
								possible = false;
								continue; 
							}
							continuous = 1; // 충분하다면 다시 그대로 진행
						}
						else { // 내려가는 길
							if(grid[i][j-1]-num>1) {
								possible=false; continue;
							}
							down = true;
							continuous = 1;
						}
					} else {
						if(continuous<x && grid[i][j-1]!=num) {
							// 아직 x만큼 연속되지 않았는데 지형이 변하면
							possible = false; continue;
						} else if(grid[i][j-1]==num) {
							continuous++;
							if(continuous==x) {
								down=false; // 경사로 설치 완료
								continuous = 0; // 겹쳐서 설치할 수 없으므로 연속된 칸은 0
							}
						}
					}
				}
				if(possible && !down) count++; // 내리막 경사로 설치가 마무리되지 않으면 설치가 불가능하므로
			}
			for(int j=0;j<n;j++) {
				boolean possible = true;
				boolean down = false;
				int continuous=0;
				for(int i=0;i<n;i++) {
					int num = grid[i][j];
					if(!possible) break;
					if(i==0) {
						continuous = 1; continue;
					} else if(!down){ // 내려가는 길이 아니면
						if(grid[i-1][j]==num) { // 높이가 같은 경우
							continuous++;
						} else if(grid[i-1][j]<num) { // 올라가는 길
							if(continuous<x || num-grid[i-1][j]>1) { // 평지가 x만큼 연속되지 않았다면 
								possible = false;
								continue; 
							}
							continuous = 1; // 충분하다면 다시 그대로 진행
						} else { // 내려가는 길
							if(grid[i-1][j]-num>1) {
								possible=false; continue;
							}
							down = true;
							continuous = 1;
						}
					} else {
						if(continuous<x && grid[i-1][j]!=num) {
							// 아직 x만큼 연속되지 않았는데 지형이 변하면
							possible = false; continue;
						} else if(grid[i-1][j]==num) {
							continuous++;
							if(continuous==x) {
								down=false; // 경사로 설치 완료
								continuous = 0; // 겹쳐서 설치할 수 없으므로 연속된 칸은 0
							}
						}
					}
				}
				if(possible && !down) count++;
			}
			System.out.println("#"+(t+1)+" "+count);
		}
		
		br.close();
	}

}


import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int r1 = Integer.parseInt(input[0]);
		int c1 = Integer.parseInt(input[1]);
		int r2 = Integer.parseInt(input[2]);
		int c2 = Integer.parseInt(input[3]);
		int max = Math.max(Math.abs(r1), Math.max(Math.abs(c1), Math.max(Math.abs(r2), Math.abs(c2))));
		
 		int[][] grid = new int[r2-r1+1][c2-c1+1];
		int[] dx = {0,-1,0,1};
		int[] dy = {1,0,-1,0};
		int direction=0; int flag1=0; int flag2 = 0; int flag3 = 1;
		
		int count = 0;
		int num=1; int x=max; int y=max;
		while(count<(r2-r1+1)*(c2-c1+1)) {
			if(x>=max+r1&&x<=max+r2 && y>=max+c1&&y<=max+c2) {
				grid[x-(max+r1)][y-(max+c1)] = num; count++;
				
			} // 상대위치, 절대위치 설정해야 함
			if(flag1==flag3) {
				flag1=0;
				direction= (direction+1)%4;
				flag2++; // 방향을 몇 번 바꿨는지 저장
			}
			if(flag2==2) { // 방향을 2번 바꾸면 직진거리 증가
				flag2=0;
				flag3++; 
			}
			x = x+dx[direction]; y=y+dy[direction];
			num++;
			flag1++; // 앞으로 몇 번 갔는지 저장
		}
		int maxLength = (Math.max(Math.abs(grid[0][0]),Math.max(Math.abs(grid[0][c2-c1]), Math.max(Math.abs(grid[r2-r1][0]), Math.abs(grid[r2-r1][c2-c1]))))+"").length();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<r2-r1+1;i++) {
			for(int j=0;j<c2-c1+1;j++) {
				int dif = maxLength-(grid[i][j]+"").length();
				if(j!=0) {
					for(int k=0;k<dif+1;k++) {
						sb.append(" ");
					}
				} else {
					for(int k=0;k<dif;k++) {
						sb.append(" ");
					}
				}
				sb.append(grid[i][j]);
				

			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}

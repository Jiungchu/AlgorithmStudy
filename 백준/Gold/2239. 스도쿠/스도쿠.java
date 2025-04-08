
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int map[][];
	static boolean end;
	static boolean[][] rused, cused, bused;
	
	public static void main(String[] args) throws IOException {
		init();
		solution(0);
	}
	
	static void solution(int index) {
		if(index==81) {
			show();
			end=true;
			return;
		}
		if(end) return;
		
		int r = index/9, c=index%9;
		if(map[r][c]!=0) { // 값이 이미 있을 경우
			solution(index+1);
		} else { // 0인 경우 값을 채워서 넘겨야 함
			for(int num=1;num<=9;num++) {
				if(!rused[r][num] && !cused[c][num] && !bused[r/3*3 + c/3][num]) {
					rused[r][num] = true;
					cused[c][num] = true;
					bused[r/3*3 + c/3][num] = true;
					map[r][c] = num;
					solution(index+1);
					// 백트래킹
					rused[r][num] = false;
					cused[c][num] = false;
					bused[r/3*3 + c/3][num] = false;
					map[r][c] = 0;
				}
			}
		}
	}
	
	static void show() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void init() throws IOException{
		map = new int[9][9];
		// 각각 행, 열, 블록 기준으로 사용한 수들을 저장
		rused = new boolean[9][10];
		cused = new boolean[9][10];
		bused = new boolean[9][10];
		end=false;
		
		for(int i=0;i<9;i++) {
			String str = br.readLine();
			for(int j=0;j<9;j++) {
				int num = str.charAt(j)-'0';
				if(num != 0) {
					rused[i][num] = true;
					cused[j][num] = true;
					bused[i/3*3 + j/3][num] = true;
				}
				map[i][j]=num;
			}
		}
	}

}


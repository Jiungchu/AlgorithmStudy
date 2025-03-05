
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static String direction;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution(direction);
			sb.append("#").append(t).append("\n");
			arrayShow();
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution(String direction) {
		int start, d;
		boolean isRow;
		if (direction.equals("left")) {
			start = 0;
			d = 1;
			isRow = true;
		} else if (direction.equals("right")){
			start = N-1;
			d = -1;
			isRow = true;
		} else if (direction.equals("up")){
			start = 0;
			d = 1;
			isRow = false;
		} else {
			start = N-1;
			d = -1;
			isRow = false;
		}
		if (isRow) {
			int row = start, col = start;
			while (row >= 0 && row < N) {
				int length = start;
				boolean possible = false; // 마지막 원소가 합쳐질 수 있는 원소인지
				while (col >= 0 && col < N ) {
					if (arr[row][col] != 0) {
						// 현재 길이 마지막으로 원소 이동
						arr[row][length] = arr[row][col];
						if(col!=length) arr[row][col]=0;
						length += d;
						// 합칠 수 있다면 합치기
						if (((start==0 && length > 1)||(start != 0 && length<start-1)) && possible && arr[row][length - 2*d] == arr[row][length - d]) {
							arr[row][length - 2*d] = arr[row][length - d] * 2;
							arr[row][length - d] = 0;
							length -= d;
							possible = false; // 이미 합쳐진 원소이므로 다시 합치는 건 불가능
						} else
							possible = true;
					}
					col += d;
				}
				col = start;
				row += d;
			}
		} else {
			int row = start, col = start;
			while (col >= 0 && col < N) {
				int length = start;
				boolean possible = false; // 마지막 원소가 합쳐질 수 있는 원소인지
				while (row >= 0 && row < N ) {
					if (arr[row][col] != 0) {
						// 현재 길이 마지막으로 원소 이동
						arr[length][col] = arr[row][col];
						if(length!=row) arr[row][col] = 0;
						length += d;
						// 합칠 수 있다면 합치기
						if (((start==0 && length > 1)||(start != 0 && length<start-1))&& possible && arr[length - 2*d][col] == arr[length - d][col]) {
							arr[length- 2*d][col] = arr[length - d][col] * 2;
							arr[length-d][col] = 0; 
							length -= d;
							possible = false; // 이미 합쳐진 원소이므로 다시 합치는 건 불가능
						} else
							possible = true;
					}
					row += d;
				}
				row = start;
				col += d;
			}
		}

	}
	
	static void arrayShow() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		direction = st.nextToken();
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

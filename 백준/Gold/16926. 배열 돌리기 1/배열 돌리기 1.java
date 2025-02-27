
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 회의가 끝나는 시간을 key로 하고, 회의 시작 시간이 담긴 우선순위 큐를 사용
	static int N, M, R, num;
	static int[][] arr;
	static int[] dr = {0,1,0,-1}, dc = {1,0,-1,0}; 
	static List<List<int[]>> layers; // 레이어의 좌표를 저장하는 리스트
	static List<List<Integer>> values; // 레이어의 값을 저장하는 리스트

	public static void main(String[] args) throws IOException {
		init();
		solution();
		br.close();
	}

	static void solution() {
		getLayer();
//		System.out.println(layers);
//		System.out.println(values);
		// 레이어별로 회전
		for(int l=0;l<num;l++) {
			List<int[]> currLayer = layers.get(l);
			List<Integer> currVal = values.get(l);
			int size = currLayer.size(); // 레이어에 포함된 좌표의 수
			int start = R%size; // size만큼 돌면 제자리
			
			for(int i=0;i<size;i++) {
				int index = (start+i)%size; // 시작 위치에 따라 조정
				// 좌표는 그대로 두고 숫자만 변경
				int r = currLayer.get(i)[0];
				int c = currLayer.get(i)[1];
				int value = currVal.get(index);
				arr[r][c] = value; 
			}
		}
		
		// 출력
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void getLayer() {
		num = 0 ; // 레이어의 수
		
		while((N-num*2)>0 && (M-num*2)>0) {
			// 레이어 추가
			layers.add(new ArrayList<>());
			values.add(new ArrayList<>());
			dfs(num,num,num,0); // i번쨰 레이어의 시작 지점은 (i,i)
			num++;
		}
		
	}
	
	// 레이어 찾을 때 사용
	static void dfs(int r, int c, int num, int d) {
		int nr = r+dr[d];
		int nc = c+dc[d];
		
		// num으로 레이어 사이즈 관리
		if(nr<num||nr>=N-num || nc<num||nc>=M-num) {
			if(++d==4) return; // 시작 지점으로 돌아온 경우
			nr = r+dr[d];
			nc = c+dc[d];
		}
		// 리스트에 레이어 정보 전달하고 재귀호출
		layers.get(num).add(new int[] {r,c});
		values.get(num).add(arr[r][c]);
		dfs(nr,nc,num,d);
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		layers = new ArrayList<>();
		values = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}

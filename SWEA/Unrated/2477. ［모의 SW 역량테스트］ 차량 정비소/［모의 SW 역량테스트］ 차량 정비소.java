
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int t, N, M, K, A, B;
	static int[] ai, bj;
	static Queue<int[]> customers;
	
	public static void main(String[] args) throws IOException {
		int T= Integer.parseInt(br.readLine());
		for(t=1; t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}
	
	static void solution() {
		// 상태를 배열로 저장. 점유하지 않으면 0, 점유하고 있으면 고객 번호와 점유가 끝나는 시간을 저장
		int[][] receptionState = new int[N][];
		int[][] repairState = new int[M][];
		// 대기하는 고객 번호를 저장할 큐
		Queue<Integer> q1 = new ArrayDeque<>();
		Queue<int[]> q2 = new ArrayDeque<>(); // 고객 번호와 사용한 접수 창구의 번호도 저장
		
		int ans = 0;
		// 시뮬레이션 돌리기
		for(int t=0;t<=5000;t++) {
			// 도착한 고객을 접수 큐에 넣기
			while(!customers.isEmpty() && customers.peek()[1]<=t) {
				q1.offer(customers.poll()[0]);
			}
			
			// repair desk 먼저 처리
			for(int i=0;i<M;i++) {
				// 처리 완료된 경우 null로 설정
				if(repairState[i] != null && repairState[i][1]==t) repairState[i]=null;
				// 대기 고객이 있고 자리가 비었을 경우
				if(!q2.isEmpty() && repairState[i]==null) {
					int[] c = q2.poll();
					int num = c[0], deskNum = c[1];
					if(deskNum==A && i+1==B) ans += num;
					repairState[i] = new int[] {num, t+bj[i]};
				}
			}
			
			// 접수 데스크 처리
			for(int i=0;i<N;i++) {
				// 처리가 끝났으면 다음 대기 큐에 넣기
				if(receptionState[i] != null && receptionState[i][1]==t) {
					q2.offer(new int[] {receptionState[i][0], i+1});
					receptionState[i]=null;
				}
				if(!q1.isEmpty() && receptionState[i]==null) {
					receptionState[i] = new int[] {q1.poll(), t+ai[i]};
				}
			}
		}
		ans = ans==0?-1:ans;
		sb.append("#").append(t).append(" ").append(ans).append("\n");
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		ai = new int[N];
		bj = new int[M];
		customers = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			ai[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			bj[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			customers.offer(new int[] {i+1, Integer.parseInt(st.nextToken())});
		}
	}

}

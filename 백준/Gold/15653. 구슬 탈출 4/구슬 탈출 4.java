import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class State {
		int move;
		int rr;
		int rc;
		int br;
		int bc;
		
		public State() {}
		
		public State(int move, int rr, int rc, int br, int bc) {
			this.move = move;
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
		}

		// 빨간 구슬, 파란 구슬의 x, y를 각각 4비트씩으로 표현, 키 하나로 표현하기
		public int getKey() {
			return (rr<<12) + (rc<<8) + (br<<4) + bc;
		}

		@Override
		public String toString() {
			return "State [move=" + move + ", rr=" + rr + ", rc=" + rc + ", br=" + br + ", bc=" + bc + "]";
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, or, oc;
	static char[][] map;
	static boolean[] visited;
	static Queue<State> q;
	
	public static void main(String[] args) throws IOException{
		init();
		System.out.println(solution());
	}
	
	static int[] dr = {1,-1,0,0}, dc = {0,0,1,-1};
	
	static int solution() {
		while(!q.isEmpty()) {
			State curr = q.poll();
//			System.out.println(curr);
			if(curr.rr == or && curr.rc ==oc) {
				return curr.move;
			}
			for(int d=0;d<4;d++) {
				State next = move(curr, d);
				if(next != null && !visited[next.getKey()]) {
					visited[next.getKey()] = true;
					q.offer(next);
				}
			}
		}
		
		return -1;
	}
	
	// 가장자리로 나가는 경우는 없음
	static State move(State prev, int d) {
		// 경로상에서 B를 만나면 B가 더 앞쪽에 있는 것. 둘 다 동시에 켜질 수는 없음
		boolean redIsForward = false;
		boolean blueIsForward = false;
		
		int prr = prev.rr, prc = prev.rc;
		int pbr = prev.br, pbc = prev.bc;
		
		int nrr = prr, nrc = prc;
		while(map[nrr+dr[d]][nrc+dc[d]] != '#') {
			nrr += dr[d]; nrc += dc[d];
			// 경로상에서 B를 만나면
			if(nrr == pbr && nrc == pbc) blueIsForward = true; 
//			System.out.println(or+" "+oc+" "+nrr+" "+nrc);
			if(!blueIsForward && nrr==or && nrc==oc) break; // 구멍을 만나면 break 처리
		}
		
		int nbr = pbr, nbc = pbc;
		while(map[nbr+dr[d]][nbc+dc[d]] != '#') {
			nbr += dr[d]; nbc += dc[d];
			if(map[nbr][nbc] == 'O') return null; // 파란 공은 들어가면 안됨
			if(nbr == prr && nbc == prc) redIsForward = true;
		}
//		System.out.println(blueIsForward);
		// 한 칸씩 뒤로 움직이기
		if(redIsForward) {
			nbr -= dr[d]; nbc -= dc[d];
		} else if (blueIsForward) {
			nrr -= dr[d]; nrc -= dc[d];
		}
		
		return new State(prev.move+1, nrr, nrc, nbr, nbc);
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[1<<17-1];
		q  = new ArrayDeque<>();
		
		// 초기 상태를 저장
		State init = new State();
		init.move = 0;
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				char c = s.charAt(j);
				map[i][j] = c;
				if(c == 'R') {
					init.rr = i; 
					init.rc = j; 
				} else if(c == 'B') {
					init.br = i;
					init.bc = j;
				} else if(c == 'O') {
					or = i; oc= j;
				}
			}
		}
		q.offer(init);
		visited[init.getKey()]=true;
	}

}

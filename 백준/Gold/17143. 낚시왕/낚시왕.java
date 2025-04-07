
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Shark {
		int r;
		int c;
		int s;
		int d;
		int z;
		int t; // 시점 정보
		boolean isDead;
		
		public Shark() {}
		
		public Shark(int r, int c, int s, int d, int z, int t) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.t = t;
			this.isDead = false;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + ", t=" + t + ", isDead="
					+ isDead + "]";
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int R, C, M;
	static Shark[][] map;
	static Queue<Shark> q;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	// 위, 아래, 오른쪽, 왼쪽 순서. 1번부터 시작하므로 크기는 5로 설정
	static int[] dr= {0,-1,1,0,0}, dc= {0, 0,0,1,-1};
	
	static void solution() {
		int ans=0;
		// 총 r번 움직이게 됨
		// 1. 오른쪽으로 한 칸 이동
		for(int c=0;c<C;c++) {
			if(q.isEmpty()) break; // 상어가 존재하지 않는다면 즉시 종료
			// 2. 해당 열에서 가장 가까운 상어 잡기. 시점은 고려하지 않아도 됨. 죽었는지만 판단
			for(int r=0;r<R;r++) {
				if(map[r][c]!=null && !map[r][c].isDead) {
					Shark s = map[r][c];
					ans += s.z;
					s.isDead = true;
					map[r][c] = null;
					break;
				}
			}
			
			// 3. 상어 이동
			int size = q.size();
			while(size-->0) {
				Shark cur = q.poll();
				if(cur.isDead) continue; // 이미 죽은 상어는 고려하지 않음
				if(cur.equals(map[cur.r][cur.c])) map[cur.r][cur.c]=null; // map에서 제거
				int move = cur.s;
//				System.out.print(cur+" -> ");
				// 좌우로 이동할 때, R-1번 이동하면, 좌표는 R-r로 바뀌고 방향이 반대가 됨
				if(cur.d == 1 || cur.d == 2) {
					int times = cur.s/(R-1);
					move -= times*(R-1);
					// 몫이 짝수이면 제자리, 홀수이면 방향이 반대가 되고 좌표는 R-r-1 혹은 C-c-1로 바뀜
					if(times %2 == 1) {
						cur.d = cur.d%2==0?cur.d-1:cur.d+1;
						cur.r = R-cur.r-1;
					}
				}
				else {
					int times = cur.s/(C-1);
					move -= times*(C-1);
					if(times %2 == 1) {
						cur.d = cur.d%2==0?cur.d-1:cur.d+1;
						cur.c = C-cur.c-1;
					}
				}
				// 나머지는 직접 이동
				int nr = cur.r, nc = cur.c;
				while(move>0) {
					nr = cur.r + dr[cur.d];
					nc = cur.c + dc[cur.d];
					if(( cur.d<=2 && (nr==0 || nr==R-1)) || (cur.d>=3 && ( nc==0 || nc==C-1))) {
						cur.d = cur.d%2==0?cur.d-1:cur.d+1;
					}
					cur.r = nr; cur.c=nc;
					move--;
				}
				cur.t++; // 시점 업데이트 
//				System.out.println(cur);
				Shark prev = map[cur.r][cur.c]; // 현재 상어와 같은 위치에 있는지 확인
				if(prev!=null && prev.t == cur.t) {
//					System.out.println(prev+" , "+cur);
					if(cur.z>prev.z) {
						map[cur.r][cur.c] = cur;
						prev.isDead = true; // 이미 큐에 들어가 있으므로 죽은 것으로 처리
						q.offer(cur);
					} // cur 상어가 작다면 해당 상어는 죽은 것
				} else {
					// null이거나, 같은 시점, 같은 위치에 상어가 없는 경우
					map[cur.r][cur.c] = cur; 
					q.offer(cur);
				}
			}
//			System.out.println(q);
		}
		System.out.println(ans);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R][C];
		q = new ArrayDeque<>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			if((r==0 && d==1) || (c==C-1 && d==3)) d += 1;
			else if ((r==R-1 && d==2) || (c==0 && d==4)) d -= 1; 
			
			Shark shark = new Shark(r,c,s,d,z,0);
			map[r][c] = shark; // r행 c열에는 하나의 shark만 존재하도록 하기
			q.offer(shark);
		}
	}

}

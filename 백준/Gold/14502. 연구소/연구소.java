
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, map[][], min, area, t;
	static List<int[]> virus;
	
    public static void main(String[] args) throws IOException{
    	init();
    	solution(0,0,0);
    	// 오염지역의 수가 최소일 때 
    	System.out.println(area-min-3);
    }
    
    // 3개씩 조합을 뽑아 확인하기
    static void solution(int index, long mask, int count) {
    	if(count==3) {
    		int num = check(mask);
    		if(num!=-1) min = num;
    		return;
    	}
    	if(index>=N*M) return;
    	if (map[index / M][index % M] == 0) {
            solution(index+1, mask | (1L << index), count + 1);
        }
    	solution(index+1, mask, count);
    }
    
    // 오염된 지역의 수를 count
    static int[] dr = {1,-1,0,0}, dc={0,0,1,-1};

    static int check(long mask) {
    	int polluted = 0;
    	// 기본 bfs로 바이러스 위치에서 출발
    	Queue<int[]> q = new ArrayDeque<>();
    	boolean[][] visited = new boolean[N][M];
    	for(int i=0;i<N*M;i++) {
    		if((mask&(1L<<i)) >0) {
    			int r = i/M, c = i%M;
    			visited[r][c]=true;  // mask에 표시된 지역을 벽으로 취급
    		}
    	}
    	for(int[] v : virus) {
    		q.add(new int[] {v[0],v[1]});
    		while(!q.isEmpty()) {
    			int[] curr = q.poll();
    			int r = curr[0], c= curr[1];
    			for(int d=0;d<4;d++) {
    				int nr = r+dr[d], nc=c+dc[d];
    				if(nr>=0&&nr<N && nc>=0&&nc<M && !visited[nr][nc] && map[nr][nc]==0) {
    					visited[nr][nc] = true;
    					polluted++;
    					q.add(new int[] {nr, nc});
    				}
    			}
    			if(polluted>=min) return -1; // 가지치기
    		}
    	}
    	return polluted;
    }
    
    static void init() throws IOException {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	map = new int[N][M];
    	virus = new ArrayList<>();
    	int nonZero = 0;
    	for(int i=0;i<N;i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0;j<M;j++) {
    			int num = Integer.parseInt(st.nextToken());
    			map[i][j] = num;
    			if(num==2) virus.add(new int[] {i,j});
    			if(num != 0) nonZero++;
    		}
    	}
    	// 초기 오염 지역 
    	min = Integer.MAX_VALUE;
    	min = check(0);
    	area = N*M-nonZero;
    }
}

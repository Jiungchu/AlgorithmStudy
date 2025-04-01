
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, map[][], costs[][], t;
	
    public static void main(String[] args) throws IOException{
    	N = Integer.parseInt(br.readLine()); 
    	t=0;
    	while(N!=0) {
    		init();
    		solution();
    		N = Integer.parseInt(br.readLine());
    	}
    	System.out.println(sb.toString());
    }
    
    static int[] dr= {1,-1,0,0}, dc= {0,0,1,-1};
    
    static void solution() {
    	PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(a[2]-b[2]));
    	pq.add(new int[] {0,0,map[0][0]});
    	while(!pq.isEmpty()) {
    		int[] curr = pq.poll();
    		int r = curr[0], c = curr[1], cost = curr[2];
    		if(costs[r][c]<cost) continue;
    		costs[r][c] = cost;
//    		System.out.println(r+" "+c+" "+cost);
    		for(int d=0;d<4;d++) {
    			int nr = r+dr[d]; int nc=c+dc[d];
    			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
    			if(costs[nr][nc]>cost+map[nr][nc]) {
    				costs[nr][nc]=cost+map[nr][nc];
    				pq.add(new int[] {nr,nc,costs[nr][nc]});
    			}
    		}
    	}
    	sb.append("Problem ").append(++t).append(": ").append(costs[N-1][N-1]).append("\n");
    }
    
    static void init() throws IOException {
    	map = new int[N][N];
    	costs = new int[N][N];
    	for(int i=0;i<N;i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for(int j=0;j<N;j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			costs[i][j] = Integer.MAX_VALUE;
    		}
    	}
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

	static class Node implements Comparable<Node>{
		int num;
		double x;
		double y;
		double time;
		
		public Node(int num, double x, double y, double dist) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.time = dist/5;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(time, o.time);
		}
		
	}
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	static double startX, startY, endX, endY, times[][], results[];
	static Node[] nodes;
	static boolean[] isOptimal;
	static int N;
	static PriorityQueue<Node> pq;
	
    public static void main(String[] args) throws IOException{
		init();
		System.out.println(solution());
    }
    
    static double solution() {
    	while(!pq.isEmpty()) {
    		Node n = pq.poll();
    		if(isOptimal[n.num]) continue;
    		results[n.num] = n.time;
//    		System.out.println(n.num+" "+n.time);
    		if(n.num==N) return n.time;
    		isOptimal[n.num] = true;
     		for(int i=0;i<=N;i++) {
     			Node n2 = nodes[i];
     			if(results[n2.num]>n.time+times[n.num][i]) {
     				results[n2.num] = n.time+times[n.num][i]; 
     				pq.add(new Node(n2.num, n2.x, n2.y, results[n2.num]*5));
//     				n2.time = results[n2.num]; // 우선순위 큐 안에 있는 객체 멤버를 수정
//     				pq.add(n2);  // 같은 애를 넣기. 삽입하지 않으면 우선순위 큐 정렬이 x
     			}
     		}
    	}
    	
    	return -1;
    }
    
    static void init() throws IOException {
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	startX = Double.parseDouble(st.nextToken());
    	startY = Double.parseDouble(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	endX = Double.parseDouble(st.nextToken());
    	endY = Double.parseDouble(st.nextToken());
    	
    	N = Integer.parseInt(br.readLine());
    	nodes = new Node[N+1];
    	results = new double[N+1]; // 결과(i번 노드까지 걸리는 시간)를 저장할 배열
    	Arrays.fill(results,Double.MAX_VALUE);
    	isOptimal = new boolean[N+1]; // i번째 원소의 최적 값을 구했는지 확인
    	
    	// 입력받으면서 pq에 바로 넣기
    	pq = new PriorityQueue<>();
    	for(int i=0;i<N;i++) {
    		st =new StringTokenizer(br.readLine());
    		double x = Double.parseDouble(st.nextToken());
    		double y = Double.parseDouble(st.nextToken());
    		double dist = getDistance(startX, startY,x, y);
    		
    		nodes[i] = new Node(i,x,y,dist);
//    		System.out.println(i+" "+dist+" "+nodes[i].time+" ");
    		pq.add(nodes[i]);
    	}
    	// 도착 지점을 N번째 원소로 사용
    	nodes[N] = new Node(N,endX,endY,getDistance(startX, startY, endX, endY));
    	pq.add(nodes[N]);
    	
    	// 서로 간 걸리는 시간을 저장
    	times = new double[N+1][N+1];
    	for(int i=0;i<=N;i++) {
    		for(int j=0;j<=N;j++) {
    			Node n1 = nodes[i], n2 =nodes[j];
    			times[i][j] = 2 + Math.abs(getDistance(n1.x, n1.y, n2.x, n2.y)-50)/5;
    		}
    	}
    	
    }
    
    static double getDistance(double x1, double y1, double x2, double y2) {
    	return Math.sqrt((Math.pow(x1-x2,2)+Math.pow(y1-y2,2)));
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N,M,K, cards[], parents[];
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(sb);
	}
	
	static void solution() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			int num = Integer.parseInt(st.nextToken());
			int index = upperBound(num);
			int card = find(index);
			if(card<M-1)
				union(card+1, card);
			sb.append(cards[card]).append("\n");
//			System.out.println(card);
//			System.out.println(Arrays.toString(parents));
		}
	}
	
	static int upperBound(int key) {
		int left = 0, right = M;
		while(left<right) {
			int mid = (left+right)/2;
			if(cards[mid] <= key) left = mid+1;
			else right = mid;
		}
		return left;
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px != py) parents[py] = px;
	}
	
	static int find(int x) {
		if(x==parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 유니온 파인드 사용
		cards = new int[M];
		parents = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			int num = Integer.parseInt(st.nextToken());
			cards[i] = num;
		}
		Arrays.sort(cards);
		// 정렬한 후 부모로 저장. 카드 번호가 아니라 인덱스로 저장
		for(int i=0;i<M;i++) {
			parents[i] = i;
		}
	}

}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, count1, count2;
	static List<int[]> items;
	static Deque<Integer> stack;

	public static void main(String[] args) throws IOException{
		init();
		solution();
		System.out.println(count1+" "+count2);
		br.close();
	}
	
	static void solution() {
		// 모든 봉우리를 시작 지점이 빠른 순으로 정렬
		Collections.sort(items, (a,b)->Integer.compare(a[0], b[0]));
//		for(int i=0;i<items.size();i++) {
//			System.out.println(Arrays.toString(items.get(i)));
//		}
		count1 = items.size();
		count2 = 0;
		for(int i=0;i<items.size();) {
//			System.out.println("for : "+i);
			i = check(i);
		}
	}
	
	// curr과 end 사이에 있는 다른 item들을 조사
	// i번째 item이 몇 개의 봉우리를 포함하고 있는지 반환하는 함수
	static int check(int i) {
//		System.out.println("check : "+i);
		boolean flag = true; // count2를 ++할지 결정하는 함수
		int[] curr = items.get(i);
		int end=curr[1];
		int next=i+1;
		while(next<items.size()) {
//			System.out.println(next);
			// 바깥 봉우리의 범위에 포함되는 경우
			if(items.get(next)[0]<end) {
				count1--;
				flag = false; 
				next = check(next);
			} else break; // 포함되지 않는 봉우리를 만나면 즉시 break
		}
		if(flag) count2++; // 다른 봉우리를 포함하지 않는 봉우리
		return next;
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		items = new ArrayList<>();
		int prevX = Integer.MAX_VALUE; // 단순 초기화. x가 0일 수도 있으므로
		int prevY = Integer.MAX_VALUE;
		stack = new ArrayDeque();
		int firstX=0, firstY=0; // 첫 번째 원소와 연결되는 경우 설정
		for(int i=0;i<N+1;i++) {
			int x=0, y=0;
			if(i==N) {
				x=firstX; y=firstY;
			}else {
				StringTokenizer st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
			}
			if(i==0) {
				firstX = x; firstY=y;
			}
			if(x==prevX && (y>0)^(prevY>0)) {
				// 현재 y가 0 이상이라면 봉우리 생성. stack에 넣기
				if(y>0) {
					stack.push(x);
				} else if(!stack.isEmpty()){
					// 스택에 이미 원소가 있고, 아래로 내려오는 선이라면
					int prev = stack.pop();
					if(prev<x) items.add(new int[] {prev,x});
					else items.add(new int[] {x,prev});
				} else {
					stack.push(x);
				}
			}
			prevX = x;
			prevY = y;
		}
		// 스택에 남아 있는 원소가 있다면 두 개가 봉우리
		if(!stack.isEmpty()) {
			int x1 = stack.pop();
			int x2 = stack.pop();
			items.add(new int[] {Math.min(x1, x2), Math.max(x1, x2)});
		}
		
	}
}

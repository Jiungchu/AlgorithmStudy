
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, inorder[], postorder[];
	static Map<Integer, Integer> indexMap;
	
	public static void main(String[] args) throws IOException{
		init();
		solution(postorder[N-1], 0, N-1, 0);
		System.out.println(sb.toString());
	}
	
	static void solution(int root, int left, int right, int depth) {
		sb.append(root).append(" ");
		// 종료 조건
//		System.out.println(root+" "+left+" "+right);
		if(left>=right) return;
		// 왼쪽 자식의 위치. inorder에서 현재 root의 index를 찾고, postorder에서 해당 index-1해주면 왼쪽 자식의 위치
		int lChildIndex = indexMap.get(root)-1-depth;
		if(lChildIndex>=left) { // 왼쪽 자식의 인덱스가 left 범위를 벗어난다면 왼쪽 자식이 없는 것
			solution(postorder[lChildIndex], left, lChildIndex, depth);
		}
		// inorder에서 root의 위치가 postorder의 root 바로 왼쪽이라면 오른쪽 자식이 없는 것
		// postorder에서 오른쪽 서브트리가 inorder의 서브트리보다 한 칸씩 뒤로 밀림
		int rChildIndex = right-1;
		if(lChildIndex != rChildIndex)
			solution(postorder[rChildIndex], lChildIndex+1, rChildIndex, depth+1);
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		inorder = new int[N];
		postorder = new int[N];
		indexMap = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
			indexMap.put(inorder[i], i);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
	}

}

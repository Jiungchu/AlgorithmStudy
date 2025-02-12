
import java.util.*;
import java.io.*;

public class Main {
	static int maxLength=0;
	static Map<Integer, List<int[]>> edges = new HashMap<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s; 
		while((s=br.readLine())!=null && !s.isEmpty()) {
			String[] edge = s.split(" ");
			int node1 = Integer.parseInt(edge[0]);
			int node2 = Integer.parseInt(edge[1]);
			int weight = Integer.parseInt(edge[2]);
			if(!edges.containsKey(node1)) {
				edges.put(node1, new LinkedList<>());
			} 
			edges.get(node1).add(new int[] {node2,weight});
		}
		
		// 리프 노드 to 리프 노드의 모든 경우의 수를 비교해야 하므로 리프 노드에서 시작
		// 마지막 노드는 반드시 리프 노드
		dfs(1);
		System.out.println(maxLength);
		br.close();
	}
	
	// 현재 점의 자식 노드부터 리프노드까지의 길이 중 가장 긴 길이를 return하는 함수
	// 각 노드에서의 최대 길이와, 현재 최대 길이를 노드별로 비교
	public static int dfs(int currNode) {
		// 리프 노드는 key가 되지 않음
		if(!edges.containsKey(currNode)) {
			return 0;
		}
		// 자식노드들을 순회하면서 리프노드까지 가장 긴 길이를 출력
		int max=0; int second=0;
		for(int[] edge : edges.get(currNode)) {
			int length = edge[1]+dfs(edge[0]);
			// 자식 노드들끼리 길이가 같을 수도 있음
			if(length>=max) {
				if(second<=max) second=max;
				max=length;
			} else if(second<length){
				second= length;
			}
		}
		// 해당 노드에서의 최대값과 전체 최대값을 비교
		if(max+second>maxLength) {
			maxLength=max+second;
		}
		return max;
	}
}

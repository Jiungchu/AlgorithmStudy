
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Map<Integer, List<Integer>> map;
	static boolean[] used;
	static int n, m, count;
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			init();
			findCombination(1);
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	static void findCombination(int index) {
		if(index==n+1) {
			count++;return;
		}
		boolean available= true;
		// 해당 재료와 어울릴 수 없는 재료가 포함되었는지 확인
		if(map.containsKey(index)) {
			for(int num : map.get(index)) {
				if(used[num]) {
					available = false;
					break;
				}
			}
		}
		// 궁합이 맞지 않은 재료가 포함되지 않은 경우
		if(available) {
			used[index] = true;
			findCombination(index+1);
			used[index] = false;
		}
		
		findCombination(index+1);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		used = new boolean[n+1]; // 재료 번호는 1번부터 시작
		count = 0;
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			// 서로 어울릴 수 없는 재료를 map으로 관리
			// 어떤 재료 번호로 접근하면, 해당 재료와 함께할 수 없는 재료를 모두 포함하는 리스트 반환
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			if(!map.containsKey(num1)) map.put(num1, new ArrayList<>());
			if(!map.containsKey(num2)) map.put(num2, new ArrayList<>());
			map.get(num1).add(num2);
			map.get(num2).add(num1);
		}
	}
}

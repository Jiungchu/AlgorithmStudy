
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	static void next(int count, String currString, int[] visited,List<Integer> list, HashMap<Integer,Integer> duplicated,int m) {
		if(count==m) {
			sb.append(currString+"\n");return;
		}
		for(int i=0;i<list.size();i++) {
			int num = list.get(i);
			// 중복이 있는 경우도 포함해서 생각
			if((duplicated.containsKey(num)&&visited[i]<duplicated.get(num)) || visited[i]==0) {
				int[] nextVisited = visited.clone();
				nextVisited[i]++;
				next(count+1, currString+" "+num, nextVisited ,list,duplicated, m);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt(); int m=sc.nextInt();
		HashSet<Integer> set = new HashSet<>();
		HashMap<Integer, Integer> duplicated = new HashMap<>();
		for(int i=0;i<n;i++) {
			int num = sc.nextInt();
			if(set.contains(num)) {
				if(duplicated.containsKey(num)) duplicated.put(num, duplicated.get(num)+1);
				else duplicated.put(num,2);
			}
			else set.add(num);
		}
		// 정렬
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		int len = list.size();
		for(int i=0;i<len;i++) {
			int[] visited = new int[len];
			visited[i] = 1;
			next(1, list.get(i)+"",visited,list,duplicated,m);
		}
		System.out.println(sb.toString());
		sc.close();
	}

}

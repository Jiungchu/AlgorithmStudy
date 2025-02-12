
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	static void next(int count, int curr, String currString, List<Integer> list, int m) {
		if (count == m) {
			sb.append(currString+"\n");
			return;
		}
		for (int i = curr; i < list.size(); i++) {
			int num = list.get(i);
			next(count + 1, i, currString + " " + num,list, m);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			int num = sc.nextInt();
			set.add(num);
		}
		// 정렬
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		int len = list.size();
		for (int i = 0; i < len; i++) {
			next(1, i, list.get(i) + "", list,  m);
		}
		System.out.println(sb.toString());
		sc.close();
	}

}

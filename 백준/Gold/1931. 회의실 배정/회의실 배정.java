
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> dict2 = new HashMap<Integer, Integer>();

		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			if (start == end) { // 회의 시작 시간과 끝나는 시간이 같으면 다른 HashMap에 별도로 관리
				if (dict.containsKey(start) && dict.get(start) == end) { // 이미 같은 지점에서 회의가 있었으면 ++
					ans++;
					continue;
				}
				if (dict.containsKey(start))
					dict2.put(start, dict.get(start));
				dict.put(start, end);
			} else if (dict.containsKey(start) && dict.get(start).equals(start)) {
				if ((!dict2.containsKey(start) || dict2.get(start) > end))
					dict2.put(start, end);
			} else {
				// 회의가 시작하는 시간별로 가장 작은 값만 저장
				if (!dict.containsKey(start) || dict.get(start) >= end) {
					dict.put(start, end);
				}
			}

		}

		// key들을 오름차순으로 정렬
		List<Integer> keyList = new ArrayList<>(dict.keySet());
		Collections.sort(keyList);

		// 가능한 가장 짧은 회의들만 넣기
		int end = 0;
		int currMin = 0;
		for (int key : keyList) {
			int currEnd = dict.get(key);
			if (key >= currMin || key==currEnd) { // 이전 point들의 최소값보다 더 시작 시간이 늦으면, 이전 지점 확정
				if(key==currEnd && currMin>key) ans--;
				ans++; // 마지막에 확정해주는 부분이 없기 때문에 최초 1번은 ++
				end = currMin; // 확정된 마지막 지점
				currMin = currEnd;
				if (key == currEnd && dict2.containsKey(key)) {
					ans++;
					currMin = dict2.get(key);
					currEnd = currMin;
				} else
					currMin = currEnd;
			}
			if (currEnd < currMin) {
				currMin = currEnd;
			}
		}

//		int lastKey = keyList.get(keyList.size() - 1);
//		if (lastKey == dict.get(lastKey) && dict2.containsKey(lastKey))
//			ans++;

		System.out.println(ans);
		br.close();

	}

}

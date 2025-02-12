
import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Set<Integer> set = new HashSet<>();

		String[] inputString = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(inputString[i]);
			arr[i] = num;
			if(set.contains(num)) continue;
			else set.add(num);
		}
		List<Integer> keyList = new ArrayList<>(set);
		Collections.sort(keyList);
		
		HashMap<Integer, Integer> dict = new HashMap<>();
		int order = 0;
		for(int key:keyList) {
			dict.put(key, order++);
		}
		
		for(int i=0;i<n;i++) {
			sb.append(dict.get(arr[i])+" ");
		}
		System.out.println(sb.toString());
		br.close();

	}

}

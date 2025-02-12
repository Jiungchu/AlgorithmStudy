import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 수열의 길이를 key로 하고, 해당 수열의 마지막 값을 value로 하는 map을 만듦
		// 이때 같은 길이에 대해서는, 마지막 값이 가장 작은 경우만 저장
		HashMap<Integer, Integer> dict = new HashMap<>();
		
		// 값을 입력받을 때 계산도 동시에
		int maxLength = 0;
		dict.put(++maxLength,sc.nextInt()); // 일단 첫 번째 값은 저장
		for(int i=0;i<n-1;i++) {
			int num=sc.nextInt();
			if(dict.get(maxLength)<num) { // 가장 긴 수열의 마지막 수보다 클 경우
				dict.put(++maxLength, num);
				continue;
			}
			for(int j=maxLength;j>0;j--) {
				// maxLength가 1이거나, 특정 길이 수열의 마지막 수보다 현재 값이 클 경우 값 저장
				if(dict.get(j)>num && (!dict.containsKey(j-1) || dict.get(j-1)<num)) {
					dict.put(j, num);
				}
					
			}
		}
		System.out.println(maxLength);
		
		sc.close();

	}

}

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] arr = new int[Math.max(2 * k + 1, 100001)];
		Arrays.fill(arr, -1);

		int ans = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		arr[n] = 0;
		queue.offer(n);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			int value = arr[curr];
			// 순간이동 가능한 모든 거리 탐색
			// k보다 멀리 가면 종료
			int index = curr;
			while (index <= k) {
				if (index == k) { // 해당 지점부터 2씩 곱해가며 모든 점 탐색
					ans = value;
					System.out.println(ans);
					return;
				}
				index = index << 1;
				if (arr[index] != -1)
					break;
				arr[index] = value;
				queue.offer(index);
			}
			if (curr > 0 && arr[curr - 1] == -1) {
				arr[curr - 1] = value + 1;
				queue.offer(curr - 1);
			}
			if (curr <= k && arr[curr + 1] == -1) {
				arr[curr + 1] = value + 1;
				queue.offer(curr + 1); // k보다 커지면 굳이 x
			}
		}

		sc.close();
	}

}

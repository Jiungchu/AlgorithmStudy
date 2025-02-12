import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int size = 200001;
	static int[] time = new int[size];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(n);
		int curr = 0;
		while (!queue.isEmpty()) {
			curr = queue.poll();
			if (curr == k)
				break;
			if (curr - 1 >= 0)
				queueOffer(queue, curr, curr - 1);
			if (curr + 1 < size - 1)
				queueOffer(queue, curr, curr + 1);
			if (curr * 2 < size - 1)
				queueOffer(queue, curr, 2 * curr);
		}
		System.out.println(time[curr]);

		sc.close();
	}

	static void queueOffer(Queue<Integer> queue, int prev, int curr) {
		if (time[curr] == 0 || time[curr] > time[prev] + 1) {
			queue.offer(curr);
			time[curr] = time[prev] + 1;
		}

	}
}
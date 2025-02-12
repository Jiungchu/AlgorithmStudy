
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	static void next(int curr, String currString, int n, int m, int count) {
		if (count == m) {
			sb.append(currString + "\n");
			return;
		}
		for (int i = curr; i <= n; i++) {
			String nextString = currString + " " + i;
			next(i, nextString, n, m, count + 1);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		for (int i = 1; i <= n; i++) {
			next(i, i + "", n, m, 1);
		}

		System.out.println(sb.toString());
		sc.close();

	}

}

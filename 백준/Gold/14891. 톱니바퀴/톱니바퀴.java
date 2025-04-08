import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int ans, K, magnetsArr[][], keys[];
	static List<ArrayDeque<Integer>> magnets;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(ans);
	}

	static void solution() throws IOException {
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			rotate(num, d, -1);
		}
		// 회전이 끝나고 가장 앞 원소들을 확인
		for (int i = 0; i < 4; i++) {
			int num = magnets.get(i).peekFirst();
			if (num == 1)
				ans += (1 << i);
		}
	}

	static void rotate(int num, int d, int from) { // 회전을 호출한 자석 번호도 전달
		// 인접한 자석들 확인. 각각 회전 정도를 반영
		// 왼쪽
		if (num != 0 && from != num - 1) {
			int left = magnetsArr[num - 1][(2 + keys[num - 1]) % 8];
			int right = magnetsArr[num][(6 + keys[num]) % 8];
			if (left != right)
				rotate(num - 1, -d, num); // 반대 방향으로 회전
		}
		// 오른쪽
		if (num != 3 && from != num + 1) {
			int left = magnetsArr[num][(2 + keys[num]) % 8];
			int right = magnetsArr[num + 1][(6 + keys[num + 1]) % 8];
			if (left != right)
				rotate(num + 1, -d, num); // 반대 방향으로 회전
		}

		ArrayDeque<Integer> cur = magnets.get(num);
		if (d == 1) { // 마지막 원소를 처음으로
			cur.addFirst(cur.pollLast());
		} else { // 첫 원소를 마지막으로
			cur.addLast(cur.pollFirst());
		}
		keys[num] -= d;
	}

	static void init() throws IOException {

		ans = 0;
		magnets = new ArrayList<>();
		magnetsArr = new int[4][8]; // key로 참조하기 위해 배열도 사용
		keys = new int[4]; // 각 자석에서 2, 6번 원소는 옆 자석과 연결되어 있으므로 정보를 인덱스 정보 저장
		for (int i = 0; i < 4; i++) {
			ArrayDeque<Integer> deq = new ArrayDeque<>();
			String str = br.readLine();
			// arraydeque를 사용해서 맨 앞, 맨 뒤 원소를 삽입 삭제 -> 회전 구현
			for (int j = 0; j < 8; j++) {
				int num = str.charAt(j) - '0';
				deq.offer(num);
				magnetsArr[i][j] = num;
			}
			keys[i] = 800; // 해당 자석이 회전한 정도를 저장. 음수가 될 수 있으므로 8의 배수를 여유롭게 넣어둠
			magnets.add(deq);
		}
		K = Integer.parseInt(br.readLine());
	}

}

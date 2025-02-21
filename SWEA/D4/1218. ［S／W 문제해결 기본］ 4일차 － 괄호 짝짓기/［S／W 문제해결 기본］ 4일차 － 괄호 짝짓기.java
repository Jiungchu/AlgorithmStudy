
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	static int t, length;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (t = 1; t <= 10; t++) {
			length = Integer.parseInt(br.readLine());
			String str = br.readLine();
			// 스택을 사용해서 풀이
			sb.append("#").append(t).append(" ");
			if(check(str)) sb.append(1);
			else sb.append(0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static boolean check(String str) {
		Deque<Character> stack = new ArrayDeque<>();
		for(int i=0;i<length;i++) {
			char currChar = str.charAt(i);
			// 여는 괄호는 스택에 넣기
			if(currChar=='(' || currChar=='{' || currChar=='[' || currChar=='<') {
				stack.push(currChar);
			} else if(!stack.isEmpty()) {
				// 스택의 마지막 괄호와 짝이 맞으면 계속 진행
				char lastChar = stack.pop();
				if((lastChar=='('&&currChar==')')||
						(lastChar=='['&&currChar==']')||
						(lastChar=='{'&&currChar=='}')||
						(lastChar=='<'&&currChar=='>')) continue;
				// 짝이 맞지 않으면 유효하지 않음
				else return false;
				
			} else {
				return false; // 스택이 비었는데 닫는 괄호가 들어온 경우
			}
		}
		if(stack.isEmpty() )return true;
		else return false; // 스택에 남아있는 문자가 있으면 짝이 맞지 않은 경우
	}
}

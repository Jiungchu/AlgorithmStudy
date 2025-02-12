
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String func = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String arrString = br.readLine();
			boolean isError = false;
			// [] 긁어내서 배열로 만들기
			String[] arr = arrString.substring(1, arrString.length() - 1).split(",");
			// 함수 적용. 인덱스만 관리하여 성능 향상
			int start = 0; int end=arr.length;
			boolean rev = false;
			for (int i = 0; i < func.length(); i++) {
				char currOper = func.charAt(i);
				if (currOper == 'R')
					if(rev) rev=false;
					else rev=true;
				else if (currOper == 'D') {
					if(rev) end--;
					else start++;
				}
				if (start>end || (n==0 && start>=end)) { // 0을 입력해도 length=1이라 별도로 처리해야 함
					sb.append("error");
					isError = true;
					break;
				}
			}
			
			if (!isError) {
				sb.append("[");
				if(!rev) { // 정방향
					for (int i = start; i < end; i++) {
						sb.append(arr[i]);
						if (i != end-1)
							sb.append(",");
					}
				} else {
					for (int i = end-1; i >= start; i--) {
						sb.append(arr[i]);
						if (i != start)
							sb.append(",");
					}
				}
				sb.append("]");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();

	}

}

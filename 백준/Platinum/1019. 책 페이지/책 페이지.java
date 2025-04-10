import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] count = new int[10];

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        // 1의 자리부터 한 자리씩 보기 
        for (int i = 1; N/i>0; i*= 10) {
            int high = N/(i*10); // 현재 자리 앞에 있는 수
            int cur = (N/i)%10; // 현재 자리의 수
            int low = N%i; // 현재 자리의 최소 수

            for (int d=0; d<10; d++) {
                if (d<cur) count[d] += (high+1) * i;
                else if (d==cur) count[d] += high*i + (low+1);
                else count[d] += high*i;

                if (d == 0) count[d] -= i; // 0은 가장 앞자리에 못 오므로 보정
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(count[i] + " ");
        }
    }
}
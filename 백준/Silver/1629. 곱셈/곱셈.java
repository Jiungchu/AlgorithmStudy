
import java.util.*;

public class Main {
	static HashMap<Integer, Long> dict = new HashMap<>();
 	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		System.out.println(remainder(a,c,b));
		sc.close();
	}

	static long remainder(int a, int c, int time) {
		if(time==1) return a%c;
		if(dict.containsKey(time)) return dict.get(time);
		int center = time/2;
		dict.put(time, (remainder(a,c,center)*remainder(a,c,time-center))%c);
		return dict.get(time);
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FlowControl {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter a number: ");
		try {
			int num = Integer.parseInt(br.readLine());
			boolean primeFlag = true;
			for(int i = 2; i < num; i++) {
				if(num % i == 0)
					primeFlag = false;
			}
			if(primeFlag)
				System.out.println("Prime No");
			else
				System.out.println("Not a Prime No");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

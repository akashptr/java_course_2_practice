import static java.lang.System.out;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class StaticImports {
	public static void main(String[] args) {
		out.println("Enter a number below");
		try (Scanner obj = new Scanner(System.in)) {
			double num = parseDouble(obj.next());
			out.println("Square root of the number is " + sqrt(num));
			out.println("Square of the number is " + pow(num, 2));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
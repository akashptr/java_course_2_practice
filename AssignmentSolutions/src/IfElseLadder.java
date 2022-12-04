
public class IfElseLadder {

	public static void main(String[] args) {
		double maths = 65;
		double physics = 68;
		double chemistry = 62;

		double average = ((maths + physics + chemistry) / 3);

		if (maths < 35 || physics < 35 || chemistry < 35) {
			System.out.println("Result is FAIL");
		} else if (average <= 59) {
			System.out.println("Grade C");
		} else if (average <= 69) {
			System.out.println("Grade B");
		} else {
			System.out.println("Grade A");
		}
	}
}
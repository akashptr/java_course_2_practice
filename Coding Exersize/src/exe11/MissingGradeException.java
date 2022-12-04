package exe11;

public class MissingGradeException extends Exception {
	int studentId;
	public MissingGradeException(int studentId) {
		this.studentId = studentId;
	}
	public int getStudentId() {
		return studentId;
	}
}

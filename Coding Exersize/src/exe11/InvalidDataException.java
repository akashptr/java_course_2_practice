package exe11;

public class InvalidDataException extends RuntimeException {
	public InvalidDataException(Exception cause) {
		this.initCause(cause);
	}
}

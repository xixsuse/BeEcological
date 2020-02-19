package error;

public class LengthPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public LengthPasswordException() {
		super();
	}
	
	public LengthPasswordException(String message) {
		super(message);
	}
	
	public LengthPasswordException(Throwable throwable) {
		super(throwable);
	}
}
package error;

public class InexistentUsernameException extends Exception {
	private static final long serialVersionUID = 1L;

	public InexistentUsernameException() {
		super();
	}
	
	public InexistentUsernameException(String message) {
		super(message);
	}
	
	public InexistentUsernameException(Throwable throwable) {
		super(throwable);
	}
}
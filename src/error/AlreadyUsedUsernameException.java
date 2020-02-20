package error;

import java.sql.SQLException;

public class AlreadyUsedUsernameException extends SQLException {
	private static final long serialVersionUID = 1L;

	public AlreadyUsedUsernameException() {
		super();
	}
	
	public AlreadyUsedUsernameException(String message) {
		super(message);
	}
	
	public AlreadyUsedUsernameException(Throwable throwable) {
		super(throwable);
	}
}
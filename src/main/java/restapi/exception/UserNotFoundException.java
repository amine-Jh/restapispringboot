package restapi.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException( Long id) {
		super(id+"  id not found ");
	}

}



public class StudentNotFoundException {
	private String message;

	public StudentNotFoundException(String message) {

		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
